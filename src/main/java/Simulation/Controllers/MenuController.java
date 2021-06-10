package Simulation.Controllers;

import Simulation.SimulationApplication;
import Simulation.SimulationEngine;
import Utilities.CrossingsMap;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class MenuController {

    @FXML private Label placeName;
    @FXML private ComboBox<String> placesNames;
    @FXML private Label currentEpochLabel;
    private boolean isStatistics = false;
    private Timeline timeline;

    private SimulationEngine engine;
    private SimulationApplication application;
    private ParametersController parametersController;
    private MapController mapController;
    private StatisticsController statisticsController;

    public void stopPressed() {
        timeline.pause();
    }

    public void startPressed() {
        changeSpeed(1000);
        timeline.play();
    }

    public void doubleSpeedPressed() {
        changeSpeed(500);
        timeline.play();
    }

    public void loadStatisticsPressed() {
        if (!isStatistics) {
            application.showStatistics();
            isStatistics = true;
        }
    }

    public void loadParametersPressed() {
        if (isStatistics) {
            application.showParameters();
            isStatistics = false;
        }
    }

    public void setEngine(SimulationEngine newEngine) {
        engine = newEngine;
        setTimeline();
    }

    public void setApplication(SimulationApplication newApplication) {
        application = newApplication;
    }

    private void setPlaceName(String newPlace) {
        placeName.setText(newPlace);
    }

    public void itemClicked() throws IOException, ParseException {
        System.out.println("Click");
        String clickedName = placesNames.getSelectionModel().getSelectedItem();
        parametersController.addEntrances(clickedName);
        CrossingsMap map = new CrossingsMap("src/main/resources/Utilities/Crossings");
        mapController.crossingSet(map.getCrossingFile(clickedName));
        setPlaceName(clickedName);
    }

    public void addCrossings() throws IOException, ParseException {
        CrossingsMap map = new CrossingsMap("src/main/resources/Utilities/Crossings");
        System.out.println("Crossings:");
        System.out.println(map.getCrossingNames());
        placesNames.getItems().addAll(map.getCrossingNames());
    }

    private void changeSpeed(int milliSeconds) {
        timeline.stop();
        timeline.getKeyFrames().setAll(getAppropriateKeyFrame(milliSeconds));
    }

    public void setTimeline() {
        this.timeline = new Timeline(getAppropriateKeyFrame(1000));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    private KeyFrame getAppropriateKeyFrame(int milliSeconds) {
        if (engine == null) {
            throw new RuntimeException("Lack of engines!");
        }
        else {
            return new KeyFrame(
                    Duration.millis(milliSeconds),
                    ae ->  {
                        engine.nextEpoch();
                        updateEpochLabel();
                    }
                    );
        }
    }

    private void updateEpochLabel() {
        int epoch = Integer.parseInt(currentEpochLabel.getText());
        epoch = epoch + 1;
        currentEpochLabel.setText(String.valueOf(epoch));
    }

    public void setParametersController(ParametersController controller) {
        parametersController = controller;
    }

    public void setMapController(MapController controller) {
        mapController = controller;
    }


    public void setStatisticsController(StatisticsController statisticsController) {
        this.statisticsController = statisticsController;
    }
}
