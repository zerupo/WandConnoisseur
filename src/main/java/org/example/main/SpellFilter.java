package org.example.main;

import org.example.projectiles.Projectile;
import org.example.spells.Spell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


enum Operator{GREATER, GREATER_OR_EQUAL, LOWER, LOWER_OR_EQUAL, EQUALS, DIFFERENT}
enum LogicGate{AND, OR}
enum PropertyType{BOOLEAN, INT, DOUBLE}
enum State{TRUE, FALSE, INVALID}
class Result{
    private State state;
    private String error;

    public Result(State state){
        this.state = state;
        this.error = "";
    }

    public Result(State state, String error){
        this.state = state;
        if(state == State.INVALID){
            this.error = error;
        }else{
            this.error = "";
        }
    }

    // getters
    public State getState(){
        return this.state;
    }

    public String getError(){
        return this.error;
    }

    // setters
    public void setState(State state){
        this.state = state;
        if(state != State.INVALID){
            this.error = "";
        }
    }

    public void setState(State state, String error){
        this.state = state;
        if(state == State.INVALID){
            this.error = error;
        }else{
            this.error = "";
        }
    }

    public void setError(String error){
        if(this.state == State.INVALID){
            this.error = error;
        }
    }
}
record TypedProperty(PropertyType type, Object value){}

class Expression{
    private final String value1;
    private final String value2;
    private final Operator operator;
    private LogicGate logicGate;
    private Expression expression;

    // leaf
    public Expression(String value1, Operator operator, String value2){
        this.value1 = value1.toLowerCase().trim();
        this.operator = operator;
        this.value2 = value2.toLowerCase().trim();
        this.logicGate = null;
        this.expression = null;
    }

    // node
    public Expression(String value1, Operator operator, String value2, LogicGate logicGate, Expression expression){
        this.value1 = value1.toLowerCase().trim();
        this.operator = operator;
        this.value2 = value2.toLowerCase().trim();
        this.logicGate = logicGate;
        this.expression = expression;
    }

    public void add(LogicGate logicGate, Expression expression){
        this.logicGate = logicGate;
        this.expression = expression;
    }

