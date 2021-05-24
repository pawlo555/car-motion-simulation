import Objects.Line;
import Objects.Point;
import Objects.StraightRoad;
import Utilities.Direction;
import Utilities.QuadrangleArea;
import Utilities.RoadRoadConnector;
import Utilities.Vector2D;
import org.junit.Test;

public class RoadRoadConnectorTest {

    @Test
    public void simpleLeftConnectTest(){
        QuadrangleArea area1 = new QuadrangleArea(new Vector2D(0,0), new Vector2D(3,0), new Vector2D(0,10), new Vector2D(3,10));
        QuadrangleArea area2 = new QuadrangleArea(new Vector2D(0,10), new Vector2D(3,10), new Vector2D(0,20), new Vector2D(3,20));
        StraightRoad road1 = new StraightRoad(2, area1);
        StraightRoad road2 = new StraightRoad(2, area2);

        RoadRoadConnector.connectFront(road1, road2, Direction.LEFT);
        System.out.println(road1);
        System.out.println(road2);

    }
}
