package Objects;

import java.util.ArrayList;
import java.util.Collections;

public class Cell {
    public Line line;

    public ArrayList<Cell> neighbors =  new ArrayList<>();


    public Cell(Line line){
        this.line = line;
    }
}