    public Result evaluate(Spell spell){
        Function<Spell, TypedProperty> resolver = SpellFilter.PROPERTY_RESOLVERS.get(this.value1.toLowerCase());
        TypedProperty typedProperty;
        Result result = new Result(State.INVALID, "Erreur inconnue");
        boolean valueBoolean;
        int[] valueInt = new int[2];
        double[] valueDouble = new double[2];

        if(resolver == null){
            result.setState(State.INVALID, "\"" + this + "\" -> la propriété \"" + this.value1 + "\" est inconnue");
            return result;
        }

        typedProperty = resolver.apply(spell);
        if(typedProperty == null){
            return result;
        }

        if(typedProperty.value() == null){
            result.setState(State.FALSE);
        }else{
            switch(typedProperty.type()){
                case BOOLEAN -> {
                    if(this.operator != Operator.EQUALS && this.operator != Operator.DIFFERENT){
                        result.setError("\"" + this + "\" -> \"" + operatorToString(this.operator) + "\" n'est pas un comparateur valide pour un booléen");
                        return result;
                    }
                    if(!this.value2.equalsIgnoreCase("true") && !this.value2.equalsIgnoreCase("false")){
                        result.setError("\"" + this + "\" -> \"" + this.value2 + "\" n'est pas un booléen valide, remplacez par \"true\" ou \"false\"");
                        return result;
                    }

                    valueBoolean = (Boolean)typedProperty.value();
                    if(this.operator == Operator.DIFFERENT){
                        valueBoolean = !valueBoolean;
                    }
                    result.setState((valueBoolean == Boolean.parseBoolean(this.value2)) ? State.TRUE : State.FALSE);
                }
                case INT -> {
                    if(this.value2.equalsIgnoreCase("true") || this.value2.equalsIgnoreCase("false")){
                        result.setError("\"" + this + "\" -> \"" + this.value2 + "\" n'est pas un entier valide");
                        return result;
                    }

                    valueInt[0] = (Integer) typedProperty.value();
                    valueInt[1] = Integer.parseInt(this.value2);
                    result.setState(switch(this.operator){
                        case GREATER -> (valueInt[0] > valueInt[1]) ? State.TRUE : State.FALSE;
                        case GREATER_OR_EQUAL -> (valueInt[0] >= valueInt[1]) ? State.TRUE : State.FALSE;
                        case LOWER -> (valueInt[0] < valueInt[1]) ? State.TRUE : State.FALSE;
                        case LOWER_OR_EQUAL -> (valueInt[0] <= valueInt[1]) ? State.TRUE : State.FALSE;
                        case EQUALS -> (valueInt[0] == valueInt[1]) ? State.TRUE : State.FALSE;
                        case DIFFERENT -> (valueInt[0] != valueInt[1]) ? State.TRUE : State.FALSE;
                    });
                }
                case DOUBLE -> {
                    if(this.value2.equalsIgnoreCase("true") || this.value2.equalsIgnoreCase("false")){
                        result.setError("\"" + this + "\" -> \"" + this.value2 + "\" n'est pas un nombre valide");
                        return result;
                    }

                    valueDouble[0] = (Double) typedProperty.value();
                    valueDouble[1] = Double.parseDouble(this.value2);
                    result.setState(switch(this.operator){
                        case GREATER -> (valueDouble[0] > valueDouble[1]) ? State.TRUE : State.FALSE;
                        case GREATER_OR_EQUAL -> (valueDouble[0] >= valueDouble[1]) ? State.TRUE : State.FALSE;
                        case LOWER -> (valueDouble[0] < valueDouble[1]) ? State.TRUE : State.FALSE;
                        case LOWER_OR_EQUAL -> (valueDouble[0] <= valueDouble[1]) ? State.TRUE : State.FALSE;
                        case EQUALS -> (valueDouble[0] == valueDouble[1]) ? State.TRUE : State.FALSE;
                        case DIFFERENT -> (valueDouble[0] != valueDouble[1]) ? State.TRUE : State.FALSE;
                    });
                }
            }
        }

        if(this.expression == null || result.getState() == State.INVALID || result.getState() == State.TRUE && this.logicGate == LogicGate.OR || result.getState() == State.FALSE && this.logicGate == LogicGate.AND){
            return result;
        }

        return this.expression.evaluate(spell);
    }

    public String toString(){
        return this.value1 + " " + operatorToString(this.operator) + " " + this.value2;
    }

    private String operatorToString(Operator operator){
        String result = "";
        result = switch(operator){
            case GREATER -> ">";
            case GREATER_OR_EQUAL -> ">=";
            case LOWER -> "<";
            case LOWER_OR_EQUAL -> "<=";
            case EQUALS -> "=";
            case DIFFERENT -> " !=";
        };
        return result;
    }
}

class ExpressionParser{
    private final Pattern p = Pattern.compile("^ *(?i)([a-z0-9_]+) *([><]=?|!?=) *([+-]?[0-9]+(?:\\.[0-9]+)?|true|false) *$");
    private final String[] tokens;
    private int pos = 0;

    public ExpressionParser(String input){
        this.tokens = tokenize(input);
    }

    public Expression parse(){
        this.pos = 0;
        return this.parseOr();
    }

    private Expression parseOr(){
        Expression left = parseAnd();
        while(pos < tokens.length && tokens[pos].equals("or")){
            pos++; // skip "or"
            Expression right = parseAnd();
            left.add(LogicGate.OR, right);
        }
        return left;
    }

    private Expression parseAnd(){
        Expression left = parsePrimary();
        while(pos < tokens.length && tokens[pos].equals("and")){
            pos++; // skip "and"
            Expression right = parsePrimary();
            left.add(LogicGate.AND, right);
        }
        return left;
    }

