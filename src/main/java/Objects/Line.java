package Objects;


import Utilities.PointType;
import Utilities.Geometry.QuadrangleArea;

import java.util.ArrayList;

public class Line {
    private QuadrangleArea lane;
    // list of lines that car can choose from and drive to
    public Point[] cells;
    // bolean if this line have no ways that means it is exit from intersection
    public boolean isEntry;
    public int myRoad;

    public Line(QuadrangleArea lane, QuadrangleArea[] points){
        this.lane = lane;
        initalize(points);
    }

    private void initalize(QuadrangleArea[] points){
        cells = new Point[points.length];
        for (int i = 0; i < points.length; i++){
            cells[i] = new Point(points[i].getMiddle());
            if (i == 0)
                cells[i].setType(PointType.SIMULATION_ENTRY);
            else if (i == points.length-1)
                cells[i].setType(PointType.SIMULATION_EXIT);
        }

    }

    public Point[] getCells(){
        return cells;
    }

    public Point getEntry(){
        if (cells[0].getType() == PointType.SIMULATION_ENTRY)
            return cells[0];
        else
            return null;
    }

    public Point getExit(){
        if (cells[cells.length-1].getType() == PointType.SIMULATION_EXIT)
            return cells[cells.length-1];
        else
            return null;
    }

    public QuadrangleArea getArea(){
        return lane;
    }

//    public Line(int x,int y,ArrayList<Integer> ways,int road){
//        this.x = x;
//        this.y = y;
//        this.ways = ways;
//        this.myRoad = road;
//        if(ways.size() == 0){
//            isEntry = false;
//        } else{
//            isEntry = true;
//        }
//    }


}
