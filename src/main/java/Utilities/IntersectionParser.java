package Utilities;

import Utilities.Crossing.Crossing;
import Utilities.Crossing.EntrancesAndExits;
import Utilities.Crossing.CrossingLane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class IntersectionParser {
    private JSONParser parser = new JSONParser();
    private JSONObject json;

    public IntersectionParser(String filename){
        try {
            json = (JSONObject) parser.parse(new FileReader(filename));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return (String) json.get("name");
    }

    public int getId(){
        return ((Long) json.get("id")).intValue();
    }


    public Vector2D parsePosition(JSONArray arr){
        double x,y;
        x = (double) arr.get(0);
        y = (double) arr.get(1);
        // TODO zamienic x z y gdy Pawel poprawi mapke
//        x = (double) arr.get(1);
//        y = (double) arr.get(0);
        return new Vector2D(x, y);
    }

    public Crossing getCrossing(){
        ArrayList<EntrancesAndExits> roads = new ArrayList<>();

        String name = getName();
        int id = getId();

        JSONObject intersectionArea = (JSONObject) json.get("area");

        Vector2D leftBottom = parsePosition((JSONArray) intersectionArea.get("leftBottom"));
        Vector2D rightBottom = parsePosition((JSONArray) intersectionArea.get("rightBottom"));
        Vector2D leftTop = parsePosition((JSONArray) intersectionArea.get("leftTop"));
        Vector2D rightTop = parsePosition((JSONArray) intersectionArea.get("rightTop"));

        JSONArray entrancesAndExits = (JSONArray) json.get("roads");
        Iterator roadsItr = entrancesAndExits.iterator();

        while (roadsItr.hasNext()){
            ArrayList<CrossingLane> lanes = new ArrayList<>();
            JSONObject roadInfo = (JSONObject) roadsItr.next();
            int roadId = ((Long) roadInfo.get("roadId")).intValue();
            JSONArray roadLanes = (JSONArray) roadInfo.get("lanes");
            Iterator lanesItr = roadLanes.iterator();

            /////////////////////////////
            while (lanesItr.hasNext()) {
                JSONObject laneInfo = (JSONObject) lanesItr.next();
                int laneId = ((Long) laneInfo.get("laneId")).intValue();
                int x = ((Long) laneInfo.get("xCoord")).intValue();
                int y = ((Long) laneInfo.get("yCoord")).intValue();

                ArrayList<Integer> laneExits = new ArrayList<>();
                JSONArray exits = (JSONArray) laneInfo.get("exits");
                if(exits != null){
                    for (int i=0;i<exits.size();i++){
                        laneExits.add(((Long) exits.get(i)).intValue());
                    }
                }

                lanes.add(new CrossingLane(laneId,x,y,laneExits,roadId));
            }
            roads.add(new EntrancesAndExits(lanes,roadId));
        }
        return new Crossing(id,name,leftBottom,rightBottom,leftTop,rightTop,roads);
    }

}