    private Expression parsePrimary(){
        if(pos >= tokens.length){
            throw new IllegalArgumentException("Erreur inconnue");
        }

        if(tokens[pos].equals("(")){
            pos++; // skip "("
            Expression expr = parseOr();
            if(pos >= tokens.length || !tokens[pos].equals(")")){
                throw new IllegalArgumentException("Erreur parenthèses invalides dans la condition \"" + String.join(" ", this.tokens) + "\"");
            }
            pos++; // skip ")"
            return expr;
        }else{
            return parseExpression();
        }
    }

    private Expression parseExpression(){
        if(pos >= tokens.length){
            throw new IllegalArgumentException("Erreur inconnue");
        }

        String expr = tokens[pos++];

        Matcher m = this.p.matcher(expr);
        if(m.find()){
            Operator operator = switch (m.group(2)){
                case ">" -> Operator.GREATER;
                case ">=" -> Operator.GREATER_OR_EQUAL;
                case "<" -> Operator.LOWER;
                case "<=" -> Operator.LOWER_OR_EQUAL;
                case "!=" -> Operator.DIFFERENT;
                default -> Operator.EQUALS;
            };
            return new Expression(m.group(1), operator, m.group(3));
        }else{
            throw new IllegalArgumentException("Erreur de syntaxe dans la condition \"" + expr + "\"");
        }
    }

    private String[] tokenize(String input){
        List<String> resultList = new ArrayList<>();
        String currentToken = "";
        boolean previousIsToken = true;
        int nbParenthesis = 0;

        for(int i=0; i < input.length(); i++){
            char c = input.charAt(i);
            switch(c){
                case '(' -> {
                    nbParenthesis++;
                    if(!currentToken.equals("")){
                        if(previousIsToken){
                            resultList.add(currentToken);
                        }else{
                            resultList.set(resultList.size() - 1, resultList.get(resultList.size() - 1) + " " + currentToken);
                        }
                    }
                    resultList.add(String.valueOf(c));
                    currentToken = "";
                    previousIsToken = true;
                }
                case ')' -> {
                    nbParenthesis--;
                    if(nbParenthesis < 0){
                        throw new IllegalArgumentException("Erreur parenthèses invalides dans la condition \"" + input + "\"");
                    }
                    if(!currentToken.equals("")){
                        if(previousIsToken){
                            resultList.add(currentToken);
                        }else{
                            resultList.set(resultList.size() - 1, resultList.get(resultList.size() - 1) + " " + currentToken);
                        }
                    }
                    resultList.add(String.valueOf(c));
                    currentToken = "";
                    previousIsToken = true;
                }
                case ' ' -> {
                    switch(currentToken){
                        case "and", "or", "(", ")":
                            resultList.add(currentToken);
                            currentToken = "";
                            previousIsToken = true;
                            break;
                        case "":
                            break;
                        default:
                            if(previousIsToken){
                                resultList.add(currentToken);
                            }else{
                                resultList.set(resultList.size() - 1, resultList.get(resultList.size() - 1) + " " + currentToken);
                            }
                            currentToken = "";
                            previousIsToken = false;
                            break;
                    }
                }
                default -> currentToken += c;
            }
        }
        if(!currentToken.equals("")){
            if(previousIsToken){
                resultList.add(currentToken);
            }else{
                resultList.set(resultList.size() - 1, resultList.get(resultList.size() - 1) + " " + currentToken);
            }
        }
        if(nbParenthesis != 0){
            throw new IllegalArgumentException("Erreur parenthèses invalides dans la condition \"" + input + "\"");
        }

        return resultList.toArray(new String[0]);
    }
}

