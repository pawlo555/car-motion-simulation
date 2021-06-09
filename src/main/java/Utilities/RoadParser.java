package Utilities;

import Objects.StraightRoad;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class RoadParser {
    private JSONParser parser = new JSONParser();
    private JSONObject json;

    public RoadParser(String filename){
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

    public int getNLanes(){
        return ((Long) json.get("nLanes")).intValue();
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

    public ArrayList<StraightRoad> getRoads(){
        int nLanes = getNLanes();
        ArrayList<StraightRoad> roads = new ArrayList<>();
        JSONArray roadParts = (JSONArray) json.get("parts");
        Iterator partsItr = roadParts.iterator();
        while (partsItr.hasNext()){
            JSONObject roadInfo = (JSONObject) partsItr.next();
            int id = ((Long) roadInfo.get("partId")).intValue();
            Vector2D leftBottom = parsePosition((JSONArray) roadInfo.get("leftBottom"));
            Vector2D rightBottom = parsePosition((JSONArray) roadInfo.get("rightBottom"));
            Vector2D leftTop = parsePosition((JSONArray) roadInfo.get("leftTop"));
            Vector2D rightTop = parsePosition((JSONArray) roadInfo.get("rightTop"));
            QuadrangleArea roadArea = new QuadrangleArea(leftBottom, rightBottom, leftTop, rightTop);
            roads.add(new StraightRoad(nLanes, roadArea));

        }
        return roads;
    }

}
