package Simulation;

import Objects.City;
import Objects.Point;
import Utilities.*;
import Vehicles.AbstractVehicle;
import Vehicles.Bus;
import Vehicles.Car;

import java.util.*;
import java.util.stream.Collectors;

public class SimulationEngine implements VehicleObserver {

    private final ArrayList<SimulationObserver> simulationObservers = new ArrayList<>();
//    private final HashMap<Vector2D, AbstractVehicle> vehicles = new HashMap<>();
    private ArrayList<AbstractVehicle> vehicles = new ArrayList<>();
    private City city;
    private ExitsManager exitsManager;
    private EntrancesParametersManager entranceManager;

    public SimulationEngine(){
        CityBuilder builder = new CityBuilder();
        builder.buildFromDirectory("src/main/resources/Roads/ClockwiseRoads");
        builder.buildFromDirectory("src/main/resources/Roads/CounterwiseRoads");
//        builder.connectRoads();
        city = builder.getResult();
        builder.reset();
    }

    private int currentEpoch = 0;

    public void nextEpoch() {
        System.out.println("Generating new epoch");
        //spawn new vehicles
        Set<String> roadsToSpawn = entranceManager.getEntrancesNames();
        for (String roadName : roadsToSpawn){
            ArrayList<Point> points = city.getEntry(roadName);
            boolean spawned = false;
            for (Point entry : points) {
                if (!entry.hasVehicle() && entranceManager.shouldSpawnCar(roadName)) {
                    AbstractVehicle vehicle = new Car(entry);
                    vehicles.add(vehicle);
                    System.out.println(vehicle + " has been added");
                    vehicle.addObserver(this);
                }
            }
        }
        if (shouldSpawnVehicle())
            spawnCar();

        iterate();
        notifyObservers();
        currentEpoch = currentEpoch + 1;
    }

    public void iterate(){
        //restore moved
        for (AbstractVehicle vehicle : vehicles){
            vehicle.setMoved(false);
        }
        //accelerate
        for (AbstractVehicle vehicle : vehicles){
            vehicle.accelerate(1);
        }
        //decelerate
        for (AbstractVehicle vehicle : vehicles){
            int speed = vehicle.getSpeed();
            int distance = MoveHelper.distanceToVehicleInRange(vehicle.getHeadPoint(), speed);
            if (distance > 0){
                vehicle.setSpeed(distance-1);
            }
        }
        //move
        ArrayList<AbstractVehicle> vehicles_copy = new ArrayList<>(vehicles);
        for (AbstractVehicle vehicle : vehicles_copy){
            vehicle.move();
        }
    }

    public void spawnCar(){
        ArrayList<Point> entries = city.getEntries();
        int size = entries.size();
        int index = (int)(Math.random()*(size));
        Point entry = entries.get(index);
        if (!entry.hasVehicle()) {
            AbstractVehicle vehicle = new Car(entry);
            vehicles.add(vehicle);
            System.out.println(vehicle + " has been added");
            vehicle.addObserver(this);
        }
    }

    public void spawnBus(){
        ArrayList<Point> entries = city.getEntries();
        int size = entries.size();
        int index = (int)(Math.random()*(size));
        Point entry = entries.get(index);
        if (!entry.hasVehicle()) {
            AbstractVehicle vehicle = new Bus(entry);
            vehicles.add(vehicle);
            System.out.println(vehicle + " has been added");
            vehicle.addObserver(this);
        }
    }

    private boolean shouldSpawnVehicle(){
        return Math.random() < 0.3;
    }

    public int getCurrentEpoch() {
        return currentEpoch;
    }

//    public List<AbstractVehicle> getCarsInSquare(Vector2D upperLeft, Vector2D lowerRight) {
//        if (upperLeft == null || lowerRight == null) {
//            throw new NullPointerException("Vector2D in getCarsInSquare is null");
//        }
//        return vehicles.entrySet().stream().
//                filter(map -> map.getKey().between(upperLeft, lowerRight)).
//                map(Map.Entry::getValue).collect(Collectors.toList());
//    }

    public List<AbstractVehicle> getCarsInSquare(Vector2D upperLeft, Vector2D lowerRight) {
//        if (upperLeft == null || lowerRight == null) {
//            throw new NullPointerException("Vector2D in getCarsInSquare is null");
//        }
//        return vehicles.stream().
//                filter(map -> map.getPosition().between(upperLeft, lowerRight)).
//                collect(Collectors.toList());
        return vehicles;
    }

    public void addObserver(SimulationObserver observer) {
        simulationObservers.add(observer);
    }

    public void notifyObservers() {
        for(SimulationObserver observer: simulationObservers) {
            observer.nextEpoch();
        }
    }

    @Override
    public void carMoved(AbstractVehicle vehicle) {
        System.out.println(vehicle.toString());
    }

    @Override
    public void carDroveOnto(AbstractVehicle vehicle) {

    }

    @Override
    public void carExit(AbstractVehicle vehicle) {
        vehicles.remove(vehicle);
        vehicle.removeObserver(this);
    }
}
