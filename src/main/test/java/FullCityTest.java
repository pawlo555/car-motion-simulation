import Objects.City;
import Objects.Point;
import Utilities.CityBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FullCityTest {
    String directory = "src/main/resources/Roads/CounterwiseRoads";
    City city;

    @Before
    public void init(){
        CityBuilder builder = new CityBuilder();
        builder.buildFromDirectory("src/main/resources/Roads/ClockwiseRoads");
        builder.buildFromDirectory("src/main/resources/Roads/CounterwiseRoads");
        builder.buildFromDirectory("src/main/resources/Roads/CrossingRoads/Czarnowiejska");
        builder.buildIntersectionFromDirectory("src/main/resources/Crossing");
        builder.connectRoadsAndCrossings("src/main/resources/Connections");
        city = builder.getResult();
        builder.reset();
    }

    @Test
    public void initTest(){
        List<Point> connectedToCzarnowiejskaAGHEntrance = city.getExit("Czarnowiejska AGH");
        for (Point point : connectedToCzarnowiejskaAGHEntrance){
            System.out.println(point);
        }
    }
}
