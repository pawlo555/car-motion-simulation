package Utilities;

import java.io.File;
import Objects.City;
import Objects.StraightRoad;
import Objects.SuperRoad;
import Utilities.Crossing.Crossing;
import org.json.simple.JSONObject;

import java.util.*;

public class CityBuilder {
    private City city = new City();
    private SuperRoadBuilder superRoadBuilder = new SuperRoadBuilder();
    private IntersectionBuilder intersectionBuilder = new IntersectionBuilder();
    private ConnectionParser connectionParser;

    public void reset(){
        city = new City();
    }

    public City getResult(){
        return city;
    }

    public void listf(String directoryName, List<String> filePaths) {
        File directory = new File(directoryName);

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if(fList != null)
            for (File file : fList) {
                if (file.isFile()) {
                    filePaths.add(directoryName+"/"+file.getName());
                } else if (file.isDirectory()) {
                    listf(directoryName+"/"+file.getName(), filePaths);
                }
            }
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

    public void buildCrossing(String path){
        // build superRoads and connect them in a given order
        intersectionBuilder.buildFromJSON(path);
        Crossing crossing = intersectionBuilder.getResult();
        String name = crossing.name;
        intersectionBuilder.reset();
        city.addCrossing(name, crossing);
    }

    public void connectRoadsAndCrossings(String path){
        List<String> connectionsPaths = new ArrayList<>();
        listf(path, connectionsPaths);
        for (String connectionPath : connectionsPaths){
            connectionParser = new ConnectionParser(connectionPath, city.getCrossings(), city.getRoads());
            connectionParser.connect();
        }
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

    public void buildIntersectionFromDirectory(String path){
        List<String> crossingPaths = new ArrayList<>();
        listf(path, crossingPaths);
        for (String crossingPath : crossingPaths){
            buildCrossing(crossingPath);
        }

    }

//    public void buildRoadsFromDirectory(String path){
//        List<String> roadPaths = new ArrayList<>();
//        listf(path, roadPaths);
//        for (String roadPath : roadPaths){
//            buildCrossing(crossingPath);
//        }
//
//    }

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

}
