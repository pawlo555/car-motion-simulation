package Utilities;

import Objects.Point;
import Utilities.Crossing.Crossing;
import Utilities.Direction;
import Utilities.PointType;
import Utilities.RoadObject;

import java.util.ArrayList;
import java.util.Collections;

public class RoadCrossingConnector {

    public static void connectFront(RoadObject road, Crossing crossing){
        //connect end of road to the crossing
        ArrayList<Point> exits = road.getExits();
        Point crossingPoint = crossing.crossingPoint;

        for (Point exit : exits){
            exit.addNeighbor(Direction.FRONT, crossingPoint);
            exit.setType(PointType.NORMAL);
        }

    }

    public static void connectBack(RoadObject road, Crossing crossing){
        //connect of road to the crossing
        ArrayList<Point> entries = road.getExits();
        Point crossingPoint = crossing.crossingPoint;

        for (Point entry : entries){
            entry.addNeighbor(Direction.BACK, crossingPoint);
            entry.setType(PointType.NORMAL);
        }

    }
}
