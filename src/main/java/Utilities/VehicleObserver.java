package Utilities;

import Vehicles.AbstractVehicle;

public interface VehicleObserver {

    void carMoved(AbstractVehicle vehicle);
    void carDroveOnto(AbstractVehicle vehicle);
    void carExit(AbstractVehicle vehicle);
}
