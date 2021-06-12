package Utilities;

import java.io.File;
import Objects.City;
import Objects.StraightRoad;
import Objects.SuperRoad;
import org.json.simple.JSONObject;

import java.util.*;

public class CityBuilder {
    private City city = new City();
    private SuperRoadBuilder superRoadBuilder = new SuperRoadBuilder();

    public void reset(){
        city = new City();
    }

    public City getResult(){
        return city;
    }

    public void buildRoads(List<String> paths){
        // build superRoads and connect them in a given order
        for (String path : paths){
            superRoadBuilder.buildFromJSON(path);
            SuperRoad road = superRoadBuilder.getResult();
            int roadId = road.getId();
            String name = road.getName();
            superRoadBuilder.reset();
            city.addRoad(name, road);
        }
    }

    public SuperRoad buildRoad(String path){
        // build superRoads and connect them in a given order
        superRoadBuilder.buildFromJSON(path);
        SuperRoad road = superRoadBuilder.getResult();
        int roadId = road.getId();
        String name = road.getName();
        superRoadBuilder.reset();
        city.addRoad(name, road);
        return road;
    }

    public void buildFromDirectory(String path){
        Map<Integer, SuperRoad> roads = new LinkedHashMap<>();
        SuperRoad road;
        String[] pathnames;
        File f = new File(path);
        pathnames = f.list();
        for (int i = 0; i < pathnames.length; i++){
            pathnames[i] = path+'/'+pathnames[i];
            road = buildRoad(pathnames[i]);
            roads.put(road.getId(), road);
        }
        //connect
        connectRoadsFront(roads);

    }

    public void connectRoadsFront(Map<Integer, SuperRoad> roads){
        SortedSet<Integer> keys = new TreeSet<>(roads.keySet());
        LinkedList<SuperRoad> connected = new LinkedList<>();
        for (Integer roadId : keys){
            // do something
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

    public void connectRoads(){
        // build superRoads and connect them in a given order
        // city.connectRoadsFront();
    }

}
