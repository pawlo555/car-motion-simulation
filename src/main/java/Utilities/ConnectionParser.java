package Utilities;

import Objects.Point;
import Objects.SuperRoad;
import Utilities.Crossing.Crossing;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConnectionParser {
    private JSONParser parser = new JSONParser();
    private JSONObject json;
    private Map<String ,Crossing> crossings;
    Map<String, SuperRoad> roads;

    public ConnectionParser(String filename, Map<String ,Crossing> crossings, Map<String, SuperRoad> roads){
        try {
            json = (JSONObject) parser.parse(new FileReader(filename));
            this.crossings = crossings;
            this.roads = roads;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void connect(){
        JSONArray crossingParts = (JSONArray) json.get("crossingParts");
        Iterator partsItr = crossingParts.iterator();
        while (partsItr.hasNext()){
            JSONObject crossingInfo = (JSONObject) partsItr.next();
            String crossingName = (String) crossingInfo.get("crossingName");
            Crossing crossing = crossings.get(crossingName);
            JSONArray connections = (JSONArray) crossingInfo.get("connections");
            Iterator connectionsItr = connections.iterator();
            while (connectionsItr.hasNext()){
                JSONObject connectionInfo = (JSONObject) connectionsItr.next();
                int roadId = ((Long) connectionInfo.get("id")).intValue();
                String roadName = (String) connectionInfo.get("roadName");
                String connectionType = (String) connectionInfo.get("connectionType");
                SuperRoad road = roads.get(roadName);
                crossing.roadNames.put(roadName, roadId);
                if (connectionType.equals("front")){
                    RoadCrossingConnector.connectFront(road, crossing);
                }
                else {
                    RoadCrossingConnector.connectBack(road, crossing);
                    List<Point> entries = road.getEntries();
                    for (Point entry : entries){
                        crossing.addExitNeighbour(entry.getLane(), roadName, entry);
                    }
                }
            }
        }
    }

}

