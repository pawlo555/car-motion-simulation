import Objects.Line;
import Objects.Point;
import Objects.StraightRoad;
import Utilities.QuadrangleArea;
import Utilities.Vector2D;

import org.junit.Before;
import org.junit.Test;

public class SimulationTest {
    TestSimulation simulation;

    @Before
    public void init(){
        QuadrangleArea area = new QuadrangleArea(new Vector2D(120,0), new Vector2D(135, 30), new Vector2D(0,150), new Vector2D(15,190));
        StraightRoad road = new StraightRoad(2, area);
        simulation = new TestSimulation(road);
        for (Line lane : road.getLanes()){
            System.out.println(lane.getArea());
            for (Point cell : lane.getCells()){
                System.out.println(cell);
            }
            System.out.println();
        }
    }

    @Test
    public void noCarTest(){
        for (int i = 0 ; i < 30; i++){
            simulation.iterate();
            simulation.print();
        }
    }

    @Test
    public void oneCarTest(){
        for (int i = 0 ; i < 30; i++){
            if (i == 5)
                simulation.spawnCar();
            simulation.iterate();
            simulation.print();
            System.out.println();
        }
    }

    @Test
    public void twoCarsTest(){
        for (int i = 0 ; i < 30; i++){
            if (i == 5 || i == 7)
                simulation.spawnCar();
            simulation.iterate();
            simulation.print();
            System.out.println();
        }
    }

    @Test
    public void oneBusTest(){
        for (int i = 0 ; i < 30; i++){
            if (i == 5)
                simulation.spawnBus();
            simulation.iterate();
            simulation.print();
            System.out.println();
        }
    }
}
