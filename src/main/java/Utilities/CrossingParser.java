package Utilities;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CrossingParser {
    private final JSONObject entrances;
    private final JSONObject exits;
    private final JSONObject positionOnMap;
    private final String name;

    public CrossingParser(String filename) throws IOException, ParseException {
        JSONObject json = (JSONObject) new JSONParser().parse(new FileReader(filename));
        name = (String) json.get("name");
        entrances = (JSONObject) json.get("Entrances");
        exits = (JSONObject) json.get("Exits");
        positionOnMap = (JSONObject) json.get("positionOnMap");
    }

    public int getFlow(String name) {
        JSONObject entrance = (JSONObject) entrances.get(name);
        long value = (long) entrance.get("flow");
        return (int) value;
    }

    public double getBusProbability(String name) {
        JSONObject entrance = (JSONObject) entrances.get(name);
        return (double) entrance.get("busProbability");
    }

    public int getMaxSpeed(String name) {
        JSONObject entrance = (JSONObject) entrances.get(name);
        long value = (long) entrance.get("maxSpeed");
        return (int) value;
    }

    // TODO we want to save data to file.
    public void setNewSettings(int flow, double busProbability, int maxSpeed) {

    }

    public String getName() {
        return name;
    }

    public int getEntranceId(String name) {
        JSONObject entrance = (JSONObject) entrances.get(name);
        long value = (long) entrance.get("id");
        return (int) value;
    }

    public int getExitId(String name) {
        JSONObject exit = (JSONObject) exits.get(name);
        long value = (long) exit.get("id");
        return (int) value;
    }

    public int getHorizontalPosition(String zoom) {
        JSONObject zoomValues = (JSONObject) positionOnMap.get("zoom"+zoom);
        long value = (long) zoomValues.get("horizontal");
        return (int) value;
    }

    public int getVerticalPosition(String zoom) {
        JSONObject zoomValues = (JSONObject) positionOnMap.get("zoom"+zoom);
        long value = (long) zoomValues.get("vertical");
        return (int) value;
    }

    public Set<String> getEntrancesNames() {
        return (Set<String>) entrances.keySet();
    }

    public Set<String> getExitsNames() {
        return (Set<String>) exits.keySet();
    }
}
