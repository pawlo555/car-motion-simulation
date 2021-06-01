package Utilities;

import Objects.City;
import Objects.StraightRoad;
import Objects.SuperRoad;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class CityBuilder {
    private City city = new City();
    private SuperRoadBuilder superRoadBuilder = new SuperRoadBuilder();

    public void reset(){
        city = new City();
    }

    public City getResult(){
        return city;
    }

    public void buildRoads(ArrayList<String> paths){
        // build superRoads and connect them in a given order
        for (String path : paths){
            superRoadBuilder.buildFromJSON(path);
            SuperRoad road = superRoadBuilder.getResult();
            int roadId = road.getId();
            superRoadBuilder.reset();
            city.addRoad(roadId, road);
        }
    }

    public void connectRoads(){
        // build superRoads and connect them in a given order
        city.connectRoadsFront();
    }

}
