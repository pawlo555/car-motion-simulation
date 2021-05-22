package Vehicles;

import Objects.Point;

public class Bus extends AbstractVehicle{
    public Bus(Point point) {
        super(2, point);
    }

    @Override
    public String toString() {
        String tailPositions = "";
        for (Point tailPosition : tailPoints){
            tailPositions += tailPosition.getPosition().toString();
        }
        return "Bus{" +
                "head position=" + headPoint.getPosition() +
                ", tail positions=" + tailPositions +
                ", length=" + length +
                ", speed=" + speed +
                '}';
    }
}
