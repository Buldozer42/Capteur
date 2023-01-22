package view;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.Capteur;
import model.Observateur;

public abstract class Visualisateur implements Observateur {
    @FXML
    private Button btnFermer;
    @FXML
    protected void clickButtonFermer(){
        Stage stage = (Stage) btnFermer.getScene().getWindow();
        stage.close();
    }
    private void intialize (Capteur c){
        c.addObservateur(this);
    }
}
