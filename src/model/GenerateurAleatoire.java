package model;

import java.util.Random;

public class GenerateurAleatoire implements GenerateurStrategy{
    private double bornInf;
    private double bornSup;

    public GenerateurAleatoire(double bornInf, double bornSup) {
        this.bornInf = bornInf;
        this.bornSup = bornSup;
    }

    public GenerateurAleatoire(){
        this.bornInf = Double.MIN_VALUE;
        this.bornSup = Double.MAX_VALUE;
    }

    public double getBornInf() {
        return bornInf;
    }

    public void setBornInf(double bornInf) {
        this.bornInf = bornInf;
    }

    public double getBornSup() {
        return bornSup;
    }

    public void setBornSup(double bornSup) {
        this.bornSup = bornSup;
    }

    @Override
    public double genereTemperature() {
        Random random = new Random();
        double temperature;
        temperature = bornInf+random.nextDouble(bornSup-bornInf);
        return temperature;
    }
}
