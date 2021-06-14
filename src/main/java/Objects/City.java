package Objects;

import Utilities.Crossing.Crossing;
import Utilities.Direction;
import Utilities.RoadRoadConnector;

import java.util.*;

public class City {
    private Map<String, SuperRoad> roads = new LinkedHashMap<>();
    private Map<String, Crossing> crossings = new LinkedHashMap<>();

    public void addRoad(String name, SuperRoad road){
        roads.put(name, road);
    }

    public void addCrossing(String name, Crossing crossing){
        crossings.put(name, crossing);
    }

    public ArrayList<Point> getEntry(String name){
        String myName = name + " Entrance";
        return roads.get(myName).getEntries();
    }

    public ArrayList<Point> getExit(String name){
        String myName = name + " Exit";
        return roads.get(myName).getExits();
    }

    public ArrayList<Point> getEntries(){
        ArrayList<Point> entries = new ArrayList<>();
        for (SuperRoad road : roads.values()){
            entries.addAll(road.getEntries());
        }
        return entries;
    }

    public ArrayList<Point> getExits(){
        ArrayList<Point> exits = new ArrayList<>();
        for (SuperRoad road : roads.values()){
            exits.addAll(road.getExits());
        }
        return exits;
    }

    public Map<String, SuperRoad> getRoads(){
        return roads;
    }

    public Map<String, Crossing> getCrossings(){
        return crossings;
    }

}
