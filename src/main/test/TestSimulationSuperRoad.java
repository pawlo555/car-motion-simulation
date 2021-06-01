import Objects.Point;
import Objects.StraightRoad;
import Objects.SuperRoad;
import Utilities.MoveHelper;
import Utilities.VehicleObserver;
import Vehicles.AbstractVehicle;
import Vehicles.Bus;
import Vehicles.Car;

import java.util.ArrayList;

public class TestSimulationSuperRoad implements VehicleObserver{
    private SuperRoad road;
    private ArrayList<AbstractVehicle> vehicles = new ArrayList<>();

    public TestSimulationSuperRoad(SuperRoad road){
        this.road = road;
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

    public void print(){
        for (AbstractVehicle vehicle : vehicles){
            System.out.print(vehicle.getHeadPoint().getPosition());
        }
        if (vehicles.isEmpty())
            System.out.println("No cars in simulation");
    }

    public void spawnCar(){
        ArrayList<Point> entries = road.getEntries();
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
        ArrayList<Point> entries = road.getEntries();
        int size = entries.size();
        int index = (int)(Math.random()*(size));
        Point entry = entries.get(index);
        AbstractVehicle vehicle = new Bus(entry);
        vehicles.add(vehicle);
        System.out.println(vehicle+" has been added");
        vehicle.addObserver(this);

    }

    @Override
    public void carMoved(AbstractVehicle vehicle) {

    }

    @Override
    public void carDroveOnto(AbstractVehicle vehicle) {

    }

    @Override
    public void carExit(AbstractVehicle vehicle) {
        vehicles.remove(vehicle);
    }

}
