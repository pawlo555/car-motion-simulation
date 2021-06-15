package Utilities;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

public class ExitsManager implements SimulationObserver {
    TreeMap<String, ExitStats> exitsStats = new TreeMap<>();

    /**
     * Create an exit object, TreeMap is generated from Exits found in CrossingMap
     * @throws IOException - thrown where not file in CrossingMap is found
     * @throws ParseException - thrown where parsed file has incorrect structure
     * @param directoryPath - path to directory containing info about exits
     */
    public ExitsManager(String directoryPath) throws IOException, ParseException {
        CrossingsMap crossingsMap = new CrossingsMap(directoryPath);
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
        System.out.println("Exits manager new epoch");
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

    /**
     * Returns data from N last epoch from selected exit
     * @param N Number of epoch to get data from
     * @param exitName Name of the exit to take data from
     * @return list of last N epoch flow.
     */
    public List<Integer> getLastNEpoch(int N, String exitName) {
        return exitsStats.get(exitName).getLastNEpoch(N);
    }
}
