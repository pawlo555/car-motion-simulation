//package Objects;
//
//import java.util.ArrayList;
//
//// road 1 way in way out road with lines
//public class Road {
//    public int number;
//    public ArrayList<Line> lines = new ArrayList<Line>();
//
//    public Road(ArrayList<Integer> x,ArrayList<Integer> y,ArrayList<ArrayList<Integer>> ways,int number){
//        this.number = number;
//        for(int i =0;i< x.size();i+=1){
//            Line line = new Line(x.get(i),y.get(i),ways.get(i),number);
//            this.lines.add(line);
//        }
//    }
//
//    //
//    public void addLineToRoad(int x,int y,ArrayList<Integer> ways){
//        Line line = new Line(x,y,ways,number);
//        this.lines.add(line);
//    }
//
//}
