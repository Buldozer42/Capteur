package model;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class CapteurZone extends CapteurAbstrait implements Runnable{
    private Thread thread;
    private ArrayList<Capteur> lesCapteurs;

    public CapteurZone(String nom, ArrayList<Capteur> lesCapteurs) {
        super(nom);
        this.temperature = new SimpleDoubleProperty(0);
        this.lesCapteurs = lesCapteurs;
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
        calculTemperature();
    }

    @Override
    public void run(){
        while (true) {
            Platform.runLater(() -> calculTemperature());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public DoubleProperty getTemperature() {
        return this.temperature;
    }
    private void calculTemperature(){
        double temperature = 0;
        for (Capteur capteur : lesCapteurs){
            temperature += capteur.getTemperature().get();
        }
        this.temperature.set(temperature/lesCapteurs.size());
    }
//    private void genereTemperature(){
//        for (Capteur capteur : lesCapteurs){
//            capteur.setTemperature(capteur.getStrategy().genereTemperature());
//        }
//        calculTemperature();
//        this.notifier();
//    }
    public void addCapteur(Capteur capteur){
        lesCapteurs.add(capteur);
        calculTemperature();
    }
    public void removeCapteur(Capteur capteur){
        lesCapteurs.remove(capteur);
        calculTemperature();
    }
    public ArrayList<Capteur> getLesCapteurs() {
        return lesCapteurs;
    }
    public void setLesCapteurs(ArrayList<Capteur> lesCapteurs) {
        this.lesCapteurs = lesCapteurs;
        calculTemperature();
    }
    public IntegerProperty getNbCapteurs(){
        return new SimpleIntegerProperty(lesCapteurs.size());
    }
}
