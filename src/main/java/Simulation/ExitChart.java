package Simulation;

import Utilities.ExitsManager;
import Utilities.SimulationObserver;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class ExitChart extends LineChart<Number, Number> implements SimulationObserver {
    private Series<Number, Number> series = new Series<>();
    ExitsManager manager;
    String currentExit = null;
    private int currentEpoch = 0;

    private static final int majorTick = 10;
    private static final int maxFlow = 100;
    private static final int epochOnChart = 100;

    private static final NumberAxis xAxis = new NumberAxis("Epoch",0,epochOnChart, majorTick);
    private static final NumberAxis yAxis = new NumberAxis("Car flow",0, maxFlow, (int) Math.log(maxFlow));

    public ExitChart() {
        super(xAxis, yAxis);
        this.getData().add(series);
        series.getData().add(new XYChart.Data<>(12, 25));
        series.getData().add(new XYChart.Data<>(23, 99));
        series.getData().add(new XYChart.Data<>(53, 54));
    }

    public void changeExit(String newExitName) {
        System.out.println("Changing exit");
        currentExit = newExitName;
        setFlowData();
    }

    public void setManager(ExitsManager manager) {
        this.manager = manager;
    }

    public void setFlowData() {
        if (currentExit != null) {
            System.out.println("Setting data");
            this.getData().remove(series);
            //List<Integer> dataList = manager.getLastNEpoch(epochOnChart, currentExit);
            List<Integer> dataList = new ArrayList<>();

            series.getData().add(new XYChart.Data<>(10, 10));
            System.out.println(dataList);
            //series = new Series<>();
            int i = Math.max(currentEpoch - epochOnChart, 0);
            for (Integer flow : dataList) {
                if (i < currentEpoch) {
                    break;
                }
                series.getData().add(new XYChart.Data<>(i, flow));
                i++;
            }
            //this.getData().add(series);
        }
    }

    @Override
    public void nextEpoch() {
        currentEpoch = currentEpoch+1;
        setFlowData();
    }
}
