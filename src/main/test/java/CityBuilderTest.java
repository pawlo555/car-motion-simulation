import Utilities.CityBuilder;
import org.junit.Test;

public class CityBuilderTest {
    String directory = "src/main/resources/Roads/ClockwiseRoads";

    @Test
    public void buildFromDirectoryTest(){
        CityBuilder builder = new CityBuilder();
        builder.buildFromDirectory(directory);
    }
}
