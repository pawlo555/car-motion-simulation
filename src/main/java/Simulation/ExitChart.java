package Simulation;

import Utilities.CellObserver;
import Utilities.SimulationObserver;
import Vehicles.AbstractVehicle;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.LinkedList;

public class ExitChart extends LineChart<Number, Number> implements CellObserver, SimulationObserver {

    private final LinkedList<Integer> carsFlow = new LinkedList<>();
    private final Series<Number, Number> series = new Series<>();
    private int currentEpoch = 100;

    private static final int majorTick = 10;
    private static int maxFlow = 100;

    private static final NumberAxis xAxis = new NumberAxis("Epoch",0,100, majorTick);
    private static final NumberAxis yAxis = new NumberAxis("Car flow",0, maxFlow, (int) Math.log(maxFlow));

    public ExitChart() {
        super(xAxis, yAxis);
        this.getData().add(series);
        carsFlow.addLast(0);
    }

    @Override
    public void carMoveThrough(AbstractVehicle vehicle) {
        int currentFlow = carsFlow.pollLast();
        carsFlow.add(currentFlow+1);
        updateYAxis(currentFlow+1);
    }

    @Override
    public void nextEpoch() {
        carsFlow.add(0);
        updateXAxis();
    }

    private void updateYAxis(int currentFlow) {
        if (maxFlow < currentFlow) {
            maxFlow = currentFlow;
        }
    }

    private void updateXAxis() {
        currentEpoch = currentEpoch + 1;

        if (currentEpoch > 100) {
            NumberAxis xAxis = (NumberAxis) this.getXAxis();
            xAxis.setLowerBound(currentEpoch-100);
            xAxis.setUpperBound(currentEpoch);
        }
        this.dataItemAdded(series, 0, new XYChart.Data<>(carsFlow.size(), carsFlow.getLast()));
        carsFlow.addLast(0);

    }
}
