package model;

import java.util.List;

public abstract class Observable {
    public List<Observateur> lesObservateurs;
    public void addObservateur(Observateur observateur){
        this.lesObservateurs.add(observateur);
    }
    public void deleteObservateur(Observateur observateur){
        this.lesObservateurs.remove(observateur);
    }
    public void notifier(){
        for (Observateur observateur : lesObservateurs){
            observateur.update();
        }
    }
}
