import Objects.Line;
import Objects.Point;
import Objects.StraightRoad;
import Utilities.QuadrangleArea;
import Utilities.Vector2D;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StraightRoadTest {

    @Test
    public void createRoadTest(){
        QuadrangleArea roadArea = new QuadrangleArea(new Vector2D(0,0), new Vector2D(3, 0), new Vector2D(0,5), new Vector2D(3,5));
        StraightRoad testRoad = new StraightRoad(2, roadArea);
        for (Line lane : testRoad.getLanes()){
            System.out.println(lane.getArea());
            for (Point cell : lane.getCells()){
                System.out.println(cell.getPosition());
            }
            System.out.println();
        }
    }

    @Test
    public void createTrapezoidRoadTest(){
        QuadrangleArea roadArea1 = new QuadrangleArea(new Vector2D(0,0), new Vector2D(3, 0), new Vector2D(0,10), new Vector2D(3,12));
        StraightRoad testRoadTrapezoid = new StraightRoad(2, roadArea1);
        for (Line lane : testRoadTrapezoid.getLanes()){
            System.out.println(lane.getArea());
            for (Point cell : lane.getCells()){
                System.out.println(cell.getPosition());
            }
            System.out.println();
        }
    }

    @Test
    public void createRandomRoadTest(){
        QuadrangleArea randomArea = new QuadrangleArea(new Vector2D(120,0), new Vector2D(135, 30), new Vector2D(0,150), new Vector2D(15,190));
        StraightRoad testRandomRoad = new StraightRoad(2, randomArea);
        for (Line lane : testRandomRoad.getLanes()){
            System.out.println(lane.getArea());
            for (Point cell : lane.getCells()){
                System.out.println(cell.getPosition());
            }
            System.out.println();
        }
    }

    @Test
    public void NeighborhoodTest(){
        QuadrangleArea randomArea = new QuadrangleArea(new Vector2D(120,0), new Vector2D(135, 30), new Vector2D(0,150), new Vector2D(15,190));
        StraightRoad testRandomRoad = new StraightRoad(2, randomArea);
        for (Line lane : testRandomRoad.getLanes()){
            System.out.println(lane.getArea());
            for (Point cell : lane.getCells()){
                System.out.println(cell);
            }
            System.out.println();
        }
    }

    @Test
    public void EntriesTest(){
        QuadrangleArea randomArea = new QuadrangleArea(new Vector2d(120,0), new Vector2d(135, 30), new Vector2d(0,150), new Vector2d(15,190));
        StraightRoad testRandomRoad = new StraightRoad(2, randomArea);
        for (Point point : testRandomRoad.getEntries()){
            System.out.println(point+" is entry.");
        }
    }

    @Test
    public void ExitsTest(){
        QuadrangleArea randomArea = new QuadrangleArea(new Vector2d(120,0), new Vector2d(135, 30), new Vector2d(0,150), new Vector2d(15,190));
        StraightRoad testRandomRoad = new StraightRoad(2, randomArea);
        for (Point point : testRandomRoad.getExits()){
            System.out.println(point+" is exit.");
        }
    }
}
