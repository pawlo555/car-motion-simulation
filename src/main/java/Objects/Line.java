package Objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Line {
    // co-ordinates of line
    public int x;
    public int y;
    // list of lines that car can choose from and drive to
    public ArrayList<Integer> ways;
    // bolean if this line have no ways that means it is exit from intersection
    public boolean isEntry;
    public int myRoad;

    public Line(int x,int y,ArrayList<Integer> ways,int road){
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
