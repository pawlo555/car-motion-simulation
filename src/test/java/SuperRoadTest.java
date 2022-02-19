import Objects.Point;
import Objects.SuperRoad;
import Utilities.SuperRoadBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SuperRoadTest {
    SuperRoadBuilder builder = new SuperRoadBuilder();
    SuperRoad superRoad;

    @Before
    public void prepare(){
//        builder.buildFromJSON("src/main/resources/Roads/ClockwiseRoads/AlejaZygmuntaKrasinskiegoP.json");
//        builder.buildFromJSON("src/main/resources/Roads/CrossingRoads/Czarnowiejska/CzarnowiejskaRynekAGH.json");
//        builder.buildFromJSON("src/main/resources/Roads/ClockwiseRoads/MostDebnickiP.json");
        builder.buildFromJSON("src/main/resources/Roads/ClockwiseRoads/AlejaAdamaMickiewiczaP1.json");
//        builder.buildFromJSON("src/main/resources/Roads/ClockwiseRoads/AlejaAdamaMickiewiczaP2.json");
        superRoad = builder.getResult();
        builder.reset();
    }

    @Test
    public void getEntriesTest(){
        for (Point entry : superRoad.getEntries()){
            System.out.println(entry);
        }
    }

    @Test
    public void getExitsTest(){
        for (Point exit : superRoad.getExits()){
            System.out.println(exit);
        }
    }

    @Test
    public void printFullPointsTest(){
        superRoad.printFullPoints();
    }
}
