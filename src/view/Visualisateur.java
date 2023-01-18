package view;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Capteur;
import model.Observateur;

public abstract class Visualisateur implements Observateur {
    private void clickFermer(){
//        Stage stage = (Stage) .getScene().getWindow();
//        stage.close();
    }
    private void intialize (Capteur c){
        c.addObservateur(this);
    }
}
