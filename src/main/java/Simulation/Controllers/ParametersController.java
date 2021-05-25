package Simulation.Controllers;

import Utilities.CrossingParser;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import Utilities.CrossingsMap;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class ParametersController {

    @FXML private Slider carsPerMinute;
    @FXML private Slider busProbability;
    @FXML private Slider maxSpeed;

    @FXML private TextField carsText;
    @FXML private TextField probabilityText;
    @FXML private TextField speedText;

    @FXML private ComboBox<String> entrances;

    private CrossingParser parser;

    public void selectEntrance() {
        if (parser != null) {
            String entrance = entrances.getSelectionModel().getSelectedItem();
            carsText.setText(Integer.toString(parser.getFlow(entrance)));
            probabilityText.setText(Double.toString(parser.getBusProbability(entrance)));
            speedText.setText(Integer.toString(parser.getMaxSpeed(entrance)));
        }
    }

    public void addEntrances(String crossingName) throws IOException, ParseException {
        CrossingsMap map = new CrossingsMap("src/main/resources/Utilities/Crossings");
        String fileToParse = map.getCrossingFile(crossingName);
        parser = new CrossingParser(fileToParse);
        entrances.getItems().removeAll(entrances.getItems());
        entrances.getItems().addAll(parser.getEntrancesNames());
    }

    public void getFlow() {

    }

    public void getBusProbability() {

    }

    public void getEntrances() {

    }
}
