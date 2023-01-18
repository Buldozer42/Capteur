package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class Capteur extends Observable  implements Runnable{
    private static int idActuel;
    private int id;
    private String nom;
    private DoubleProperty temperature;
//    private Thread thread;
    private GenerateurStrategy strategy;


    public Capteur(String nom, GenerateurStrategy strategy) {
        this.id = idActuel;
        idActuel += 1;
        this.nom = nom;
        this.temperature = new SimpleDoubleProperty(strategy.genereTemperature());
        this.lesObservateurs = new ArrayList<>();
        this.strategy = strategy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        this.notifier();
    }

    public DoubleProperty getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = new SimpleDoubleProperty(temperature);
        this.notifier();
    }

    public GenerateurStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(GenerateurStrategy strategy) {
        this.strategy = strategy;
    }

    public void setGenerateur(GenerateurStrategy strategy){
        this.strategy = strategy;
    }

    @Override
    public void run(){}

}
