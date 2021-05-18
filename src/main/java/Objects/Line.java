package Objects;

import Utilities.Geometry.QuadrangleArea;

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
        }

    }

    public Point[] getCells(){
        return cells;
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
