package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import model.Capteur;

import java.io.IOException;

public class SpinnerWindow extends Visualisateur{
    @FXML
    private Spinner spinner;
    protected Capteur capteur;
    private GridPane layout;

    public SpinnerWindow(Capteur capteur) throws IOException {
        this.capteur = capteur;
        Stage stage = new Stage();
        layout = new GridPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SpinnerWindow.fxml"));
        loader.setRoot(this.layout);
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Spinner");
        stage.show();
    }
    @FXML
    public void initialize(){
        spinner.getValueFactory().valueProperty().bindBidirectional(this.capteur.getTemperature());
    }

    @Override
    public void update() {
        spinner.getValueFactory().valueProperty().bindBidirectional(this.capteur.getTemperature());
    }
}
