package model;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class Capteur extends CapteurAbstrait  implements Runnable{
    private Thread thread;
    private GenerateurStrategy strategy;


    public Capteur(String nom, GenerateurStrategy strategy) {
        super(nom);
        this.temperature = new SimpleDoubleProperty(strategy.genereTemperature());
        this.strategy = strategy;
        if (strategy.getClass().getSimpleName().equals("GenerateurManuel")) {
            this.thread = null;
        }
        else{
            startThread();
        }
    }
    public void startThread(){
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }
    public void stopThread(){
        thread.interrupt();
    }

    public DoubleProperty getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature.set(temperature);
        this.notifier();
    }

    public GenerateurStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(GenerateurStrategy strategy) {
        this.strategy = strategy;
        if (thread != null){
            thread.interrupt();
        }
        if (strategy.getClass().getSimpleName().equals("GenerateurManuel")) {
            this.thread = null;
        }
        else{
            startThread();
        }
        this.notifier();
    }

    public void setGenerateur(GenerateurStrategy strategy){
        this.strategy = strategy;
    }

    @Override
    public void run(){
        while (true) {
            Platform.runLater(() -> setTemperature(this.strategy.genereTemperature()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
