package org.example.main;

import org.example.menu.MenuTree;
import org.example.projectiles.Projectile;

import java.util.ArrayList;

class CastStateProjectile{
    private Projectile projectile;
    private int count;

    public CastStateProjectile(Projectile projectile){
        this.projectile = projectile;
        this.count = 1;
    }

    public CastStateProjectile(Projectile projectile, int count){
        this.projectile = projectile;
        this.count = count;
    }

    // getters
    public Projectile getProjectile(){
        return this.projectile;
    }

    public int getCount(){
        return this.count;
    }

    // setters
    public void setProjectile(Projectile projectile){
        this.projectile = projectile;
    }

    public void setCount(int count){
        this.count = count;
    }

    public void addCount(int count){
        this.count += count;
    }

    public void addCount(){
        this.count++;
    }
}

public class CastState{
    private ArrayList<CastStateProjectile> castStateProjectiles = new ArrayList<>();
    private int castDelay = 0;
    private DamageComponent damageComponent = new DamageComponent();
    private int lifetime = 0;
    private int critRate = 0;
    private int pattern = 0;
    private double spread = 0;

    public CastState(){
        // nothing
    }

    // getters
    public CastStateProjectile[] getCastStateProjectile(){
        return this.castStateProjectiles.toArray(new CastStateProjectile[0]);
    }

    public int getCastDelay(){
        return this.castDelay;
    }

    public DamageComponent getDamageComponent(){
        return this.damageComponent;
    }

    public int getLifetime(){
        return this.lifetime;
    }

    public int getCritRate(){
        return this.critRate;
    }

    public int getPattern(){
        return this.pattern;
    }

    public double getSpread(){
        return this.spread;
    }

    // setters
    public void setCastDelay(int castDelay){
        this.castDelay = castDelay;
    }

    public void setDamageComponent(DamageComponent damageComponent){
        this.damageComponent = damageComponent;
    }

    public void setLifetime(int lifetime){
        this.lifetime = lifetime;
    }

    public void setCritRate(int critRate){
        this.critRate = critRate;
    }

    public void setPattern(int pattern){
        this.pattern = pattern;
    }

    public void setSpread(double spread){
        this.spread = spread;
    }

    // adders
    public void addCastDelay(int castDelay){
        this.castDelay += castDelay;
    }

    public void addDamageComponent(DamageComponent damageComponent){
        this.damageComponent.add(damageComponent);
    }

    public void addLifetime(int lifetime){
        this.lifetime += lifetime;
    }

    public void addCritRate(int critRate){
        this.critRate += critRate;
    }

    public void addPattern(int pattern){
        this.pattern += pattern;
    }

    public void addSpread(double spread){
        this.spread += spread;
    }

    public void addProjectile(Projectile projectile){
        if(projectile.getTriggerType() == Projectile.TriggerType.none){
            for(int i=0; i < this.castStateProjectiles.size(); i++){
                if(this.castStateProjectiles.get(i).getProjectile().getClass() == projectile.getClass()){
                    this.castStateProjectiles.get(i).addCount();
                    return;
                }
            }
        }
        this.castStateProjectiles.add(new CastStateProjectile(projectile));
    }

    public CastState addProjectileTrigger(Projectile projectile, int timer, Projectile.TriggerType triggerType){
        CastState newCastState = new CastState();

        projectile.addTrigger(triggerType, timer, newCastState);
        this.addProjectile(projectile);

        return newCastState;
    }

