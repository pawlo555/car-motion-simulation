package Utilities;

import Utilities.Crossing.Crossing;

public class IntersectionBuilder {
    private Crossing crossing;
    private IntersectionParser parser;

    public void reset(){
        crossing = null;
    }

    public Crossing getResult(){
        return crossing;
    }

    public void buildFromJSON(String filename){
        this.parser = new IntersectionParser(filename);
        crossing = parser.getCrossing();
    }
}
