import Utilities.CrossingParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class CrossingParserTest {
    @Test
    public void nameTest() {
        boolean exception = false;
        try {
            CrossingParser parser = new CrossingParser("src/main/test/resources/testCrossing.json");
            assertEquals("Rondo", parser.getName());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            exception = true;
        }
        assertFalse(exception);
    }

    @Test
    public void flowTest() {
        boolean exception = false;
        try {
            CrossingParser parser = new CrossingParser("src/main/test/resources/testCrossing.json");
            assertEquals(90, parser.getFlow("3-ego Maja"));
            assertEquals(150, parser.getFlow("Sienkiewicza"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            exception = true;
        }
        assertFalse(exception);
    }

    @Test
    public void busProbabilityTest() {
        boolean exception = false;
        try {
            CrossingParser parser = new CrossingParser("src/main/test/resources/testCrossing.json");
            assertEquals(0.01, parser.getBusProbability("3-ego Maja"), 0.01);
            assertEquals(0.1, parser.getBusProbability("Sienkiewicza"), 0.0);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            exception = true;
        }
        assertFalse(exception);
    }

    @Test
    public void maxSpeedTest() {
        boolean exception = false;
        try {
            CrossingParser parser = new CrossingParser("src/main/test/resources/testCrossing.json");
            assertEquals(50, parser.getMaxSpeed("3-ego Maja"));
            assertEquals(60, parser.getMaxSpeed("Sienkiewicza"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            exception = true;
        }
        assertFalse(exception);
    }

    @Test
    public void maxIdTest() {
        boolean exception = false;
        try {
            CrossingParser parser = new CrossingParser("src/main/test/resources/testCrossing.json");
            assertEquals(1, parser.getId("3-ego Maja"));
            assertEquals(2, parser.getId("Sienkiewicza"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            exception = true;
        }
        assertFalse(exception);
    }

    @Test
    public void positionOnMapTest() {
        boolean exception = false;
        try {
            CrossingParser parser = new CrossingParser("src/main/test/resources/testCrossing.json");
            assertEquals(1, parser.getHorizontalPosition("14"));
            assertEquals(2, parser.getHorizontalPosition("14"));
            assertEquals(3, parser.getHorizontalPosition("15"));
            assertEquals(4, parser.getHorizontalPosition("15"));
            assertEquals(5, parser.getHorizontalPosition("16"));
            assertEquals(6, parser.getHorizontalPosition("16"));
            assertEquals(7, parser.getHorizontalPosition("17"));
            assertEquals(8, parser.getHorizontalPosition("17"));
            assertEquals(9, parser.getHorizontalPosition("18"));
            assertEquals(10, parser.getHorizontalPosition("18"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            exception = true;
        }
        assertFalse(exception);
    }

}