public class SpellFilter{
    public static final Map<String, Function<Spell, TypedProperty>> PROPERTY_RESOLVERS = Map.ofEntries(
        // boolean
        entry("has_charges", spell -> new TypedProperty(PropertyType.BOOLEAN, spell.getHasCharges())),
        entry("has_related_projectile", spell -> new TypedProperty(PropertyType.BOOLEAN, spell.getRelatedProjectile() != null)),
        entry("never_unlimited", spell -> new TypedProperty(PropertyType.BOOLEAN, spell.getNeverUnlimited())),
        entry("recursive", spell -> new TypedProperty(PropertyType.BOOLEAN, spell.getRecursive())),
        entry("set_recoil", spell -> new TypedProperty(PropertyType.BOOLEAN, spell.getSetRecoil())),

        // int
        entry("cast_delay", spell -> new TypedProperty(PropertyType.INT, spell.getCastDelay())),
        entry("crit_rate", spell -> new TypedProperty(PropertyType.INT, spell.getCritRate())),
        entry("lifetime", spell -> new TypedProperty(PropertyType.INT, spell.getLifetime())),
        entry("mana", spell -> new TypedProperty(PropertyType.INT, spell.getManaCost())),
        entry("max_charges", spell -> new TypedProperty(PropertyType.INT, spell.getMaxCharges())),
        entry("pattern", spell -> new TypedProperty(PropertyType.INT, spell.getPattern())),
        entry("price", spell -> new TypedProperty(PropertyType.INT, spell.getPrice())),
        entry("projectile_count", spell -> new TypedProperty(PropertyType.INT, spell.getRelatedProjectileCount())),
        entry("projectile_lifetime_max", spell -> {Projectile projectile = spell.getRelatedProjectile(); return new TypedProperty(PropertyType.INT, projectile != null ? projectile.getLifetimeMax() : null);}),
        entry("projectile_lifetime_min", spell -> {Projectile projectile = spell.getRelatedProjectile(); return new TypedProperty(PropertyType.INT, projectile != null ? projectile.getLifetimeMin() : null);}),
        entry("projectile_lifetime_randomness", spell -> {Projectile projectile = spell.getRelatedProjectile(); return new TypedProperty(PropertyType.INT, projectile != null ? projectile.getLifetimeRandomness() : null);}),
        entry("recharge_time", spell -> new TypedProperty(PropertyType.INT, spell.getRechargeTime())),
        entry("timer_length", spell -> new TypedProperty(PropertyType.INT, spell.getTimerLength())),

        // double
        entry("damage", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getTotal())),
        entry("damage_curse", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getCurse())),
        entry("damage_drill", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getDrill())),
        entry("damage_electricity", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getElectricity())),
        entry("damage_explosion", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getExplosion())),
        entry("damage_fire", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getFire())),
        entry("damage_healing", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getHealing())),
        entry("damage_holy", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getHoly())),
        entry("damage_ice", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getIce())),
        entry("damage_melee", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getMelee())),
        entry("damage_overeating", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getOvereating())),
        entry("damage_physics_hit", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getPhysics_hit())),
        entry("damage_poison", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getPoison())),
        entry("damage_projectile", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getProjectile())),
        entry("damage_radioactive", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getRadioactive())),
        entry("damage_slice", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getDamageComponent().getSlice())),
        entry("projectile_speed_min", spell -> {Projectile projectile = spell.getRelatedProjectile(); return new TypedProperty(PropertyType.DOUBLE, projectile != null ? projectile.getSpeedMin() : null);}),
        entry("projectile_speed_max", spell -> {Projectile projectile = spell.getRelatedProjectile(); return new TypedProperty(PropertyType.DOUBLE, projectile != null ? projectile.getSpeedMax() : null);}),
        entry("recoil", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getRecoil())),
        entry("screenshake", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getScreenshake())),
        entry("spread", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpread())),
        entry("t0", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[0])),
        entry("t1", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[1])),
        entry("t2", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[2])),
        entry("t3", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[3])),
        entry("t4", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[4])),
        entry("t5", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[5])),
        entry("t6", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[6])),
        entry("t7", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[7])),
        entry("t8", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[8])),
        entry("t9", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[9])),
        entry("t10", spell -> new TypedProperty(PropertyType.DOUBLE, spell.getSpawnProbability()[10]))
    );

    public static final Map<String, Function<Spell, String>> STRING_PROPERTY_RESOLVERS = Map.ofEntries(
        // string
        entry("alias", Spell::getAliasString),
        entry("charge", Spell::getChargeString),
        entry("projectile_lifetime", spell -> {Projectile projectile = spell.getRelatedProjectile(); return projectile != null ? projectile.getLifetimeString() : "<null>";}),
        entry("projectile_speed", spell -> {Projectile projectile = spell.getRelatedProjectile(); return projectile != null ? projectile.getSpeedString() : "<null>";}),
        entry("tier", Spell::getTierString),
        entry("trigger_type", spell -> spell.getTriggerType().toString()),
        entry("type", spell -> spell.getType().toString()),

        // boolean
        entry("has_charges", spell -> String.valueOf(spell.getHasCharges())),
        entry("has_related_projectile", spell -> String.valueOf(spell.getRelatedProjectile() != null)),
        entry("never_unlimited", spell -> String.valueOf(spell.getNeverUnlimited())),
        entry("recursive", spell -> String.valueOf(spell.getRecursive())),
        entry("set_recoil", spell -> String.valueOf(spell.getSetRecoil())),

        // int
        entry("cast_delay", spell -> String.format("%1$df (%2$3.2fs)", spell.getCastDelay(), spell.getCastDelay()/60.0)),
        entry("crit_rate", spell -> String.valueOf(spell.getCritRate()) + "%"),
        entry("lifetime", spell -> String.format("%1$df (%2$3.2fs)", spell.getLifetime(), spell.getLifetime()/60.0)),
        entry("mana", spell -> String.valueOf(spell.getManaCost())),
        entry("max_charges", spell -> String.valueOf(spell.getMaxCharges())),
        entry("pattern", spell -> spell.getPattern() + "°"),
        entry("price", spell -> String.valueOf(spell.getPrice())),
        entry("projectile_count", spell -> String.valueOf(spell.getRelatedProjectileCount())),
        entry("projectile_lifetime_max", spell -> {Projectile projectile = spell.getRelatedProjectile(); return projectile != null ? String.valueOf(projectile.getLifetimeMax()) : "<null>";}),
        entry("projectile_lifetime_min", spell -> {Projectile projectile = spell.getRelatedProjectile(); return projectile != null ? String.valueOf(projectile.getLifetimeMin()) : "<null>";}),
        entry("projectile_lifetime_randomness", spell -> {Projectile projectile = spell.getRelatedProjectile(); return projectile != null ? String.valueOf(projectile.getLifetimeRandomness()) : "<null>";}),
        entry("recharge_time", spell -> String.format("%1$df (%2$3.2fs)", spell.getRechargeTime(), spell.getRechargeTime()/60.0)),
        entry("timer_length", spell -> String.format("%1$df (%2$3.2fs)", spell.getTimerLength(), spell.getTimerLength()/60.0)),

        // double
        entry("damage", spell -> String.valueOf(spell.getDamageComponent().getTotal())),
        entry("damage_curse", spell -> String.valueOf(spell.getDamageComponent().getCurse())),
        entry("damage_drill", spell -> String.valueOf(spell.getDamageComponent().getDrill())),
        entry("damage_electricity", spell -> String.valueOf(spell.getDamageComponent().getElectricity())),
        entry("damage_explosion", spell -> String.valueOf(spell.getDamageComponent().getExplosion())),
        entry("damage_fire", spell -> String.valueOf(spell.getDamageComponent().getFire())),
        entry("damage_healing", spell -> String.valueOf(spell.getDamageComponent().getHealing())),
        entry("damage_holy", spell -> String.valueOf(spell.getDamageComponent().getHoly())),
        entry("damage_ice", spell -> String.valueOf(spell.getDamageComponent().getIce())),
        entry("damage_melee", spell -> String.valueOf(spell.getDamageComponent().getMelee())),
        entry("damage_overeating", spell -> String.valueOf(spell.getDamageComponent().getOvereating())),
        entry("damage_physics_hit", spell -> String.valueOf(spell.getDamageComponent().getPhysics_hit())),
        entry("damage_poison", spell -> String.valueOf(spell.getDamageComponent().getPoison())),
        entry("damage_projectile", spell -> String.valueOf(spell.getDamageComponent().getProjectile())),
        entry("damage_radioactive", spell -> String.valueOf(spell.getDamageComponent().getRadioactive())),
        entry("damage_slice", spell -> String.valueOf(spell.getDamageComponent().getSlice())),
        entry("projectile_speed_min", spell -> {Projectile projectile = spell.getRelatedProjectile(); return projectile != null ? String.valueOf(projectile.getSpeedMin()) : "<null>";}),
        entry("projectile_speed_max", spell -> {Projectile projectile = spell.getRelatedProjectile(); return projectile != null ? String.valueOf(projectile.getSpeedMax()) : "<null>";}),
        entry("recoil", spell -> (spell.getSetRecoil() ? "=" : "") + String.valueOf(spell.getRecoil())),
        entry("screenshake", spell -> String.valueOf(spell.getScreenshake())),
        entry("spread", spell -> String.valueOf(spell.getSpread()) + "°"),
        entry("t0", spell -> String.valueOf(spell.getSpawnProbability()[0])),
        entry("t1", spell -> String.valueOf(spell.getSpawnProbability()[1])),
        entry("t2", spell -> String.valueOf(spell.getSpawnProbability()[2])),
        entry("t3", spell -> String.valueOf(spell.getSpawnProbability()[3])),
        entry("t4", spell -> String.valueOf(spell.getSpawnProbability()[4])),
        entry("t5", spell -> String.valueOf(spell.getSpawnProbability()[5])),
        entry("t6", spell -> String.valueOf(spell.getSpawnProbability()[6])),
        entry("t7", spell -> String.valueOf(spell.getSpawnProbability()[7])),
        entry("t8", spell -> String.valueOf(spell.getSpawnProbability()[8])),
        entry("t9", spell -> String.valueOf(spell.getSpawnProbability()[9])),
        entry("t10", spell -> String.valueOf(spell.getSpawnProbability()[10]))
    );

    public SpellFilter(){
        // nothing
    }

    public Spell[] filter(Spell[] spells, String input){
        Expression expression = new ExpressionParser(input).parse();
        ArrayList<Spell> result = new ArrayList<>();
        Result currentResult;

        if(expression == null){
            throw new IllegalArgumentException("Erreur de syntaxe dans la condition \"" + input + "\"");
        }

        for(int i=0; i < spells.length; i++){
            currentResult = expression.evaluate(spells[i]);
            switch(currentResult.getState()){
                case TRUE -> result.add(spells[i]);
                case INVALID -> throw new IllegalArgumentException("Erreur lors de l'évaluation : " + currentResult.getError());
            }
        }

        return result.toArray(new Spell[0]);
    }

    private static class SortStruct{
        String propertyName;
        public Function<Spell, TypedProperty> resolver;
        public boolean sortType; // ASC = false

        public SortStruct(String propertyName, Function<Spell, TypedProperty> resolver, boolean sortType){
            this.propertyName = propertyName;
            this.resolver = resolver;
            this.sortType = sortType;
        }

        public SortStruct(String propertyName, Function<Spell, TypedProperty> resolver){
            this.propertyName = propertyName;
            this.resolver = resolver;
            this.sortType = false;
        }
    }

    public Spell[] sort(Spell[] spells, String input){
        Pattern p = Pattern.compile("^ *(?i)([a-z0-9_]+) *(|ASC|DESC) *$");
        Matcher m;
        String[] inputs = input.split(",");
        SortStruct[] sortStruct = new SortStruct[inputs.length];
        double[][] comparisonValues = new double[spells.length][inputs.length];
        TypedProperty typedProperty;
        Spell currentSpell;
        double[] currentComparisonValue;

        for(int i=0; i < inputs.length; i++){
            m = p.matcher(inputs[i]);
            if(m.find()){
                sortStruct[i] = new SortStruct(m.group(1), PROPERTY_RESOLVERS.get(m.group(1)), m.group(2).equalsIgnoreCase("DESC"));
                if(sortStruct[i].resolver == null){
                    throw new IllegalArgumentException("\"" + inputs[i] + "\" -> la propriété \"" + m.group(1) + "\" est inconnue");
                }
            }else{
                throw new IllegalArgumentException("Erreur de syntaxe dans le tri \"" + inputs[i] + "\"");
            }
        }

        // use NaN value as default to avoid multiple calculations of the same property/spell
        for(int i=0; i < comparisonValues.length; i++){
            Arrays.fill(comparisonValues[i], Double.NaN);
        }

        for(int i=0; i < spells.length - 1; i++){
            for(int j=i+1; j < spells.length; j++){
                for(int k=0; k < comparisonValues[i].length; k++){
                    if(Double.isNaN(comparisonValues[i][k])){
                        typedProperty = sortStruct[k].resolver.apply(spells[i]);
                        if(typedProperty.value() != null){
                            comparisonValues[i][k] = switch(typedProperty.type()){
                                case BOOLEAN -> ((Boolean)typedProperty.value()) ? 1.0 : 0.0;
                                case INT -> (Integer)typedProperty.value();
                                case DOUBLE -> (Double)typedProperty.value();
                            };
                        }else{
                            comparisonValues[i][k] = -Double.MAX_VALUE;
                        }
                    }
                    if(Double.isNaN(comparisonValues[j][k])){
                        typedProperty = sortStruct[k].resolver.apply(spells[j]);
                        if(typedProperty.value() != null){
                            comparisonValues[j][k] = switch(typedProperty.type()){
                                case BOOLEAN -> ((Boolean)typedProperty.value()) ? 1.0 : 0.0;
                                case INT -> (Integer)typedProperty.value();
                                case DOUBLE -> (Double)typedProperty.value();
                            };
                        }else{
                            comparisonValues[j][k] = -Double.MAX_VALUE;
                        }
                    }
                    if(comparisonValues[i][k] != comparisonValues[j][k]){
                        if((comparisonValues[i][k] > comparisonValues[j][k]) != sortStruct[k].sortType){ // != to invert sort order depending on the type
                            currentSpell = spells[i];
                            currentComparisonValue = comparisonValues[i];
                            spells[i] = spells[j];
                            comparisonValues[i] = comparisonValues[j];
                            spells[j] = currentSpell;
                            comparisonValues[j] = currentComparisonValue;
                        }
                        break;
                    }
                }
            }
        }
        return spells;
    }

    // I can't just make an array of Function<Spell, String> because java is stupid, so I have to do this...
    private static class StringPropertyStruct{
        public Function<Spell, String> resolver;

        public StringPropertyStruct(Function<Spell, String> resolver){
            this.resolver = resolver;
        }
    }

    public String[][] getStringProperties(Spell[] spells, String input){
        String[] properties = input.split(",");
        String[][] result;
        StringPropertyStruct[] stringPropertyStruct = new StringPropertyStruct[properties.length];

        Arrays.parallelSetAll(properties, (i) -> properties[i].trim().toLowerCase());
        for(int i=0; i < properties.length; i++){
            stringPropertyStruct[i] = new StringPropertyStruct(SpellFilter.STRING_PROPERTY_RESOLVERS.get(properties[i]));
            if(stringPropertyStruct[i].resolver == null){
                throw new IllegalArgumentException("Erreur la propriété \"" + properties[i] + "\" est inconnue");
            }
        }

        result = new String[properties.length][spells.length + 1];
        for(int i=0; i < result.length; i++){
            result[i][0] = properties[i];
            for(int j=1; j < result[i].length; j++){
                result[i][j] = stringPropertyStruct[i].resolver.apply(spells[j - 1]);
            }
        }

        return result;
    }
}