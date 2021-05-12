package Simulation;

import Utilities.Vector2D;
import Vehicles.AbstractVehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimulationEngine {

    private HashMap<Vector2D, AbstractVehicle> vehicles = new HashMap();
    private int currentEpoch = 0;

    public void stop() {
        System.out.println("Stop simulating");
    }

    public void start() {
        System.out.println("Start simulating");
    }

    public void doubleSpeed() {
        System.out.println("Double speed");
    }

    public void nextEpoch() {
        System.out.println("Generating new epoch");
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

}
