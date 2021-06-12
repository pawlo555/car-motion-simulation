package Objects;

import Constants.RoadConstants;
import Utilities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;

public class StraightRoad implements RoadObject {
    //class for road that is rectangle nLanes x length
    private int nLanes;
    private double length;
    private double cellLength = RoadConstants.cellLength;
    private Line[] road;
    private int id;
    private EnumMap<Direction, IRoadComponent> roadNeighbors = new EnumMap<>(Direction.class);
    private QuadrangleArea roadArea;

//    //fix length in advance
//    public StraightRoad(int length, int nLanes, QuadrangleArea roadArea){
//        this.nLanes = nLanes;
//        this.length = length;
//        this.roadArea = roadArea;
//        road = new Line[nLanes];
//    }

    //let road count her length - preferred method
    public StraightRoad(int nLanes, QuadrangleArea roadArea){
        this.nLanes = nLanes;
        this.roadArea = roadArea;
        this.length = roadArea.leftBottom.distance(roadArea.leftTop);
        road = new Line[nLanes];
        initialize();
//        initialize neighborhood
        initializeNeighborhood();
    }

    void initialize() {
        //get rectangular part of the road
        QuadrangleArea rectRoad = roadArea.rectDecomposition();

        //upper triangle - remaining part of the road after rectDecomposition
        QuadrangleArea upper = new QuadrangleArea(rectRoad.leftTop, rectRoad.rightTop, roadArea.leftTop, roadArea.rightTop);
        Vector2D[] upperSplitPoints = GeometryUtils.splitIntoParts(upper.leftTop, upper.rightTop, nLanes);
        double upperArea;
        if (GeometryUtils.distanceFromLine(upper.leftBottom, upper.rightBottom, upper.leftTop) >
                GeometryUtils.distanceFromLine(upper.leftBottom, upper.rightBottom, upper.rightTop))
            upperArea = GeometryUtils.countTirangleArea(upper.leftBottom, upper.rightBottom, upper.leftTop);
        else
            upperArea = GeometryUtils.countTirangleArea(upper.leftBottom, upper.rightBottom, upper.rightTop);

        //lower triangle
        QuadrangleArea lower = new QuadrangleArea(roadArea.leftBottom, roadArea.rightBottom, rectRoad.leftBottom, rectRoad.rightBottom);
        Vector2D[] lowerSplitPoints = GeometryUtils.splitIntoParts(lower.leftBottom, lower.rightBottom, nLanes);

        QuadrangleArea[] lanes = rectRoad.splitIntoLanes(nLanes);
        QuadrangleArea[][] cells = new QuadrangleArea[nLanes][];

        //split each line into cells
        int minParts = 0;
        for (int i=0; i< nLanes; i++){
            double length = GeometryUtils.distanceFromLine(lanes[i].leftBottom, lanes[i].rightBottom,lanes[i].leftTop);
            int nParts = (int)(length/cellLength);
            if (i == 0 || nParts < minParts)
                minParts = nParts;
        }
        for (int i = 0; i < nLanes; i++) {
            cells[i] = lanes[i].splitIntoCells(cellLength, minParts);
        }

        QuadrangleArea lastCell = cells[0][cells[0].length-1];
        double smallArea = GeometryUtils.countRectangleArea(lastCell.leftBottom, lastCell.rightBottom,
                lastCell.leftTop, lastCell.rightTop) * nLanes;
        if (smallArea + upperArea < nLanes * cellLength && cells[0].length > 1) {
            //if cell and upper triangle are not big enough to be cell
            for (int i = 0; i < nLanes; i++) {
                cells[i] = Arrays.copyOf(cells[i], cells[i].length-1);
            }
        }

        //concatenate last cell with upper triangle
        int nCells = cells[0].length;
        for (int i = 0; i < nLanes; i++) {
            cells[i][nCells-1].leftTop = upperSplitPoints[i];
            cells[i][nCells-1].rightTop = upperSplitPoints[i+1];
        }

        //concatenate first cell with lower triangle
        for (int i = 0; i < nLanes; i++) {
            cells[i][0].leftBottom = lowerSplitPoints[i];
            cells[i][0].rightBottom = lowerSplitPoints[i+1];
        }

        lanes = roadArea.splitIntoLanes(nLanes);
        //initialize lanes
        for (int i = 0; i < nLanes; i++) {
            road[i] = new Line(lanes[i], cells[i]);
        }

    }

    private void initializeNeighborhood(){
        for (int i = 0; i < nLanes; i++){
            Line lane = road[i];
            Point[] cells = lane.getCells();
            for (int j = 0; j < cells.length; j++){
                Point cell = cells[j];
                if (j > 0)
                    cell.addNeighbor(Direction.BACK, cells[j-1]);
                if (j < cells.length - 1)
                    cell.addNeighbor(Direction.FRONT, cells[j+1]);
                if (i > 0){
                    Line leftLane = road[i-1];
                    Point[] leftPoints = leftLane.getCells();
                    cell.addNeighbor(Direction.LEFT, leftPoints[j]);
                }
                if (i < nLanes-1){
                    Line rightLane = road[i+1];
                    Point[] rightPoints = rightLane.getCells();
                    cell.addNeighbor(Direction.RIGHT, rightPoints[j]);
                }
            }
        }
    }

    public boolean canAdd(StraightRoad road, Direction direction){
        if (direction == Direction.BACK || direction == Direction.FRONT && road.nLanes == this.nLanes)
            return true;
        if (direction == Direction.LEFT || direction == Direction.RIGHT && road.nLanes == this.nLanes)
            return true;
        return false;
    }

    public void addNeighbor(IRoadComponent neighbor, Direction direction){
        //add neighbor object to direction side of road
        roadNeighbors.put(direction, neighbor);
        switch (direction){
            case RIGHT:
                for (int i = 0; i < length; i++){

                }
        }
    }

    public Line[] getLanes(){
        return road;
    }

    public ArrayList<Point> getEntries(){
        ArrayList<Point> entries = new ArrayList<>();
        for (Line lane : road){
            Point entry = lane.getEntry();
            if (entry != null)
                entries.add(entry);
        }
        return entries;
    }

    public ArrayList<Point> getExits(){
        ArrayList<Point> exits = new ArrayList<>();
        for (Line lane : road){
            Point exit = lane.getExit();
            if (exit != null)
                exits.add(exit);
        }
        return exits;
    }

    @Override
    public String toString() {
        return "StraightRoad{" +
                "road=" + Arrays.toString(road) +
                '}';
    }
}
