import Utilities.QuadrangleArea;
import Utilities.Vector2D;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuadrangleAreaTest {
    QuadrangleArea testArea = new QuadrangleArea(new Vector2D(0,0), new Vector2D(3,0),
                                                 new Vector2D(0,5), new Vector2D(3,5));
    QuadrangleArea testArea1 = new QuadrangleArea(new Vector2D(0,2), new Vector2D(3,0),
                                                  new Vector2D(0,5), new Vector2D(3,5));

    @Test
    public void testRectDecomposition(){
        QuadrangleArea rectArea = testArea.rectDecomposition();
        assertEquals(testArea, rectArea);
        rectArea = testArea1.rectDecomposition();
        System.out.println(rectArea);
    }

    @Test
    public void testSplitIntoLanes(){
        QuadrangleArea[] areas = testArea.splitIntoLanes(2);
        QuadrangleArea[] areas1 = testArea1.splitIntoLanes(2);
        for (QuadrangleArea area : areas){
            System.out.println(area);
        }for (QuadrangleArea area : areas1){
            System.out.println(area);
        }
    }

    @Test
    public void testSplitIntoCells(){
        QuadrangleArea[] areas = testArea.splitIntoCells(5);
        for (QuadrangleArea area : areas){
            System.out.println(area);
        }
    }

    @Test
    public void testSplitIntoLanesAndCells(){
        QuadrangleArea[] areas = testArea.splitIntoLanes(2);
        QuadrangleArea[][] cells = new QuadrangleArea[2][];
        for (int i = 0; i < 2; i++){
            cells[i] = areas[i].splitIntoCells(5);
        }
        for (int i = 0; i < 2; i++) {
            System.out.format("lane %d after splitting into cells:\n", i);
            for (int j = 0; j < cells[i].length; j++)
                System.out.println(cells[i][j]);
        }
    }
}
