package Utilities.Crossing;

import java.util.ArrayList;

public class CrossingCell {
    public CrossingLane crossingLane;
    public int x;
    public int y;
    public double mapX;
    public double mapY;
    public ArrayList<CrossingCell> neighbors =  new ArrayList<>();

    public CrossingCell(CrossingLane crossingLane, int x, int y){
        this.crossingLane = crossingLane;
        this.x = x;
        this.y = y;
    }

    public void  setMapCoords(double x, double y){
        this.mapX = x;
        this.mapY = y;
    }
}
