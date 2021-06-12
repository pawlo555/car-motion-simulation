import java.util.ArrayList;



public class exampleJunction {
    static Crossing createCrossing(){
        ArrayList<Road> crossingRoads= new ArrayList<>();
        Road roadA = createRoadA();
        crossingRoads.add(roadA);
        Road roadB = createRoadB();
        crossingRoads.add(roadB);
        Road roadC = createRoadC();
        crossingRoads.add(roadC);
        Road roadD = createRoadD();
        crossingRoads.add(roadD);
        Road roadE = createRoadE();
        crossingRoads.add(roadE);
        return new Crossing(crossingRoads,300,0,300,0,0);
    }

    static Road createRoadA(){
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> exits = new ArrayList<>();
        xList.add(1);
        yList.add(0);
        ArrayList<Integer> exits1 = new ArrayList<>();
        exits1.add(3);
        exits1.add(4);
        exits.add(exits1);
        return new Road(xList,yList,exits,1);
    }

    static Road createRoadB(){
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> exits = new ArrayList<>();
        xList.add(2);
        yList.add(0);
        ArrayList<Integer> exits1 = new ArrayList<>();
        exits1.add(5);
        exits.add(exits1);
        return new Road(xList,yList,exits,2);
    }

    static Road createRoadC(){
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> exits = new ArrayList<>();
        xList.add(0);
        yList.add(1);
        ArrayList<Integer> exits1 = new ArrayList<>();
        exits.add(exits1);
        return new Road(xList,yList,exits,3);
    }

    static Road createRoadD(){
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> exits = new ArrayList<>();
        xList.add(1);
        yList.add(1);
        ArrayList<Integer> exits1 = new ArrayList<>();
        exits.add(exits1);
        return new Road(xList,yList,exits,4);
    }

    static Road createRoadE(){
        ArrayList<Integer> xList = new ArrayList<>();
        ArrayList<Integer> yList = new ArrayList<>();
        ArrayList<ArrayList<Integer>> exits = new ArrayList<>();
        xList.add(2);
        yList.add(1);
        ArrayList<Integer> exits1 = new ArrayList<>();
        exits.add(exits1);
        return new Road(xList,yList,exits,5);
    }
}
