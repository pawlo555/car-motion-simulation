import Utilities.CrossingParser;
import Utilities.CrossingsMap;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class CrossingsMapTest {
    private final static String path = "src\\main\\test\\resources";

    @Test
    public void getCrossingPathTest() {
        boolean exception = false;
        try {
            CrossingsMap crossingsMap = new CrossingsMap(path);
            assertEquals(path + "\\saved.json", crossingsMap.getCrossingFile("Rondo2"));
            assertEquals(path + "\\testCrossing.json", crossingsMap.getCrossingFile("Rondo"));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            exception = true;
        }
        assertFalse(exception);
    }

    @Test
    public void getCrossingNamesTest() {
        boolean exception = false;
        try {
            CrossingsMap crossingsMap = new CrossingsMap(path);
            Set<String> namesSet = crossingsMap.getCrossingNames();
            assertTrue(namesSet.contains("Rondo"));
            assertTrue(namesSet.contains("Rondo"));
            assertEquals(2,namesSet.size());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            exception = true;
        }
        assertFalse(exception);
    }
}
