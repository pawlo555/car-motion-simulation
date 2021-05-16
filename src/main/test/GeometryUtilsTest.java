import Utilities.GeometryUtils;
import Utilities.Vector2d;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeometryUtilsTest {
    Vector2d v1 = new Vector2d(0,0);
    Vector2d v2 = new Vector2d(0,5);
    Vector2d v3 = new Vector2d(3,0);
    Vector2d v4 = new Vector2d(6,2);
    Vector2d v5 = new Vector2d(3,5);

    @Test
    public void perpendicularProjectionTest(){
        assertEquals(new Vector2d(0,0), GeometryUtils.perpendicularProjection(v1,v2,v3));
        assertEquals(new Vector2d(6,0), GeometryUtils.perpendicularProjection(v1,v3,v4));
        System.out.println(GeometryUtils.perpendicularProjection(v1,v5,v4));
    }

    @Test
    public void splitIntoPartsTest(){
        Vector2d[] parts = GeometryUtils.splitIntoParts(v1, v2, 5);
        for (Vector2d part : parts){
            System.out.println(part);
        }
    }

    @Test
    public void splitIntoPartsLenTest(){
        Vector2d[] parts = GeometryUtils.splitIntoPartsLen(v1, v2, 2);
        for (Vector2d part : parts){
            System.out.println(part);
        }
    }

    @Test
    public void countTirangleAreaTest(){
        assertEquals(7.5, GeometryUtils.countTirangleArea(v1, v3, v2), 0.001);
    }

    @Test
    public void countRectangleAreaTest(){
        assertEquals(15, GeometryUtils.countRectangleArea(v1, v3, v2, v5), 0.001);
    }

}
