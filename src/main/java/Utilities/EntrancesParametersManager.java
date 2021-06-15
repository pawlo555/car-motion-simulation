package Utilities;

import Vehicles.AbstractVehicle;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class EntrancesParametersManager {
    CrossingsMap map = new CrossingsMap("src/main/resources/Utilities/Crossings");
    private final TreeMap<String, EntranceParameters> entrances;


    public EntrancesParametersManager() throws IOException, ParseException {
        entrances = EntranceParameters.getEntrancesFromMap(map);
    }

    public void changeFlow(String entranceName, int newFlow) {
        entrances.get(entranceName).changeFlow(newFlow);
    }

    public void changeBusProbability(String entranceName, double newBusProbability) {
        entrances.get(entranceName).changeBusProbability(newBusProbability);
    }

    public void changeMaxSpeed(String entranceName, int newMaxSpeed) {
        entrances.get(entranceName).changeMaxSpeed(newMaxSpeed);
    }

    public int getFlow(String entranceName) {
        return entrances.get(entranceName).getFlow();
    }

    public double getBusProbability(String entranceName) {
        return entrances.get(entranceName).getBusProbability();
    }

    public int getMaxSpeed(String entranceName) {
        return entrances.get(entranceName).getMaxSpeed();
    }

    public Set<String> getEntrancesNames() {
        Set<String> existingEntrances = new TreeSet<>();
        existingEntrances.add("Czarnowiejska AGH");
        existingEntrances.add("Czarnowiejska Rynek");
        return existingEntrances;
    }

    public boolean shouldSpawnCar(String roadName){
        return entrances.get(roadName).shouldSpawnCar();
    }
}
