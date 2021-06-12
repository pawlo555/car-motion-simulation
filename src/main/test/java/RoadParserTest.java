import Objects.StraightRoad;
import Utilities.RoadParser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RoadParserTest {
    RoadParser parser = new RoadParser("src/main/resources/Roads/ClockwiseRoads/AlejaZygmuntaKrasinskiegoP.json");

    @Test
    public void getNameTest(){
        assertEquals("Aleja Zygmunta Krasinskiego P", parser.getName());
    }

    @Test
    public void getIdTest(){
        assertEquals(2, parser.getId());
    }

    @Test
    public void getNLanesTest(){
        assertEquals(2, parser.getNLanes());
    }

    @Test
    public void getRoadsTest(){
        ArrayList<StraightRoad> roads = parser.getRoads();
        for (StraightRoad road : roads){
            System.out.println(road);
        }
    }
}
