package org.example.main;

// https://noita.wiki.gg/wiki/Documentation:_VelocityComponent
public class VelocityComponent{
    private double gravityX = 0.0; // gravity_x
    private double gravityY = 400.0; // gravity_y
    private double mass = 0.05; // mass
    private double airFriction = 0.55; // air_friction
    private double terminalVelocity = 1000.0; // terminal_velocity
    private boolean applyTerminalVelocity = true; // apply_terminal_velocity
    private boolean updateVelocity = true; // updates_velocity
    private boolean displaceLiquid = true; // displace_liquid
    private boolean affectPhysicsBodies = false; // affect_physics_bodies
    private boolean limitToMaxVelocity = true; // limit_to_max_velocity
    private int liquidDeathThreshold = 0; // liquid_death_threshold
    private double liquidDrag = 1.0; // liquid_drag
    // mVelocity vec2 (0, 0)
    // mPrevVelocity vec2 (0, 0)
    // mLatestLiquidHitCount int 0
    // mAverageLiquidHitCount int 0
    // mPrevPosition ivec2 (0, 0)

    public VelocityComponent(double gravityX, double gravityY, double mass, double airFriction, double terminalVelocity, boolean applyTerminalVelocity, boolean updateVelocity, boolean displaceLiquid, boolean affectPhysicsBodies, boolean limitToMaxVelocity, int liquidDeathThreshold, double liquidDrag){
        this.gravityX = gravityX;
        this.gravityY = gravityY;
        this.mass = mass;
        this.airFriction = airFriction;
        this.terminalVelocity = terminalVelocity;
        this.applyTerminalVelocity = applyTerminalVelocity;
        this.updateVelocity = updateVelocity;
        this.displaceLiquid = displaceLiquid;
        this.affectPhysicsBodies = affectPhysicsBodies;
        this.limitToMaxVelocity = limitToMaxVelocity;
        this.liquidDeathThreshold = liquidDeathThreshold;
        this.liquidDrag = liquidDrag;
    }

    public VelocityComponent(double gravityY, double mass, double airFriction){
        this.gravityY = gravityY;
        this.mass = mass;
        this.airFriction = airFriction;
    }

    public VelocityComponent(){
        // empty
    }

    public VelocityComponent clone(){
        return new VelocityComponent(this.gravityX, this.gravityY, this.mass, this.airFriction, this.terminalVelocity, this.applyTerminalVelocity, this.updateVelocity, this.displaceLiquid, this.affectPhysicsBodies, this.limitToMaxVelocity, this.liquidDeathThreshold, this.liquidDrag);
    }

    // getters
    public double getGravityX(){
        return this.gravityX;
    }

    public double getGravityY(){
        return this.gravityY;
    }

    public double getMass(){
        return this.mass;
    }

    public double getAirFriction(){
        return this.airFriction;
    }

    public double getTerminalVelocity(){
        return this.terminalVelocity;
    }

    public boolean getApplyTerminalVelocity(){
        return this.applyTerminalVelocity;
    }

    public boolean getUpdateVelocity(){
        return this.updateVelocity;
    }

    public boolean getDisplaceLiquid(){
        return this.displaceLiquid;
    }

    public boolean getAffectPhysicsBodies(){
        return this.displaceLiquid;
    }

    public boolean getLimitToMaxVelocity(){
        return this.limitToMaxVelocity;
    }

    public int getLiquidDeathThreshold(){
        return this.liquidDeathThreshold;
    }

    public double getLiquidDrag(){
        return this.liquidDrag;
    }

    // setters
    public VelocityComponent setGravityX(double gravityX){
        this.gravityX = gravityX;
        return this;
    }

    public VelocityComponent setGravityY(double gravityY){
        this.gravityY = gravityY;
        return this;
    }

    public VelocityComponent setMass(double mass){
        this.mass = mass;
        return this;
    }

    public VelocityComponent setAirFriction(double airFriction){
        this.airFriction = airFriction;
        return this;
    }

    public VelocityComponent setTerminalVelocity(double terminalVelocity){
        this.terminalVelocity = terminalVelocity;
        return this;
    }

    public VelocityComponent setApplyTerminalVelocity(boolean applyTerminalVelocity){
        this.applyTerminalVelocity = applyTerminalVelocity;
        return this;
    }

    public VelocityComponent setUpdateVelocity(boolean updateVelocity){
        this.updateVelocity = updateVelocity;
        return this;
    }

    public VelocityComponent setDisplaceLiquid(boolean displaceLiquid){
        this.displaceLiquid = displaceLiquid;
        return this;
    }

    public VelocityComponent setAffectPhysicsBodies(boolean displaceLiquid){
        this.displaceLiquid = displaceLiquid;
        return this;
    }

    public VelocityComponent setLimitToMaxVelocity(boolean limitToMaxVelocity){
        this.limitToMaxVelocity = limitToMaxVelocity;
        return this;
    }

    public VelocityComponent setLiquidDeathThreshold(int liquidDeathThreshold){
        this.liquidDeathThreshold = liquidDeathThreshold;
        return this;
    }

    public VelocityComponent setLiquidDrag(double liquidDrag){
        this.liquidDrag = liquidDrag;
        return this;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();

        result.append(result.isEmpty() ? "" : "\n").append("Gravity x: ").append(this.getGravityX());
        result.append(result.isEmpty() ? "" : "\n").append("Gravity y: ").append(this.getGravityY());
        result.append(result.isEmpty() ? "" : "\n").append("Mass: ").append(this.getMass());
        if(applyTerminalVelocity){result.append(result.isEmpty() ? "" : "\n").append("Terminal velocity: ").append(this.getTerminalVelocity());}
        result.append(result.isEmpty() ? "" : "\n").append("Update velocity: ").append(this.getUpdateVelocity());
        result.append(result.isEmpty() ? "" : "\n").append("Displace liquid: ").append(this.getDisplaceLiquid());
        result.append(result.isEmpty() ? "" : "\n").append("Affect physics bodies: ").append(this.getAffectPhysicsBodies());
        result.append(result.isEmpty() ? "" : "\n").append("limit to max velocity: ").append(this.getLimitToMaxVelocity());
        result.append(result.isEmpty() ? "" : "\n").append("Liquid death threshold: ").append(this.getLiquidDeathThreshold());
        result.append(result.isEmpty() ? "" : "\n").append("Liquid drag: ").append(this.getLiquidDrag());

        return result.toString();
    }
}
