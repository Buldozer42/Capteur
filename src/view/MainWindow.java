package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Capteur;
import model.GenerateurAleatoire;

public class MainWindow {
    @FXML
    private Button btnSpinner;
    @FXML
    private Button btnImage;
    @FXML
    private Button btnFermer;
    protected Capteur capteur;

    public void initialize(){
        this.capteur = new Capteur("a", new GenerateurAleatoire(0,12));
    }
    @FXML
    private void clickButtonFermer(){
        Stage stage = (Stage) btnFermer.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickButtonImage() throws Exception{
        ImageWindow Iw = new ImageWindow(this.capteur);
    }
    @FXML
    private void clickButtonSpinner() throws Exception{
        SpinnerWindow Sw = new SpinnerWindow(this.capteur);
    }
}
