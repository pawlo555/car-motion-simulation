package Utilities;


import org.json.JSONObject;

public class CrossingParser {
    private JSONObject json;

    public CrossingParser(String filename) {
        json = new JSONObject(filename);
    }

    public int getFlow() {
        return 0;
    }

    public double getBusProbability() {
        return 0.0;
    }

    public int getMaxSpeed() {
        return 0;
    }

    public void setNewSettings(int flow, double busProbability, int maxSpeed) {

    }

}
