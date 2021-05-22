package Vehicles;

import Objects.Point;

public class Car extends AbstractVehicle{
    public Car(Point point) {
        super(1, point);
    }

    @Override
    public String toString() {
        return "Car{" +
                "position=" + headPoint.getPosition() +
                ", length=" + length +
                ", speed=" + speed +
                '}';
    }
}
