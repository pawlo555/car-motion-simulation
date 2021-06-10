package Simulation;

import Utilities.ExitsManager;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class ExitChart extends LineChart<Number, Number> {
    private final Series<Number, Number> series = new Series<>();
    ExitsManager manager;
    String currentExit;

    private static final int majorTick = 10;
    private static int maxFlow = 100;

    private static final NumberAxis xAxis = new NumberAxis("Epoch",0,100, majorTick);
    private static final NumberAxis yAxis = new NumberAxis("Car flow",0, maxFlow, (int) Math.log(maxFlow));

    public ExitChart() {
        super(xAxis, yAxis);
    }

    public void changeExit(String newExitName) {
        currentExit = newExitName;
    }

    public void setManager(ExitsManager manager) {
        this.manager = manager;
    }
}
