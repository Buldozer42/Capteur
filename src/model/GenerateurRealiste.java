package model;

import java.util.Random;

public class GenerateurRealiste implements GenerateurStrategy{
    private double bornInf;
    private double bornSup;
    private double temperature;

    public GenerateurRealiste() {
        Random random = new Random();
        this.bornInf = -20;
        this.bornSup = 40;
        this.temperature = bornInf+random.nextDouble(bornSup-bornInf);
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
        double inf, sup;
        inf = -2;
        sup = 2;
        this.temperature += inf+random.nextDouble(sup-inf);
        return this.temperature;
    }
}