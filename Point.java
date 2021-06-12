
import java.util.Random;import java.util.ArrayList;
import java.util.Collections;

public class Point {

    public ArrayList<Point> neighbors;
    public static Integer []types ={0,1,2,3,4};
    public int type;
    public int staticField;
    public int nextState;
    public boolean isWall;
    public boolean isFire;
    public boolean toMuchSmoke;
    public boolean toMuchSmokeNext;
    public boolean closeToWall;
    public Random rand = new Random();

    public Point() {
        type=0;
        nextState = type;
        staticField = 100000;
        neighbors= new ArrayList<Point>();
        isWall = false;
        isFire = false;
        closeToWall = false;
        toMuchSmoke = false;
    }

    public void clear() {
        staticField = 100000;

    }

    public void move(){
        ArrayList<Point> bestWay = new ArrayList<Point>();
        ArrayList<Point> bestWayWall = new ArrayList<Point>();
        ArrayList<Point> bestWaySmoke = new ArrayList<Point>();
        ArrayList<Point> secondWay = new ArrayList<Point>();
        ArrayList<Point> secondWayWall = new ArrayList<Point>();
        ArrayList<Point> avaible = new ArrayList<Point>();
        Point moveTo = null;

        for(Point i : neighbors){
            if(i.isFire == false && i.isWall == false && (i.nextState == 0 ||  i.nextState == 2) && i.type != 1){
                avaible.add(i);
            }
        }
        int a = this.staticField;
        for(Point i : avaible){
            if(i.staticField < a ){
                a = i.staticField;
            }
        }
        if(this.staticField > a ){
            for(Point i : avaible) {
                if (i.staticField == a && i.closeToWall == false && i.toMuchSmoke == false) {
                    bestWay.add(i);
                } else if (i.staticField == a && i.closeToWall == true && i.toMuchSmoke == false) {
                    bestWayWall.add(i);
                }else if (i.staticField == a) {
                        bestWaySmoke.add(i);
                } else if (i.staticField == a + 1 && i.closeToWall == false && i.toMuchSmoke == false) {
                    secondWay.add(i);
                } else if (i.staticField == a + 1 && i.closeToWall == true && i.toMuchSmoke == false) {
                    secondWayWall.add(i);
                }
            }
        }
        else if(this.staticField == a){
            for(Point i : avaible){
                if (i.staticField == a + 1 && i.closeToWall == false) {
                    secondWay.add(i);
                } else if (i.staticField == a + 1 && i.closeToWall == true) {
                    secondWayWall.add(i);
                }
            }
        }
        Collections.shuffle(bestWay);
        Collections.shuffle(bestWayWall);
        Collections.shuffle(secondWay);
        Collections.shuffle(secondWayWall);
        Collections.shuffle(bestWaySmoke);
        float doIWannamove = rand.nextFloat();
        if(bestWay.size() != 0){
            moveTo = bestWay.get(0);
        }
        else if(bestWayWall.size() != 0){
            moveTo = bestWayWall.get(0);
        }
        else if(bestWaySmoke.size() != 0){
            moveTo = bestWaySmoke.get(0);
        }
        else if(doIWannamove > 0.7){
            if(secondWay.size() != 0){
                moveTo = secondWay.get(0);
            }
            else if(secondWayWall.size() != 0){
                moveTo = secondWayWall.get(0);
            }
        }
        if(moveTo != null && moveTo.staticField == 0){
            this.nextState = 0;
        }
        else if(moveTo != null){
            this.nextState = 0;
            moveTo.nextState = 1;
        }
        else{
            this.nextState = 1;
        }
    }
    public void spreadSmoke(){
        if(this.isFire == true){
            for(Point i : this.neighbors) {
                i.toMuchSmokeNext = true;
            }
        }
        else if(this.toMuchSmoke == true){
            for(Point i : this.neighbors){
                float r = rand.nextFloat();
                if(r > 0.9){
                    i.toMuchSmokeNext = true;
                }
            }
            float r = rand.nextFloat();
            if(r > 0.5){
                this.toMuchSmokeNext = false;
            }
        }

    }
    public void setNextState(){
        if(this.toMuchSmoke == true){
            this.staticField -= 30;
        }
        this.toMuchSmoke = this.toMuchSmokeNext;
        if(this.toMuchSmoke == true){
            this.staticField += 30;
        }
        this.type = this.nextState;

    }


    public void addNeighbor(Point nei) {
        neighbors.add(nei);
    }
}