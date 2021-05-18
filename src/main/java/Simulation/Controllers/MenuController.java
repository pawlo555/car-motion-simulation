package Simulation.Controllers;

import Simulation.SimulationApplication;
import Simulation.SimulationEngine;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.File;
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

    public void itemClicked() {
        System.out.println("Click");
        String clickedName = placesNames.getSelectionModel().getSelectedItem();
        setPlaceName(clickedName);
    }

    @FXML
    public void addCrossings() {
        File directory = new File("resources/Crossings");
        System.out.println(directory);
        if (directory.list() != null) {
            System.out.println(Arrays.toString(directory.list()));
            String[] list = Arrays.stream(Objects.requireNonNull(directory.list())).map(this::changeName).toArray(String[]::new);
            placesNames.getItems().addAll(list);
        }
    }

    public String changeName(String name) {
        name=name.substring(0,name.length()-4);
        name=name.replaceAll("(?<!_)(?=[A-Z])", " ");
        return name;
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
}
