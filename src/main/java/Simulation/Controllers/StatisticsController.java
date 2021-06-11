package Simulation.Controllers;

import Simulation.ExitChart;
import Utilities.CrossingParser;
import Utilities.ExitsManager;
import Utilities.SimulationObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StatisticsController {

    @FXML private ComboBox<String> exits;
    @FXML private ExitChart chart;

    public void selectExits() {
        chart.changeExit(exits.getSelectionModel().getSelectedItem());
    }

    public void setExitsManager(ExitsManager manager) {
        chart.setManager(manager);
    }

    public void setExits(String crossingPath) throws IOException, ParseException {
        Set<String> exitsNames = new CrossingParser(crossingPath).getExitsNames();
        List<String> list = new ArrayList<>(exitsNames);
        exits.setItems(FXCollections.observableList(list));
    }

    public SimulationObserver getChart() {
        return chart;
    }
}
