package Simulation.Controllers;

import Utilities.CrossingParser;
import Utilities.EntrancesParametersManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import Utilities.CrossingsMap;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class ParametersController {

    @FXML private Slider carsPerMinute;
    @FXML private Slider busProbability;
    @FXML private Slider maxSpeed;

    @FXML private TextField carsText;
    @FXML private TextField probabilityText;
    @FXML private TextField speedText;

    @FXML private ComboBox<String> entrances;
    String selectedEntrance;
    private EntrancesParametersManager parametersManager;

    @FXML
    public void initialize() {
        try {
            parametersManager = new EntrancesParametersManager();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("EntrancesParametersManager cannot be initialize.");
        }
        System.out.println("Params initiation");
        addSlidersListeners();
    }

    private void addSlidersListeners() {
        busProbability.valueProperty().addListener((observableValue, number, t1) -> {
            parametersManager.changeBusProbability(selectedEntrance, (Double)t1);
            probabilityText.setText(String.valueOf(t1));
        });

        carsPerMinute.valueProperty().addListener((observableValue, number, t1) -> {
            parametersManager.changeFlow(selectedEntrance, (int) Math.round((Double)t1));
            carsText.setText(String.valueOf(t1));
        });

        maxSpeed.valueProperty().addListener((observableValue, number, t1) -> {
            parametersManager.changeMaxSpeed(selectedEntrance, (int) Math.round((Double)t1));
            speedText.setText(String.valueOf(t1));
        });
    }

    public void selectEntrance() {
        selectedEntrance = entrances.getSelectionModel().getSelectedItem();
        fillTextFields(selectedEntrance);
        setSliders(selectedEntrance);
        enableSliders();
    }

    private void fillTextFields(String entranceName) {
        probabilityText.setText(String.valueOf(parametersManager.getBusProbability(entranceName)));
        carsText.setText(String.valueOf(parametersManager.getFlow(entranceName)));
        speedText.setText(String.valueOf(parametersManager.getMaxSpeed(entranceName)));
    }

    private void setSliders(String entranceName) {
        carsPerMinute.setValue(parametersManager.getFlow(entranceName));
        maxSpeed.setValue(parametersManager.getMaxSpeed(entranceName));
        busProbability.setValue(parametersManager.getBusProbability(entranceName));
    }

    private void enableSliders() {
        carsPerMinute.setDisable(false);
        maxSpeed.setDisable(false);
        busProbability.setDisable(false);
    }



    public void addEntrances(String crossingName) throws IOException, ParseException {
        CrossingsMap map = new CrossingsMap("src/main/resources/Utilities/Crossings");
        String fileToParse = map.getCrossingFile(crossingName);
        CrossingParser parser = new CrossingParser(fileToParse);
        entrances.getItems().removeAll(entrances.getItems());
        entrances.getItems().addAll(parser.getEntrancesNames());
    }

    public void saveAllParameters() {

    }

    public void busProbabilitySet() {
        double newBusProbability = Double.parseDouble(probabilityText.getText());
        busProbability.setValue(newBusProbability);
        parametersManager.changeBusProbability(selectedEntrance, newBusProbability);
    }

    public void flowSet() {
        int newFlow = Integer.parseInt(carsText.getText());
        carsPerMinute.setValue(newFlow);
        parametersManager.changeBusProbability(selectedEntrance, newFlow);
    }

    public void maxSpeedSet() {
        int newMaxSpeed = Integer.parseInt(speedText.getText());
        maxSpeed.setValue(newMaxSpeed);
        parametersManager.changeMaxSpeed(selectedEntrance, newMaxSpeed);
    }

}