    public CastState addProjectileTrigger(Projectile projectile, Projectile.TriggerType triggerType){
        return this.addProjectileTrigger(projectile, 0, triggerType);
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        StringBuilder innerBuilder = new StringBuilder();

        for(CastStateProjectile castStateProjectile : this.castStateProjectiles){
            innerBuilder.append("\n").append(castStateProjectile.getProjectile().getEmote());
            if(castStateProjectile.getCount() > 1){
                innerBuilder.append(" (x").append(castStateProjectile.getCount()).append(")");
            }
        }
        if(!innerBuilder.isEmpty()){
            result.append("# Projectiles").append(innerBuilder);
            innerBuilder.setLength(0);
        }

        if(this.castDelay != 0){innerBuilder.append(String.format("\nCast delay: %1$df (%2$3.2fs)", this.castDelay, this.castDelay/60.0));}
        if(this.damageComponent.getProjectile() != 0){innerBuilder.append("\nProjectile damage: ").append(this.damageComponent.getProjectile());}
        if(this.damageComponent.getMelee() != 0){innerBuilder.append("\nMelee damage: ").append(this.damageComponent.getMelee());}
        if(this.damageComponent.getExplosion() != 0){innerBuilder.append("\nExplosion damage: ").append(this.damageComponent.getExplosion());}
        if(this.damageComponent.getElectricity() != 0){innerBuilder.append("\nElectricity damage: ").append(this.damageComponent.getElectricity());}
        if(this.damageComponent.getFire() != 0){innerBuilder.append("\nFire damage: ").append(this.damageComponent.getFire());}
        if(this.damageComponent.getDrill() != 0){innerBuilder.append("\nDrill damage: ").append(this.damageComponent.getDrill());}
        if(this.damageComponent.getSlice() != 0){innerBuilder.append("\nSlice damage: ").append(this.damageComponent.getSlice());}
        if(this.damageComponent.getIce() != 0){innerBuilder.append("\nIce damage: ").append(this.damageComponent.getIce());}
        if(this.damageComponent.getHealing() != 0){innerBuilder.append("\nHealing damage: ").append(this.damageComponent.getHealing());}
        if(this.damageComponent.getPhysics_hit() != 0){innerBuilder.append("\nPhysics_hit damage: ").append(this.damageComponent.getPhysics_hit());}
        if(this.damageComponent.getRadioactive() != 0){innerBuilder.append("\nRadioactive damage: ").append(this.damageComponent.getRadioactive());}
        if(this.damageComponent.getPoison() != 0){innerBuilder.append("\nPoison damage: ").append(this.damageComponent.getPoison());}
        if(this.damageComponent.getOvereating() != 0){innerBuilder.append("\nOvereating damage: ").append(this.damageComponent.getOvereating());}
        if(this.damageComponent.getCurse() != 0){innerBuilder.append("\nCurse damage: ").append(this.damageComponent.getCurse());}
        if(this.damageComponent.getHealing() != 0){innerBuilder.append("\nHoly damage: ").append(this.damageComponent.getHealing());}
        if(this.lifetime != 0){innerBuilder.append("\nLifetime: ").append(this.lifetime).append("f");}
        if(this.critRate != 0){innerBuilder.append("\nCrit rate: ").append(this.critRate).append("%");}
        if(this.pattern != 0){innerBuilder.append("\nPattern: ").append(this.pattern).append("°");}
        if(this.spread != 0){innerBuilder.append("\nSpread: ").append(this.spread).append("°");}

        if(!innerBuilder.isEmpty()){
            if(!result.isEmpty()){
                result.append("\n");
            }
            result.append("# Stats").append(innerBuilder);
        }

        return result.toString();
    }

    public MenuTree toMenuTree(String id, String title){
        MenuTree menu = new MenuTree("", title, this.toString(), null, "");
        CastState innerCastState;

        for(CastStateProjectile castStateProjectile : this.castStateProjectiles){
            innerCastState = castStateProjectile.getProjectile().getTriggerCastState();
            if(innerCastState != null){
                menu.addChild(innerCastState.toMenuTree(castStateProjectile.getProjectile().getName() + " " + castStateProjectile.getProjectile().getTriggerType()));
            }
        }
        return menu;
    }

    private MenuTree toMenuTree(String title){
        return this.toMenuTree("", title);
    }
}