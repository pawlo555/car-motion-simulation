package Utilities.Crossing;

import java.util.ArrayList;

public class CrossingLane {

    public int id;
    // co-ordinates of line
    public int x;
    public int y;
    // list of lines that car can choose from and drive to
    public ArrayList<Integer> ways;

    public boolean isEntry;
    public int myRoad;

    public CrossingLane(int id, int x, int y, ArrayList<Integer> ways,int road){
        this.id = id;
        this.x = x;
        this.y = y;
        this.ways = ways;
        this.myRoad = road;
        if(ways.size() == 0){
            isEntry = false;
        } else{
            isEntry = true;
        }
    }
}
