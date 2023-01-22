package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainWindow extends Visualisateur{
    @FXML
    private ListView<CapteurAbstrait> listView;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnAjouterZone;
    @FXML
    private Button btnVoir;
    @FXML
    private Button btnSupprimer;
    private ObservableList<CapteurAbstrait> lesCapteurs;
    private final DecimalFormat df = new DecimalFormat("#.##");

    @FXML
    public void initialize(){
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

        Capteur capteur1 = new Capteur("Capteur Réaliste", new GenerateurRealiste());
        Capteur capteur2 = new Capteur("Capteur Aléatoire", new GenerateurAleatoire(-20, 40));
        Capteur capteur3 = new Capteur("Capteur Manuel", new GenerateurManuel());
        lesCapteurs.add(capteur1);
        lesCapteurs.add(capteur2);
        lesCapteurs.add(capteur3);

        Capteur capteur4 = new Capteur("Capteur4", new GenerateurRealiste());
        Capteur capteur5 = new Capteur("Capteur5", new GenerateurRealiste());
        CapteurZone capteurZone = new CapteurZone("Capteur Zone", new ArrayList<Capteur>(){{add(capteur4); add(capteur5);}});
        lesCapteurs.add(capteurZone);
    }

    @FXML
    private void clickButtonAjouter() throws Exception{
        Capteur c = new Capteur("Nouveau Capteur Réaliste", new GenerateurRealiste());
        lesCapteurs.add(c);
    }
    @FXML
    private void clickButtonAjouterZone() throws Exception{
        CapteurZone c = new CapteurZone("Nouveau Capteur Zone", new ArrayList<Capteur>());
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
