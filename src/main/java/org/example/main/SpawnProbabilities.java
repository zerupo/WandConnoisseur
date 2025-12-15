package org.example.main;

public class SpawnProbabilities{
    private double[] spawnProbability;

    public SpawnProbabilities(double P0, double P1, double P2, double P3, double P4, double P5, double P6, double P7, double P8, double P9, double P10){
        this.spawnProbability = new double[]{P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10};
        for(int i=0; i < this.spawnProbability.length; i++){
            if(this.spawnProbability[i] < 0.0){
                this.spawnProbability[i] = 0.0;
            }
        }
    }

    public SpawnProbabilities(){
        this.spawnProbability = new double[11];
    }

    public SpawnProbabilities(SpawnProbabilities original){
        this.spawnProbability = new double[original.spawnProbability.length];

        for(int i=0; i < this.spawnProbability.length; i++){
            this.spawnProbability[i] = original.spawnProbability[i];
        }
    }

    public double[] getSpawnProbability(){
        return this.spawnProbability;
    }
}