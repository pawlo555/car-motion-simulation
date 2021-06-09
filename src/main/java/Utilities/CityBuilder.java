package Utilities;

import java.io.File;
import Objects.City;
import Objects.StraightRoad;
import Objects.SuperRoad;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            superRoadBuilder.reset();
            city.addRoad(roadId, road);
        }
    }

    public void buildFromDirectory(String path){
        String[] pathnames;
        File f = new File(path);
        pathnames = f.list();
        for (int i = 0; i < pathnames.length; i++){
            pathnames[i] = path+'/'+pathnames[i];
        }
        buildRoads(Arrays.asList(pathnames));

    }

    public void connectRoads(){
        // build superRoads and connect them in a given order
        city.connectRoadsFront();
    }

}
