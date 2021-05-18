package Utilities;


import Vehicles.AbstractVehicle;

public interface CellObserver {
    /*
    When car ride through the cell we want to know about this in order to manage statistics.
     */
    void carMoveThrough(AbstractVehicle vehicle);
}
