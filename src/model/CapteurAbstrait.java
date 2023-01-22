package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public abstract class CapteurAbstrait extends Observable {
    protected static int idActuel;
    protected int id;
    protected StringProperty nom;
    protected DoubleProperty temperature;

    public CapteurAbstrait(String nom) {
        this.id = idActuel;
        idActuel += 1;
        this.nom = new SimpleStringProperty(nom);
        this.lesObservateurs = new ArrayList<>();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StringProperty getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = new SimpleStringProperty(nom);
        this.notifier();
    }
    public abstract DoubleProperty getTemperature();
}
