import Objects.City;
import Objects.SuperRoad;
import Utilities.CityBuilder;
import Utilities.SuperRoadBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SimulationCityTest {
    CityBuilder builder = new CityBuilder();
    TestSimulationCity simulation;

    @Before
    public void init(){
        ArrayList<String> paths = new ArrayList<>();
        paths.add("src/main/resources/Roads/ClockwiseRoads/MostDebnickiP.json");
        paths.add("src/main/resources/Roads/ClockwiseRoads/AlejaZygmuntaKrasinskiegoP.json");
        paths.add("src/main/resources/Roads/ClockwiseRoads/AlejaAdamaMickiewiczaP1.json");
        paths.add("src/main/resources/Roads/ClockwiseRoads/AlejaAdamaMickiewiczaP2.json");
        builder.buildRoads(paths);
        builder.connectRoads();
        City city = builder.getResult();
        builder.reset();
        simulation = new TestSimulationCity(city);
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
