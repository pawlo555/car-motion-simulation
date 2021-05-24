package Utilities;

import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CrossingsMap {
    HashMap<String, File> map = new HashMap<>();

    public CrossingsMap(String directoryPath) throws IOException, ParseException {
        File directory = new File(directoryPath);
        File[] listFiles = directory.listFiles();
        assert listFiles != null;
        for (File file: listFiles) {
            System.out.println(file.getAbsolutePath());
            CrossingParser parser = new CrossingParser(file.getAbsolutePath());
            String crossingName = parser.getName();
            map.put(crossingName, file);
        }
    }

    public String getCrossingFile(String crossingName) {
        return map.get(crossingName).getPath();
    }

    public Set<String> getCrossingNames() {
        return map.keySet();
    }
}
