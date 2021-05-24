package Utilities;

import Objects.Point;
import Objects.StraightRoad;

import java.util.ArrayList;
import java.util.Collections;

public class RoadRoadConnector {

    public static void connectFront(StraightRoad road1, StraightRoad road2, Direction startDirection){
        //connect road2 to the front end od the road1
        //start direction gives information from which lane start adding
        ArrayList<Point> exits = road1.getExits();
        ArrayList<Point> entries = road2.getEntries();

        if (startDirection == Direction.RIGHT){
            Collections.reverse(exits);
            Collections.reverse(entries);
        }
        for (int i = 0; i < exits.size() && i < entries.size(); i++){
            Point exit = exits.get(i);
            Point entry = entries.get(i);
            exit.addNeighbor(Direction.FRONT, entry);
            entry.addNeighbor(Direction.BACK, exit);
            exit.setType(PointType.NORMAL);
            entry.setType(PointType.NORMAL);
        }

        System.out.println("After connection");
        for (Point exit : exits){
            System.out.println(exit);
        }
        for (Point entry : exits){
            System.out.println(entry);
        }

    }
}
