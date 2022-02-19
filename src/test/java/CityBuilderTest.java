import Utilities.CityBuilder;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CityBuilderTest {
    String directory = "src/main/resources/Roads/CounterwiseRoads";

    @Test
    public void buildFromDirectoryTest(){
        CityBuilder builder = new CityBuilder();
        builder.buildFromDirectory(directory);
    }

    @Test
    public void listfTest(){
        List<String> filePaths = new ArrayList<>();
        CityBuilder builder = new CityBuilder();
        builder.listf("src/main/resources/Crossing", filePaths);
        for (String file : filePaths){
            System.out.println(file);
        }
    }
}
