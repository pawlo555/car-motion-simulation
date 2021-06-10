package Simulation.Controllers;

import Simulation.ExitChart;
import Utilities.ExitsManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class StatisticsController {

    @FXML private ComboBox exits;
    @FXML private final ExitChart chart = new ExitChart();

    public void selectExits() {}

    public void setExitsManager(ExitsManager manager) {
        chart.setManager(manager);
    }
}
