package view;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import model.Capteur;
import model.CapteurAbstrait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageWindow extends Visualisateur{
    private final CapteurAbstrait capteur;
    @FXML
    private Label temperature;
    @FXML
    private Label nom;
    @FXML
    private Label id;
    @FXML
    private ImageView imageView;
    public ImageWindow(CapteurAbstrait capteur) throws IOException {
        this.capteur = capteur;
        Stage stage = new Stage();
        GridPane layout = new GridPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ImageWindow.fxml"));
        loader.setRoot(layout);
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Image");
        stage.show();
    }
    private void initializeAndUpdate() {
        id.setText(new NumberStringConverter().toString(capteur.getId()));
        nom.textProperty().bind(this.capteur.getNom());
        temperature.textProperty().bindBidirectional(this.capteur.getTemperature() , new NumberStringConverter());
        this.capteur.getTemperature().addListener((observable, oldValue, newValue) -> {
            try {
                if ((Double)newValue < 0) {
                    imageView.setImage(new Image(new FileInputStream("resource/Image/Froid.png")));
                } else if ((Double)newValue > 22) {
                    imageView.setImage(new Image(new FileInputStream("resource/Image/Chaud.png")));
                } else {
                    imageView.setImage(new Image(new FileInputStream("resource/Image/Normal.png")));
                }
            }
            catch (FileNotFoundException ignored){}
        });
    }
    @FXML
    public void initialize(){
        initializeAndUpdate();
    }

    @Override
    public void update() {
        initializeAndUpdate();
    }
}
