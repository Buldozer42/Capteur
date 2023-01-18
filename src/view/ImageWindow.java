package view;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import model.Capteur;
import model.GenerateurAleatoire;
import java.io.IOException;

public class ImageWindow extends Visualisateur{
    private Capteur capteur;
    private GridPane layout;
    @FXML
    private Label temperature;

    public ImageWindow(Capteur capteur) throws IOException {
        this.capteur = capteur;
        Stage stage = new Stage();
        layout = new GridPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ImageWindow.fxml"));
        loader.setRoot(this.layout);
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Image");
        stage.show();
    }

    @FXML
    public void initialize(){
        temperature.textProperty().bindBidirectional(this.capteur.getTemperature(), new NumberStringConverter());
    }

    @Override
    public void update() {
        temperature.textProperty().bindBidirectional(this.capteur.getTemperature(), new NumberStringConverter());
    }
}
