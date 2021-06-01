package Objects;

import Utilities.Direction;
import Utilities.RoadRoadConnector;

import java.util.*;

public class City {
    private Map<Integer, SuperRoad> roads = new LinkedHashMap<>();

    public void addRoad(int index, SuperRoad road){
        roads.put(index, road);
    }

    public void connectRoadsFront(){
        SortedSet<Integer> keys = new TreeSet<>(roads.keySet());
        LinkedList<SuperRoad> connected = new LinkedList<>();
        for (Integer roadId : keys){
            // do something
            System.out.println("Now I operate on key"+roadId);
            SuperRoad road = roads.get(roadId);
            if (connected.size() == 0)
                connected.add(road);
            else{
                SuperRoad last = connected.getLast();
                RoadRoadConnector.connectFront(last, road, Direction.LEFT);
                connected.addLast(road);
            }
        }
    }

    public ArrayList<Point> getEntries(){
        SortedSet<Integer> keys = new TreeSet<>(roads.keySet());
        SuperRoad entryRoad = roads.get(keys.first());
        return entryRoad.getEntries();
    }

    public ArrayList<Point> getExits(){
        SortedSet<Integer> keys = new TreeSet<>(roads.keySet());
        SuperRoad exitRoad = roads.get(keys.last());
        return exitRoad.getExits();
    }

}
