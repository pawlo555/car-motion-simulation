package Utilities;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CrossingParser {
    private JSONObject json;
    private ArrayList<JSONObject> entrances;
    private ArrayList<JSONObject> exits;
    private String name;

    public CrossingParser(String filename) throws IOException, ParseException {
        json = (JSONObject) new JSONParser().parse(new FileReader(filename));
        name = (String) json.get("name");
        //entrances = (ArrayList<JSONObject>) json.get("Entrances");
    }

    public int getFlow(String name) {
        return 0;
    }

    public double getBusProbability(String name) {
        return 0.0;
    }

    public int getMaxSpeed(String name) {
        return 0;
    }

    public void setNewSettings(int flow, double busProbability, int maxSpeed) {

    }

    public String getName() {
        return name;
    }

    public int getId(String name) {
        return 1;
    }

    public int getHorizontalPosition(String zoom) {
        return 0;
    }

    public int getVerticalPosition(String zoom) {
        return 0;
    }
}
