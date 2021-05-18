import Objects.Point;
import Objects.StraightRoad;
import Utilities.MoveHelper;
import Utilities.VehicleObserver;
import Vehicles.AbstractVehicle;
import Vehicles.Car;

import java.util.ArrayList;

public class TestSimulation implements VehicleObserver {
    private StraightRoad road;
    private ArrayList<AbstractVehicle> vehicles = new ArrayList<>();

    public TestSimulation(StraightRoad road){
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
            System.out.println(vehicle);
        }
        if (vehicles.isEmpty())
            System.out.println("No cars in simulation");
    }

    public void spawnCar(){
        ArrayList<Point> entries = road.getEntries();
        int size = entries.size();
        int index = (int)(Math.random()*(size));
        Point entry = entries.get(index);
        AbstractVehicle vehicle = new Car(entry);
        vehicles.add(vehicle);
        vehicle.addObserver(this);

    }

    @Override
    public void carMoved(AbstractVehicle vehicle) {
        System.out.println("Vehicle "+vehicle+" moved");
    }

    @Override
    public void carDroveOnto(AbstractVehicle vehicle) {

    }

    @Override
    public void carExit(AbstractVehicle vehicle) {
        vehicles.remove(vehicle);
    }
}
