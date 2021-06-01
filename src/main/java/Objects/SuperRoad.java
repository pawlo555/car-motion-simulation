package Objects;

import Utilities.Direction;
import Utilities.RoadObject;
import Utilities.RoadRoadConnector;
import Utilities.Vector2D;

import java.util.ArrayList;
import java.util.LinkedList;

public class SuperRoad implements RoadObject {
    private LinkedList<StraightRoad> roadParts = new LinkedList<>();
    private String name;
    private int id;

    public void addRoad(Direction direction, StraightRoad road){
        if (roadParts.size() > 0) {
            if (direction == Direction.FRONT) {
                RoadRoadConnector.connectFront(roadParts.getLast(), road, Direction.LEFT);
                roadParts.addLast(road);
            } else if (direction == Direction.BACK) {
                RoadRoadConnector.connectFront(road, roadParts.getFirst(), Direction.LEFT);
                roadParts.addFirst(road);
            }
        }
        else
            roadParts.addLast(road);
    }

    public void setName(String name){
        this.name = name;
    }

    public void setId(int id){
        this.id = id;
    }

    public ArrayList<Point> getEntries(){
        return roadParts.getFirst().getEntries();
    }

    public ArrayList<Point> getExits(){
        return roadParts.getLast().getExits();
    }

    public void printFullPoints(){
        for (StraightRoad road : roadParts){
            for (Line lane : road.getLanes()){
                for (Point point : lane.getCells()){
                    System.out.print(point.getPosition());
                }
            }
        }
    }

    public int getId(){return id;}
    public String getName(){return name;}
}
