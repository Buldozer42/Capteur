package view;

import model.Capteur;
import model.Observateur;

public class AfficheurTemperature implements Observateur {
    private String nom;
    private Capteur capteur;

    public AfficheurTemperature(String nom, Capteur capteur) {
        this.nom = nom;
        this.capteur = capteur;
    }

    @Override
    public void update() {
        System.out.println("["+this.nom+"] "+this.capteur.getNom() + " : " + this.capteur.getTemperature() + " °C");
    }
}
