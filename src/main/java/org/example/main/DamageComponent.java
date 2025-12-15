package org.example.main;

public class DamageComponent{
    private double projectile = 0.0;
    private double melee = 0.0;
    private double explosion = 0.0;
    private double electricity = 0.0;
    private double fire = 0.0;
    private double drill = 0.0;
    private double slice = 0.0;
    private double ice = 0.0;
    private double healing = 0.0;
    private double physics_hit = 0.0;
    private double radioactive = 0.0;
    private double poison = 0.0;
    private double overeating = 0.0;
    private double curse = 0.0;
    private double holy = 0.0;

    public DamageComponent(double projectile, double melee, double explosion, double electricity, double fire, double drill, double slice, double ice, double healing, double physics_hit, double radioactive, double poison, double overeating, double curse, double holy){
        this.projectile = projectile;
        this.melee = melee;
        this.explosion = explosion;
        this.electricity = electricity;
        this.fire = fire;
        this.drill = drill;
        this.slice = slice;
        this.ice = ice;
        this.healing = healing;
        this.physics_hit = physics_hit;
        this.radioactive = radioactive;
        this.poison = poison;
        this.overeating = overeating;
        this.curse = curse;
        this.holy = holy;
    }

    public DamageComponent(DamageComponent original){
        this.projectile = original.projectile;
        this.melee = original.melee;
        this.explosion = original.explosion;
        this.electricity = original.electricity;
        this.fire = original.fire;
        this.drill = original.drill;
        this.slice = original.slice;
        this.ice = original.ice;
        this.healing = original.healing;
        this.physics_hit = original.physics_hit;
        this.radioactive = original.radioactive;
        this.poison = original.poison;
        this.overeating = original.overeating;
        this.curse = original.curse;
        this.holy = original.holy;
    }

    public DamageComponent(){
        // default values
    }

    public double[] getAsArray(){
        double[] result = new double[15];
        result[0] = this.projectile;
        result[1] = this.melee;
        result[2] = this.explosion;
        result[3] = this.electricity;
        result[4] = this.fire;
        result[5] = this.drill;
        result[6] = this.slice;
        result[7] = this.ice;
        result[8] = this.healing;
        result[9] = this.physics_hit;
        result[10] = this.radioactive;
        result[11] = this.poison;
        result[12] = this.overeating;
        result[13] = this.curse;
        result[14] = this.holy;

        return result;
    }

    // getters
    public double getProjectile(){
        return this.projectile;
    }

    public double getMelee(){
        return this.melee;
    }

    public double getExplosion(){
        return this.explosion;
    }

    public double getElectricity(){
        return this.electricity;
    }

    public double getFire(){
        return this.fire;
    }

    public double getDrill(){
        return this.drill;
    }

    public double getSlice(){
        return this.slice;
    }

    public double getIce(){
        return this.ice;
    }

    public double getHealing(){
        return this.healing;
    }

    public double getPhysics_hit(){
        return this.physics_hit;
    }

    public double getRadioactive(){
        return this.radioactive;
    }

    public double getPoison(){
        return this.poison;
    }

    public double getOvereating(){
        return this.overeating;
    }

    public double getCurse(){
        return this.curse;
    }

    public double getHoly(){
        return this.holy;
    }

    // setters
    public void setProjectile(double projectile){
        this.projectile = projectile;
    }

    public void setMelee(double melee){
        this.melee = melee;
    }

    public void setExplosion(double explosion){
        this.explosion = explosion;
    }

    public void setElectricity(double electricity){
        this.electricity = electricity;
    }

    public void setFire(double fire){
        this.fire = fire;
    }

    public void setDrill(double drill){
        this.drill = drill;
    }

    public void setSlice(double slice){
        this.slice = slice;
    }

    public void setIce(double ice){
        this.ice = ice;
    }

    public void setHealing(double healing){
        this.healing = healing;
    }

    public void setPhysics_hit(double physics_hit){
        this.physics_hit = physics_hit;
    }

    public void setRadioactive(double radioactive){
        this.radioactive = radioactive;
    }

    public void setPoison(double poison){
        this.poison = poison;
    }

    public void setOvereating(double overeating){
        this.overeating = overeating;
    }

    public void setCurse(double curse){
        this.curse = curse;
    }

    public void setHoly(double holy){
        this.holy = holy;
    }

    public void add(DamageComponent damageComponent){
        this.projectile += damageComponent.projectile;
        this.melee += damageComponent.melee;
        this.explosion += damageComponent.explosion;
        this.electricity += damageComponent.electricity;
        this.fire += damageComponent.fire;
        this.drill += damageComponent.drill;
        this.slice += damageComponent.slice;
        this.ice += damageComponent.ice;
        this.healing += damageComponent.healing;
        this.physics_hit += damageComponent.physics_hit;
        this.radioactive += damageComponent.radioactive;
        this.poison += damageComponent.poison;
        this.overeating += damageComponent.overeating;
        this.curse += damageComponent.curse;
        this.holy += damageComponent.holy;
    }

    public boolean zero(){
        if(this.projectile != 0){
            return false;
        }
        if(this.melee != 0){
            return false;
        }
        if(this.explosion != 0){
            return false;
        }
        if(this.electricity != 0){
            return false;
        }
        if(this.fire != 0){
            return false;
        }
        if(this.drill != 0){
            return false;
        }
        if(this.slice != 0){
            return false;
        }
        if(this.ice != 0){
            return false;
        }
        if(this.healing != 0){
            return false;
        }
        if(this.physics_hit != 0){
            return false;
        }
        if(this.radioactive != 0){
            return false;
        }
        if(this.poison != 0){
            return false;
        }
        if(this.overeating != 0){
            return false;
        }
        if(this.curse != 0){
            return false;
        }
        if(this.holy != 0){
            return false;
        }

        return true;
    }
}