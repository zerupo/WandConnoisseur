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

    public SpawnProbabilities clone(){
        SpawnProbabilities spawnProbabilities = new SpawnProbabilities();

        System.arraycopy(this.spawnProbability, 0, spawnProbabilities.spawnProbability, 0, spawnProbabilities.spawnProbability.length);

        return spawnProbabilities;
    }

    public double[] getSpawnProbability(){
        return this.spawnProbability;
    }

    public int nbTier(){
        int total = 0;

        for(double value : this.spawnProbability){
            if(value != 0.0){
                total++;
            }
        }

        return total;
    }

    public String toString(boolean includeAll){
        if(includeAll){
            StringBuilder result = new StringBuilder("[");

            if(this.spawnProbability.length > 0){
                result.append("T0: ").append(String.format("%1$2.1f", this.spawnProbability[0]).replace(',', '.'));
            }
            for(int i=1; i < this.spawnProbability.length; i++){
                result.append("; T").append(i).append(": ").append(String.format("%1$2.1f", this.spawnProbability[i]));
            }

            return result + "]";
        }else{
            switch(this.nbTier()){
                case 0 -> {
                    return "0";
                }
                case 1 -> {
                    for(int i=0; i < this.spawnProbability.length; i++){
                        if(this.spawnProbability[i] != 0.0){
                            return "T" + i + ": " + String.format("%1$2.1f", this.spawnProbability[i]).replace(',', '.');
                        }
                    }
                }
                default -> {
                    StringBuilder result = new StringBuilder();

                    for(int i=0; i < this.spawnProbability.length; i++){
                        if(this.spawnProbability[i] != 0.0){
                            if(!result.isEmpty()){
                                result.append(", ");
                            }
                            result.append("T").append(i).append(": ").append(String.format("%1$2.1f", this.spawnProbability[i]).replace(',', '.'));
                        }
                    }

                    return "[" + result + "]";
                }
            }

            return "";
        }
    }

    public double total(){
        double result = 0.0;

        for(double value : this.spawnProbability){
            result += value;
        }

        return result;
    }
}