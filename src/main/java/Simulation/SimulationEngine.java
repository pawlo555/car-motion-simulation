package Simulation;

import Utilities.SimulationObserver;
import Utilities.Vector2D;
import Vehicles.AbstractVehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimulationEngine {

    private final ArrayList<SimulationObserver> simulationObservers = new ArrayList<>();
    private final HashMap<Vector2D, AbstractVehicle> vehicles = new HashMap<>();
    private int currentEpoch = 0;

    public void nextEpoch() {
        System.out.println("Generating new epoch");
        notifyObservers();
        currentEpoch = currentEpoch + 1;
    }

    public int getCurrentEpoch() {
        return currentEpoch;
    }

    public List<AbstractVehicle> getCarsInSquare(Vector2D upperLeft, Vector2D lowerRight) {
        if (upperLeft == null || lowerRight == null) {
            throw new NullPointerException("Vector2D in getCarsInSquare is null");
        }
        return vehicles.entrySet().stream().
                filter(map -> map.getKey().between(upperLeft, lowerRight)).
                map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public void addObserver(SimulationObserver observer) {
        simulationObservers.add(observer);
    }

    public void notifyObservers() {
        for(SimulationObserver observer: simulationObservers) {
            observer.nextEpoch();
        }
    }
}
