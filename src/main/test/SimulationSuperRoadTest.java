import Objects.SuperRoad;
import Utilities.SuperRoadBuilder;
import org.junit.Before;
import org.junit.Test;

public class SimulationSuperRoadTest {
    SuperRoadBuilder builder = new SuperRoadBuilder();
    TestSimulationSuperRoad simulation;

    @Before
    public void init(){
        builder.buildFromJSON("src/main/resources/Roads/ClockwiseRoads/AlejaZygmuntaKrasinskiegoP.json");
        SuperRoad superRoad = builder.getResult();
        builder.reset();
        simulation = new TestSimulationSuperRoad(superRoad);
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
        for (int i = 0 ; i < 300; i++){
            if (i == 5)
                simulation.spawnCar();
            simulation.iterate();
            simulation.print();
//            System.out.println();
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
