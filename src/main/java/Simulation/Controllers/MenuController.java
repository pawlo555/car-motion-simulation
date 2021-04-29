package Simulation.Controllers;

import Simulation.SimulationApplication;
import Simulation.SimulationEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;

public class MenuController {

    @FXML private Label placeName;
    @FXML private SplitMenuButton placesNames;
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

}
