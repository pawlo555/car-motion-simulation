package Simulation.Controllers;

import Simulation.SimulationApplication;
import Simulation.SimulationEngine;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class MenuController {

    @FXML private Label placeName;
    @FXML private ComboBox<String> placesNames;
    private boolean isStatistics = true;

    private SimulationEngine engine;
    private SimulationApplication application;

    public void stopPressed() {
        engine.stop();
    }

    public void startPressed() {
        engine.start();
    }

    public void doubleSpeedPressed() {
        engine.doubleSpeed();
    }

    public void loadStatisticsPressed() {
        if (!isStatistics) {
            application.loadStatistics();
            isStatistics = true;
        }
    }

    public void loadParametersPressed() {
        if (isStatistics) {
            application.loadParameters();
            isStatistics = false;
        }
    }

    public void setEngine(SimulationEngine newEngine) {
        engine = newEngine;
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
        File directory = new File("C:\\Studia\\ModelowanieSystemowDyskretnych\\Car-Motion-Simulation\\src\\main\\resources\\Crossings");
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

}
