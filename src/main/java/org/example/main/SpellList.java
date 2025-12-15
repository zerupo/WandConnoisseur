package org.example.main;

import org.example.spells.*;

import java.util.*;
import java.util.function.Predicate;

import org.reflections.Reflections;

public class SpellList{
    private Spell[] spells;

    public SpellList(Predicate<Spell> filter, Comparator<Spell> comparator){
        Reflections reflections = new Reflections("org.example.spells");
        Set<Class<? extends Spell>> subclasses = reflections.getSubTypesOf(Spell.class);
        List<Spell> spellList = new ArrayList<>();

        for(Class<? extends Spell> clazz : subclasses){
            try{
                Spell spell = clazz.getDeclaredConstructor().newInstance();
                if(filter.test(spell)){
                    spellList.add(spell);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        this.spells = spellList.toArray(new Spell[0]);
        Arrays.sort(this.spells, comparator);
    }

    public SpellList(Predicate<Spell> filter){
        this(filter, Comparator.comparing(Spell::getName));
    }

    public SpellList(Comparator<Spell> comparator){
        this(spell -> true, comparator);
    }

    public SpellList(){
        this(spell -> true, Comparator.comparing(Spell::getName));
    }

    public Spell getSpell(String alias){
        String newAlias = alias.trim().toLowerCase();
        for(int i=0; i < this.spells.length; i++){
            if(this.spells[i].containsAlias(newAlias)){
                return this.spells[i].clone();
            }
        }
        return null;
    }

    public Spell[] getSpells(){
        Spell[] result = new Spell[this.spells.length];
        for(int i=0; i < result.length; i++){
            result[i] = this.spells[i].clone();
        }
        return result;
    }

    public String[] getAllAlias(){
        String[] currentAlias;
        List <String> alias = new ArrayList<String>();
        for(int i=0; i < this.spells.length; i++){
            currentAlias = this.spells[i].getAlias();
            for(int j=0; j < currentAlias.length; j++){
                alias.add(currentAlias[j]);
            }
        }
        currentAlias = alias.toArray(new String[0]);
        Arrays.sort(currentAlias);
        return currentAlias;
    }
}