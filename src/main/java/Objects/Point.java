package Objects;

import Vehicles.AbstractVehicle;

public class Point {
    public Point backNeighbor;
    public Point leftNeighbor;
    public Point frontNeighbor;
    public Point rightNeighbor;
    private AbstractVehicle vehicle;

    public void placeVehicle(AbstractVehicle vehicle){
        this.vehicle = vehicle;
    }

    public void removeVehicle(){
        this.vehicle = null;
    }

    public boolean hasCar(){
        if (vehicle == null)
            return true;
        else
            return false;
    }

}
