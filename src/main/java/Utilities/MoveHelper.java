package Utilities;

import Objects.Point;
import Utilities.Crossing.Crossing;

public class MoveHelper {
    public static Point getMovePosition(Point point, int speed){
        //returns position that car will move to, if car can't move, method returns null
        Point neighbor = point;
        for (int i = 1; i <= speed; i++){
            neighbor = neighbor.getNeighbor(Direction.FRONT);
            if (neighbor == null)
                throw new NullPointerException("Point doesn't have neighbor in front direction");
            if (neighbor.getType() == PointType.CROSSING){
                //TODO
                Crossing crossing = neighbor.getCrossing();
                int laneId =  point.getLane();
                String roadName = point.getRoadName();
                //funkcja do wyciągania randomowej drogi
                Point movePoint = crossing.getWay(laneId, roadName);
//                System.out.println("Now car wants to drive on a crossing. Point that crossing gave him is "+movePoint);
                return movePoint;
            }
            if (neighbor.getType() == PointType.SIMULATION_EXIT)
                return neighbor;
            if (neighbor.hasVehicle())
                return null;
        }
        return neighbor;

    }

    public static boolean canMove(Point point, int speed){
        //returns true if car can move from point with given speed, false otherwise
        Point movePoint = getMovePosition(point, speed);
        if (movePoint == null || movePoint.equals(point))
            return false;
        return true;
    }

    public static int distanceToVehicleInRange(Point point, int range){
        //checks if there is any car in a distance smaller than range,
        //if there is car, then returns distance to it, otherwise -1
        Point neighbor = point;
        for (int i = 1; i <= range; i++){
            neighbor = neighbor.getNeighbor(Direction.FRONT);
            if (neighbor == null)
                throw new NullPointerException("Point doesn't have neighbor in front direction");
            if (neighbor.getType() == PointType.CROSSING){
                return -1;
            }
            if (neighbor.getType() == PointType.SIMULATION_EXIT) {
                break;
            }
            if (neighbor.hasVehicle())
                return i;
        }
        return -1;
    }

}
