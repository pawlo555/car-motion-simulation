package Utilities;

import Objects.Point;
import Vehicles.AbstractVehicle;
import Vehicles.Bus;
import Vehicles.Car;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class EntranceParameters {
    private static final int EpochInMinute = 60;

    private int flow;
    private double busProbability;
    private int maxSpeed;
    private int waitingFlow = 0;

    EntranceParameters(CrossingParser parser, String entranceName) {
        flow = parser.getFlow(entranceName);
        busProbability = parser.getBusProbability(entranceName);
        maxSpeed = parser.getMaxSpeed(entranceName);
    }

    public void changeFlow(int newFlow) {
        flow = newFlow;
    }

    public void changeBusProbability(double newBusProbability) {
        System.out.println("Changing bus probability\n");
        busProbability = newBusProbability;
    }
    public void changeMaxSpeed(int newMaxSpeed) {
        maxSpeed = newMaxSpeed;
    }

    public ArrayList<AbstractVehicle> nextEpoch(int maxSize) {
        ArrayList<AbstractVehicle> spawnedCarsList = new ArrayList<>();
        waitingFlow = waitingFlow + flow;
        while (spawnedCarsList.size() <= maxSize && waitingFlow >= EpochInMinute) {
            spawnedCarsList.add(spawnCar());
        }
        return spawnedCarsList;
    }

    private AbstractVehicle spawnCar() {
        AbstractVehicle car;
        if (Math.random() < busProbability) {
            car = new Bus(new Point(new Vector2D(0,0)));
        }
        else {
            car = new Car(new Point(new Vector2D(0,0)));
        }
        return car;
    }

    static TreeMap<String, EntranceParameters> getEntrancesFromMap(CrossingsMap crossingsMap) throws IOException, ParseException {
        TreeMap<String, EntranceParameters> entrancesMap = new TreeMap<>();
        for(String crossingName: crossingsMap.getCrossingNames()) {
            String fileName = crossingsMap.getCrossingFile(crossingName);
            CrossingParser parser = new CrossingParser(fileName);
            for(String entrance: parser.getEntrancesNames()) {
                entrancesMap.put(entrance, new EntranceParameters(parser, entrance));
            }
        }

        return entrancesMap;
    }

}
