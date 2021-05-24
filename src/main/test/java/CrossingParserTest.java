import Utilities.CrossingParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class CrossingParserTest {
    @Test
    public void NameTest() {
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
}
