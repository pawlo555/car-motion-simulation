import java.util.ArrayList;
import java.util.Collections;

public class Cell {
    public Line line;
    public int x;
    public int y;

    public ArrayList<Cell> neighbors =  new ArrayList<>();
    public Cell(Line line,int x,int y){
        this.line = line;
        this.x = x;
        this.y = y;
    }
}
