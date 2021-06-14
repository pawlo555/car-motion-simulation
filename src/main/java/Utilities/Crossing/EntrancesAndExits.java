package Utilities.Crossing;

import java.util.ArrayList;

// road 1 way in way out road with lines
public class EntrancesAndExits {
    public int number;
    public ArrayList<CrossingLane> lanes = new ArrayList<CrossingLane>();

    public EntrancesAndExits(ArrayList<CrossingLane> lanes, int number){
        this.number = number;
        this.lanes = lanes;
    }
}
