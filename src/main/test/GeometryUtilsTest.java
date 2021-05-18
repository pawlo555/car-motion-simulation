import Utilities.GeometryUtils;
import Utilities.Vector2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeometryUtilsTest {
    Vector2D v1 = new Vector2D(0,0);
    Vector2D v2 = new Vector2D(0,5);
    Vector2D v3 = new Vector2D(3,0);
    Vector2D v4 = new Vector2D(6,2);
    Vector2D v5 = new Vector2D(3,5);

    @Test
    public void perpendicularProjectionTest(){
        assertEquals(new Vector2D(0,0), GeometryUtils.perpendicularProjection(v1,v2,v3));
        assertEquals(new Vector2D(6,0), GeometryUtils.perpendicularProjection(v1,v3,v4));
        System.out.println(GeometryUtils.perpendicularProjection(v1,v5,v4));
    }

    @Test
    public void splitIntoPartsTest(){
        Vector2D[] parts = GeometryUtils.splitIntoParts(v1, v2, 5);
        for (Vector2D part : parts){
            System.out.println(part);
        }
    }

    @Test
    public void splitIntoPartsLenTest(){
        Vector2D[] parts = GeometryUtils.splitIntoPartsLen(v1, v2, 2);
        for (Vector2D part : parts){
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
