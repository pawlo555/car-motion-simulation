import Objects.StraightRoad;
import Utilities.Crossing.Crossing;
import Utilities.IntersectionParser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class IntersectionParserTest {
    IntersectionParser parser = new IntersectionParser("src/main/resources/Crossing/Czarnowiejska/CzarnowiejskaAGH.json");

    @Test
    public void getNameTest(){
        assertEquals("Czarnowiejska AGH", parser.getName());
    }

    @Test
    public void getIdTest(){
        assertEquals(1, parser.getId());
    }

    @Test
    public void getCrossingTest(){
        Crossing example = parser.getCrossing();
        System.out.println(example.waysThroughIntersection.size());
        for(int j = 0; j < example.enterList.size();j+=1){
            //System.out.println("-------------------");
            for(int i = 0;i< example.waysThroughIntersection.get(j).size();i+=1){
                System.out.println("(" + example.waysThroughIntersection.get(j).get(i).mapX + "," + example.waysThroughIntersection.get(j).get(i).mapY + ")");
            }
        }
    }
}