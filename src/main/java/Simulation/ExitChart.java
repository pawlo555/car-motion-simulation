package Simulation;

import Utilities.CellObserver;
import Utilities.ExitsManager;
import Utilities.SimulationObserver;
import Vehicles.AbstractVehicle;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.LinkedList;

public class ExitChart extends LineChart<Number, Number> {
    private final Series<Number, Number> series = new Series<>();
    ExitsManager manager = new ExitsManager();
    String currentExit;

    private static final int majorTick = 10;
    private static int maxFlow = 100;

    private static final NumberAxis xAxis = new NumberAxis("Epoch",0,100, majorTick);
    private static final NumberAxis yAxis = new NumberAxis("Car flow",0, maxFlow, (int) Math.log(maxFlow));

    public ExitChart(Axis<Number> axis, Axis<Number> axis1) throws IOException, ParseException {
        super(axis, axis1);
    }

    public void changeExit(String newExitName) {
        currentExit = newExitName;
    }
}
