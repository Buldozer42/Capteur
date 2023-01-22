package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import model.*;

import java.io.IOException;

public class DetailZoneWindow extends Visualisateur{
    private CapteurZone capteurZone;
    private ObservableList<CapteurAbstrait> lesCapteurs;
    private GridPane layout;
    @FXML
    private Label nom;
    @FXML
    private Label id;
    @FXML
    private Label nbCapteurs;
    @FXML
    private Label temperature;
    @FXML
    private Button btnImage;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnVoir;
    @FXML
    private Button btnSupprimer;
    @FXML
    private ListView<CapteurAbstrait> listView;

    public DetailZoneWindow(CapteurZone capteurZone) throws IOException {
        this.capteurZone = capteurZone;
        Stage stage = new Stage();
        layout = new GridPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DetailZoneWindow.fxml"));
        loader.setRoot(this.layout);
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("this.capteur.getNom().toString()");
        stage.show();
    }
    @FXML
    public void initialize(){
        id.setText(new NumberStringConverter().toString(capteurZone.getId()));
        nom.textProperty().bindBidirectional(this.capteurZone.getNom());
        nbCapteurs.textProperty().bindBidirectional(this.capteurZone.getNbCapteurs(), new NumberStringConverter());
        temperature.textProperty().bindBidirectional(this.capteurZone.getTemperature() , new NumberStringConverter());

        lesCapteurs = listView.getItems();

        listView.setCellFactory(new Callback<ListView<CapteurAbstrait>, ListCell<CapteurAbstrait>>() {
            @Override
            public ListCell<CapteurAbstrait> call(ListView<CapteurAbstrait> capteurListView) {
                return new ListCell<CapteurAbstrait>(){
                    @Override
                    public void updateItem(CapteurAbstrait value, boolean empty) {
                        super.updateItem(value, empty);
                        if (value != null) {
                            textProperty().bind(new SimpleStringProperty(this, "", "")
                                    .concat("[")
                                    .concat(value.getId())
                                    .concat("] ")
                                    .concat(value.getNom())
                                    .concat(" : ")
                                    .concat(value.getTemperature().asString("%.2f°C")));
                        }
                    }
                };
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnVoir.setDisable(false);
            btnSupprimer.setDisable(false);
        });

        lesCapteurs.addAll(capteurZone.getLesCapteurs());
    }
    @FXML
    private void clickButtonImage() throws Exception{
        ImageWindow Iw = new ImageWindow(this.capteurZone);
    }

    @FXML
    private void clickButtonAjouter() throws Exception{
        Capteur c = new Capteur("Nouveau Capteur Réaliste", new GenerateurRealiste());
        this.capteurZone.addCapteur(c);
        lesCapteurs.add(c);
    }

    @FXML
    private void clickButtonSupprimer() throws Exception{
        CapteurAbstrait c = lesCapteurs.get(listView.getSelectionModel().getSelectedIndex());
        listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
        if (lesCapteurs.size() == 0) {
            btnVoir.setDisable(true);
            btnSupprimer.setDisable(true);
        }
        update();
    }
    @FXML
    private void clickButtonVoir() throws Exception{
        try {
            if (lesCapteurs.get(listView.getSelectionModel().getSelectedIndex()).getClass().getSimpleName().equals("CapteurZone")) {
                DetailZoneWindow Dzw = new DetailZoneWindow((CapteurZone) lesCapteurs.get(listView.getSelectionModel().getSelectedIndex()));
            }
            else{
                DetailWindow Dw = new DetailWindow((Capteur) lesCapteurs.get(listView.getSelectionModel().getSelectedIndex()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update() {
        lesCapteurs = listView.getItems();

        listView.setCellFactory(new Callback<ListView<CapteurAbstrait>, ListCell<CapteurAbstrait>>() {
            @Override
            public ListCell<CapteurAbstrait> call(ListView<CapteurAbstrait> capteurListView) {
                return new ListCell<CapteurAbstrait>(){
                    @Override
                    public void updateItem(CapteurAbstrait value, boolean empty) {
                        super.updateItem(value, empty);
                        if (value != null) {
                            textProperty().bind(new SimpleStringProperty(this, "", "")
                                    .concat("[")
                                    .concat(value.getId())
                                    .concat("] ")
                                    .concat(value.getNom())
                                    .concat(" : ")
                                    .concat(value.getTemperature().asString("%.2f°C")));
                        }
                    }
                };
            }
        });
    }
}
