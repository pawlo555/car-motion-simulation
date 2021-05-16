package Simulation;

import Utilities.CellObserver;
import Utilities.SimulationObserver;
import Vehicles.AbstractVehicle;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.LinkedList;

public class ExitChartDrawer extends LineChart implements CellObserver, SimulationObserver {

    private LinkedList<Integer> carsFlow = new LinkedList<>();
    private static final int majorTick = 10;
    private static int maxFlow = 100;

    static final NumberAxis xAxis = new NumberAxis(0,100, majorTick);
    static final NumberAxis yAxis = new NumberAxis(0, maxFlow, (int) Math.log(maxFlow));

    public ExitChartDrawer() {
        super(xAxis, yAxis);
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

    // TODO update this
    private void updateXAxis() {
        this.getData().add(new XYChart.Data(carsFlow.size()-1,carsFlow.getLast()));
        carsFlow.addLast(0);

    }
}
