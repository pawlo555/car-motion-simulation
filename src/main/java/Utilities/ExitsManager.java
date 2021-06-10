package Utilities;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.TreeMap;

public class ExitsManager implements SimulationObserver {
    TreeMap<String, ExitStats> exitsStats = new TreeMap<>();

    /**
     * Create an exit object, TreeMap is generated from Exits found in CrossingMap
     * @throws IOException - thrown where not file in CrossingMap is found
     * @throws ParseException - thrown where parsed file has incorrect structure
     */
    public ExitsManager() throws IOException, ParseException {
        CrossingsMap crossingsMap = new CrossingsMap("src/main/resources/Utilities/Crossings");
        for(String crossingName: crossingsMap.getCrossingNames()) {
            String fileName = crossingsMap.getCrossingFile(crossingName);
            CrossingParser parser = new CrossingParser(fileName);
            for(String entrance: parser.getExitsNames()) {
                exitsStats.put(entrance, new ExitStats());
            }
        }
    }

    /**
     * When engine generate next epoch manager will inform all ExitsStat that epoch passed.
     */
    public void nextEpoch() {
        exitsStats.forEach((key, value) -> value.nextEpoch());
    }

    /**
     * When cars exit simulation engine should notify object on which exit it is.
     * @param exitName Name of the exit where is car
     */
    public void carOnExit(String exitName) {
        ExitStats exitStats = exitsStats.get(exitName);
        exitStats.carOnExit();
    }
}
