package Utilities;

import Objects.StraightRoad;
import Objects.SuperRoad;
import org.json.JSONObject;

public class SuperRoadBuilder {
    private SuperRoad superRoad = new SuperRoad();
    private RoadParser parser;

    public void reset(){
        superRoad = new SuperRoad();
    }

    public SuperRoad getResult(){
        return superRoad;
    }

    public void buildFromJSON(String filename){
        this.parser = new RoadParser(filename);
        superRoad.setId(parser.getId());
        superRoad.setName(parser.getName());
        for (StraightRoad roadPart : parser.getRoads()){
            superRoad.addRoad(Direction.FRONT, roadPart);
        }
    }

}
