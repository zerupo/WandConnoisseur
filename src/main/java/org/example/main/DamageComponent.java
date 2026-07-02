package org.example.main;

public class DamageComponent{
    public enum Type{PROJECTILE, MELEE, EXPLOSION, ELECTRICITY, FIRE, DRILL, SLICE, ICE, HEALING, PHYSICS_HIT, RADIOACTIVE, POISON, OVEREATING, CURSE, HOLY}
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

    public DamageComponent clone(){
        DamageComponent damageComponent = new DamageComponent();

        damageComponent.projectile = this.projectile;
        damageComponent.melee = this.melee;
        damageComponent.explosion = this.explosion;
        damageComponent.electricity = this.electricity;
        damageComponent.fire = this.fire;
        damageComponent.drill = this.drill;
        damageComponent.slice = this.slice;
        damageComponent.ice = this.ice;
        damageComponent.healing = this.healing;
        damageComponent.physics_hit = this.physics_hit;
        damageComponent.radioactive = this.radioactive;
        damageComponent.poison = this.poison;
        damageComponent.overeating = this.overeating;
        damageComponent.curse = this.curse;
        damageComponent.holy = this.holy;

        return damageComponent;
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

    public double getTotal(){
        return this.projectile + this.melee + this.explosion + this.electricity + this.fire + this.drill + this.slice + this.ice + this.healing + this.physics_hit + this.radioactive + this.poison + this.overeating + this.curse + this.holy;
    }

    public double getDamage(Type damageType){
        return switch(damageType){
            case PROJECTILE -> this.projectile;
            case MELEE -> this.melee;
            case EXPLOSION -> this.explosion;
            case ELECTRICITY -> this.electricity;
            case FIRE -> this.fire;
            case DRILL -> this.drill;
            case SLICE -> this.slice;
            case ICE -> this.ice;
            case HEALING -> this.healing;
            case PHYSICS_HIT -> this.physics_hit;
            case RADIOACTIVE -> this.radioactive;
            case POISON -> this.poison;
            case OVEREATING -> this.overeating;
            case CURSE -> this.curse;
            case HOLY -> this.holy;
        };
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

    public void setDamage(double damage, Type damageType){
        switch(damageType){
            case PROJECTILE -> this.projectile = damage;
            case MELEE -> this.melee = damage;
            case EXPLOSION -> this.explosion = damage;
            case ELECTRICITY -> this.electricity = damage;
            case FIRE -> this.fire = damage;
            case DRILL -> this.drill = damage;
            case SLICE -> this.slice = damage;
            case ICE -> this.ice = damage;
            case HEALING -> this.healing = damage;
            case PHYSICS_HIT -> this.physics_hit = damage;
            case RADIOACTIVE -> this.radioactive = damage;
            case POISON -> this.poison = damage;
            case OVEREATING -> this.overeating = damage;
            case CURSE -> this.curse = damage;
            case HOLY -> this.holy = damage;
        }
    }

    public void setDamage(DamageComponent damageComponent){
        this.projectile = damageComponent.projectile;
        this.melee = damageComponent.melee;
        this.explosion = damageComponent.explosion;
        this.electricity = damageComponent.electricity;
        this.fire = damageComponent.fire;
        this.drill = damageComponent.drill;
        this.slice = damageComponent.slice;
        this.ice = damageComponent.ice;
        this.healing = damageComponent.healing;
        this.physics_hit = damageComponent.physics_hit;
        this.radioactive = damageComponent.radioactive;
        this.poison = damageComponent.poison;
        this.overeating = damageComponent.overeating;
        this.curse = damageComponent.curse;
        this.holy = damageComponent.holy;
    }

    public void add(double damage, Type damageType){
        switch(damageType){
            case PROJECTILE -> this.projectile += damage;
            case MELEE -> this.melee += damage;
            case EXPLOSION -> this.explosion += damage;
            case ELECTRICITY -> this.electricity += damage;
            case FIRE -> this.fire += damage;
            case DRILL -> this.drill += damage;
            case SLICE -> this.slice += damage;
            case ICE -> this.ice += damage;
            case HEALING -> this.healing += damage;
            case PHYSICS_HIT -> this.physics_hit += damage;
            case RADIOACTIVE -> this.radioactive += damage;
            case POISON -> this.poison += damage;
            case OVEREATING -> this.overeating += damage;
            case CURSE -> this.curse += damage;
            case HOLY -> this.holy += damage;
        }
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

    public int nbType(){
        int result = 0;

        if(this.projectile != 0){
            result++;
        }
        if(this.melee != 0){
            result++;
        }
        if(this.explosion != 0){
            result++;
        }
        if(this.electricity != 0){
            result++;
        }
        if(this.fire != 0){
            result++;
        }
        if(this.drill != 0){
            result++;
        }
        if(this.slice != 0){
            result++;
        }
        if(this.ice != 0){
            result++;
        }
        if(this.healing != 0){
            result++;
        }
        if(this.physics_hit != 0){
            result++;
        }
        if(this.radioactive != 0){
            result++;
        }
        if(this.poison != 0){
            result++;
        }
        if(this.overeating != 0){
            result++;
        }
        if(this.curse != 0){
            result++;
        }
        if(this.holy != 0){
            result++;
        }

        return result;
    }

    public String toString(){
        switch(this.nbType()){
            case 0 ->{
                return "0";
            }
            case 1 -> {
                if(this.projectile != 0){
                    return "projectile " + this.projectile;
                }
                if(this.melee != 0){
                    return "melee " + this.melee;
                }
                if(this.explosion != 0){
                    return "explosion " + this.explosion;
                }
                if(this.electricity != 0){
                    return "electricity " + this.electricity;
                }
                if(this.fire != 0){
                    return "fire " + this.fire;
                }
                if(this.drill != 0){
                    return "drill " + this.drill;
                }
                if(this.slice != 0){
                    return "slice " + this.slice;
                }
                if(this.ice != 0){
                    return "ice " + this.ice;
                }
                if(this.healing != 0){
                    return "healing " + this.healing;
                }
                if(this.physics_hit != 0){
                    return "physics_hit " + this.physics_hit;
                }
                if(this.radioactive != 0){
                    return "radioactive " + this.radioactive;
                }
                if(this.poison != 0){
                    return "poison " + this.poison;
                }
                if(this.overeating != 0){
                    return "overeating " + this.overeating;
                }
                if(this.curse != 0){
                    return "curse " + this.curse;
                }
                return "holy " + this.holy;
            }
            default -> {
                StringBuilder result = new StringBuilder();

                if(this.projectile != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("projectile: ").append(this.projectile);
                }
                if(this.melee != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("melee: ").append(this.melee);
                }
                if(this.explosion != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("explosion: ").append(this.explosion);
                }
                if(this.electricity != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("electricity: ").append(this.electricity);
                }
                if(this.fire != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("fire: ").append(this.fire);
                }
                if(this.drill != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("drill: ").append(this.drill);
                }
                if(this.slice != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("slice: ").append(this.slice);
                }
                if(this.ice != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("ice: ").append(this.ice);
                }
                if(this.healing != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("healing: ").append(this.healing);
                }
                if(this.physics_hit != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("physics_hit: ").append(this.physics_hit);
                }
                if(this.radioactive != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("radioactive: ").append(this.radioactive);
                }
                if(this.poison != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("poison: ").append(this.poison);
                }
                if(this.overeating != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("overeating: ").append(this.overeating);
                }
                if(this.curse != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("curse: ").append(this.curse);
                }
                if(this.holy != 0){
                    result.append(result.isEmpty() ? "" : ", ").append("holy: ").append(this.holy);
                }

                return "[" + result.toString() + "]";
            }
        }
    }
}