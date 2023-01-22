package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import model.Capteur;
import model.GenerateurAleatoire;
import model.GenerateurManuel;
import model.GenerateurRealiste;

import java.io.IOException;

public class DetailWindow extends Visualisateur{
    @FXML
    private Label nom;
    @FXML
    private Label id;
    @FXML
    private Label temperature;
    @FXML
    private Label strategy;
    @FXML
    private Button btnSpinner;
    @FXML
    private Button btnImage;
    @FXML
    private Button btnModifier;
    @FXML
    private TextField textFieldNom;
    @FXML
    private MenuButton menuStrategy;
    @FXML
    private MenuItem menuStrategyRandom;
    protected Capteur capteur;
    private GridPane layout;

    public DetailWindow(Capteur capteur) throws IOException {
        this.capteur = capteur;
        Stage stage = new Stage();
        layout = new GridPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DetailWindow.fxml"));
        loader.setRoot(this.layout);
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(this.capteur.getNom().toString());
        stage.show();
    }
    @FXML
    public void initialize(){
        id.setText(new NumberStringConverter().toString(capteur.getId()));
        nom.textProperty().bindBidirectional(this.capteur.getNom());
        String strategyName = this.capteur.getStrategy().getClass().getSimpleName();
        strategy.setText(strategyName);
        if (! strategyName.equals("GenerateurManuel")){
            btnSpinner.setDisable(true);
        }
        temperature.textProperty().bindBidirectional(this.capteur.getTemperature() , new NumberStringConverter());
    }

    @FXML
    private void clickButtonImage() throws Exception{
        ImageWindow Iw = new ImageWindow(this.capteur);
    }
    @FXML
    private void clickButtonSpinner() throws Exception{
        SpinnerWindow Sw = new SpinnerWindow(this.capteur);
    }
    @FXML
    private void clickButtonModifier() throws Exception{
        if (nom.isVisible() && strategy.isVisible()) {
            textFieldNom.setText(nom.getText());
            textFieldNom.setVisible(true);
            nom.setVisible(false);
            menuStrategy.setText(strategy.getText());
            menuStrategy.setVisible(true);
        } else {
            nom.setText(textFieldNom.getText());
            String strategyName = menuStrategy.getText();
            if (strategyName.equals("GenerateurAleatoire")){
                this.capteur.setStrategy(new GenerateurAleatoire(-20,40));
                strategy.setText("GenerateurAleatoire");
            } else if (strategyName.equals("GenerateurRealiste")){
                this.capteur.setStrategy(new GenerateurRealiste());
                strategy.setText("GenerateurRealiste");
            }
            else if (strategyName.equals("GenerateurManuel")){
                this.capteur.setStrategy(new GenerateurManuel());
                strategy.setText("GenerateurManuel");
                btnSpinner.setDisable(false);
            }
            textFieldNom.setVisible(false);
            menuStrategy.setVisible(false);
            nom.setVisible(true);
        }
    }
    @FXML
    private void clickMenuStrategyRandom() throws Exception{
        menuStrategy.setText("GenerateurAleatoire");
    }
    @FXML
    private void clickMenuStrategyReal() throws Exception{
        menuStrategy.setText("GenerateurRealiste");
    }
    @FXML
    private void clickMenuStrategyMan() throws Exception{
        menuStrategy.setText("GenerateurManuel");
    }

    @Override
    public void update() {
        temperature.textProperty().bindBidirectional(this.capteur.getTemperature() , new NumberStringConverter());
    }
}
