package Utilities.Crossing;
import Objects.Point;
import Utilities.Direction;
import Utilities.PointType;
import Utilities.Vector2D;

import java.util.*;

import static java.lang.StrictMath.abs;

public class Crossing {
    // list of roads that are connected with crossing
    public ArrayList<EntrancesAndExits> roads;
    // preliminary sizes of intersection -> we should change it later with some kind of bounds inside crossing and outside crossing (How?)

    public Map<String,Integer> roadNames = new HashMap<>();
    public Point crossingPoint = new Point(new Vector2D(-1,-1));

    public Vector2D leftBottom;
    public Vector2D rightBottom;
    public Vector2D leftTop;
    public Vector2D rightTop;

    public int heightTop;
    public int heightBottom;
    public int widthTop;
    public int widthBottom;

    public int number;
    public String name;
    private CrossingCell[][] field;

    public ArrayList<CrossingCell> enterList = new ArrayList<>();
    public ArrayList<CrossingCell> exitList = new ArrayList<>();
    public ArrayList<ArrayList<CrossingCell>> waysThroughIntersection = new ArrayList<>();

    public ArrayList<Integer> enterRoads = new ArrayList<>();
    public ArrayList<Integer> exitRoads = new ArrayList<>();
    public ArrayList<ArrayList<Point>> ways = new ArrayList<>();

    public Crossing(int number,String name,Vector2D leftBottom, Vector2D rightBottom, Vector2D leftTop, Vector2D rightTop,ArrayList<EntrancesAndExits> roads){
        this.crossingPoint.setCrossing(this);
        crossingPoint.setType(PointType.CROSSING);
        this.number = number;
        this.name = name;
        this.roads = roads;
        this.leftBottom = leftBottom;
        this.leftTop = leftTop;
        this.rightBottom = rightBottom;
        this.rightTop = rightTop;

        int maxX = 0;
        int maxY = 0;
        int minX = 300;
        int minY = 300;
        for (EntrancesAndExits i : roads) {
            for (CrossingLane j : i.lanes) {
                if(maxX < j.x){
                    maxX = j.x;
                }
                if(maxY < j.y){
                    maxY = j.y;
                }
                if(minX > j.x){
                    minX = j.x;
                }
                if(minY > j.y){
                    minY = j.y;
                }
            }
        }

        this.heightTop = maxX;
        this.heightBottom = minX;
        this.widthTop = maxY;
        this.widthBottom = minY;
        initCrossing();

    }
    // create data structure with path from crossing
    private void initCrossing() {
        int sum0 = 0;
        int sum1 = 0;
        ArrayList<CrossingCell> enters = new ArrayList<>();
        ArrayList<CrossingCell> exits = new ArrayList<>();
        field = new CrossingCell[heightTop+1][widthTop+1];
        for (int i = 0; i < this.heightTop+1; i += 1) {
            for (int j = 0; j < this.widthTop+1; j += 1) {
                CrossingLane accurateToCell = isLine(i, j);
                field[i][j] = new CrossingCell(accurateToCell,i,j);
                if (accurateToCell != null && accurateToCell.ways.size() != 0) {
                    enters.add(field[i][j]);
                }
                if (accurateToCell != null && accurateToCell.ways.size() == 0) {
                    exits.add(field[i][j]);
                }
            }
        }
        ArrayList<Vector2D> points = new ArrayList<>();
        points.add(leftTop);
        points.add(rightTop);
        points.add(leftBottom);
        points.add(rightBottom);
        convertRectangle.convert(field,points);


        // dla każdego wejścia wyszukuje najbliższe mu odpowiednie wyjście
        for (CrossingCell i : enters) {
            for (int a : i.crossingLane.ways) {
                ArrayList<CrossingCell> possibleExits = new ArrayList<>();
                for (CrossingCell j : exits) {
                    if (a == j.crossingLane.myRoad) {
                        possibleExits.add(j);
                    }
                }
                CrossingCell myExit = null;
                if (possibleExits.size() == 1) {
                    myExit = possibleExits.get(0);

                } else if(possibleExits.size() != 0) {
                    Collections.shuffle(possibleExits);
                    myExit = possibleExits.get(0);
                    int x0 = abs(myExit.crossingLane.x - i.crossingLane.x);
                    int y0 = abs(myExit.crossingLane.y - i.crossingLane.y);
                    for (CrossingCell j : possibleExits) {
                        int x1 = abs(j.crossingLane.x - i.crossingLane.x);
                        int y1 = abs(j.crossingLane.x - i.crossingLane.x);
                        if((x1 *x1) + (y1*y1) < (x0*x0) + (y0*y0)){
                            myExit = j;
                            x0 = x1;
                            y0 = y1;
                        }
                    }
                }
                if (myExit != null) {
                    this.enterList.add(i);
                    this.exitList.add(myExit);
                }

            }
        }


        // for tworzący liste komórek po jakich powinien przejechac samochód



        for(int a=0;a<enterList.size();a+=1) {
            ArrayList<CrossingCell> wayThrough = new ArrayList<>();
            int xEnter = enterList.get(a).crossingLane.x;
            int yEnter = enterList.get(a).crossingLane.y;
            int xExit = exitList.get(a).crossingLane.x;
            int yExit = exitList.get(a).crossingLane.y;

            int goThroughX = -1;
            int goThroughY = -1;

            if(xEnter != xExit && yEnter != yExit){
                goThroughX = xExit;
                goThroughY = yEnter;
                if(isCorner(goThroughX,goThroughY) == 1){
                    goThroughX = xEnter;
                    goThroughY = yExit;
                }
            }

            int x1 = xEnter;
            int y1 = yEnter;


            if(xEnter == xExit) {
                if (yEnter > yExit) {
                    wayThrough = interYMinus(y1,yExit,x1,wayThrough);
                    wayThrough.add(field[xExit][yExit]);
                } else {
                    wayThrough = interYPlus(y1,yExit,x1,wayThrough);
                    wayThrough.add(field[xExit][yExit]);
                }
            }
            else if(yEnter == yExit){
                if(xEnter > xExit){
                    wayThrough = interXMinus(x1,xExit,y1,wayThrough);
                    wayThrough.add(field[xExit][yExit]);
                } else{
                    wayThrough = interXPlus(x1,xExit,y1,wayThrough);
                    wayThrough.add(field[xExit][yExit]);
                }
            }
            else if(goThroughX != -1){
                if(goThroughX == xEnter){
                    if(yEnter > goThroughY){
                        wayThrough = interYMinus(y1,goThroughY,x1,wayThrough);
                        if(xExit > goThroughX){
                            wayThrough = interXPlus(goThroughX,xExit,goThroughY,wayThrough);
                        } else {
                            wayThrough = interXMinus(goThroughX,xExit,goThroughY,wayThrough);
                        }
                        wayThrough.add(field[xExit][yExit]);
                    } else  {
                        wayThrough = interYPlus(y1,goThroughY,x1,wayThrough);
                        if(xExit > goThroughX){
                            wayThrough = interXPlus(goThroughX,xExit,goThroughY,wayThrough);
                        } else {
                            wayThrough = interXMinus(goThroughX,xExit,goThroughY,wayThrough);
                        }
                        wayThrough.add(field[xExit][yExit]);
                    }
                } else {
                    if(xEnter > goThroughX){
                        wayThrough = interXMinus(x1,goThroughX,y1,wayThrough);
                        if(yExit < goThroughY){
                            wayThrough = interYMinus(goThroughY,yExit,goThroughX,wayThrough);
                        } else {
                            wayThrough = interYPlus(goThroughY,yExit,goThroughX,wayThrough);
                        }
                        wayThrough.add(field[xExit][yExit]);
                    } else{
                        wayThrough = interXPlus(x1,goThroughX,y1,wayThrough);
                        if(yExit < goThroughY){
                            wayThrough = interYMinus(goThroughY,yExit,goThroughX,wayThrough);
                        } else {
                            wayThrough = interYPlus(goThroughY,yExit,goThroughX,wayThrough);
                        }
                        wayThrough.add(field[xExit][yExit]);
                    }
                }
            }
            waysThroughIntersection.add(wayThrough);
        }

        for(CrossingCell i : exitList){
            if(i.crossingLane != null){
                exitRoads.add(i.crossingLane.myRoad);
            }
        }

        for(CrossingCell i : enterList){
            if(i.crossingLane != null){
                enterRoads.add(i.crossingLane.myRoad);
            }
        }
        createPointWays();

    }

    // function returning a way that car will drive
    public CrossingLane isLine(int x, int y){
        for(EntrancesAndExits i: roads){
            for(CrossingLane j:i.lanes){
                if(j.x == x && j.y == y){
                    return j;
                }
            }
        }
        return null;
    }

    public ArrayList<CrossingCell> interYPlus(int fromVal, int toVal, int onX, ArrayList<CrossingCell> list){
        while(fromVal != toVal){
            list.add(field[onX - heightBottom][fromVal - widthBottom]);
            fromVal += 1;
        }
        return list;
    }

    public ArrayList<CrossingCell> interYMinus(int fromVal, int toVal, int onX, ArrayList<CrossingCell> list){
        while(fromVal != toVal){
            list.add(field[onX - heightBottom][fromVal - widthBottom]);
            fromVal -= 1;
        }
        return list;
    }

    public ArrayList<CrossingCell> interXPlus(int fromVal, int toVal, int onY, ArrayList<CrossingCell> list){
        while(fromVal != toVal){
            list.add(field[fromVal - heightBottom][onY - widthBottom]);
            fromVal += 1;
        }
        return list;
    }

    public ArrayList<CrossingCell> interXMinus(int fromVal, int toVal, int onY, ArrayList<CrossingCell> list){
        while(fromVal != toVal){
            list.add(field[fromVal - heightBottom][onY - widthBottom]);
            fromVal -= 1;
        }
        return list;
    }

    public int isCorner(int xC, int yC){
        if(xC == heightBottom && yC == widthBottom){
            return 1;
        } else if(xC == heightBottom && yC == widthTop){
            return 1;
        } else if(xC == heightTop && yC == widthTop){
            return 1;
        } else if(xC == heightTop && yC == widthBottom){
            return 1;
        }
        return 0;
    }

    public void createPointWays(){
        for(ArrayList<CrossingCell> i : waysThroughIntersection){
            ArrayList<Point> newList = new ArrayList<>();
            for(CrossingCell cell : i){
                newList.add(new Point(new Vector2D(cell.mapX, cell.mapY)));
            }
            this.ways.add(newList);
        }

        for(ArrayList<Point> i : ways){
            for(int j=0;j<i.size()-1;j+=1){
                i.get(j).addNeighbor(Direction.FRONT,i.get(j+1));
            }
        }
    }


    public Point getWay(int laneId,String roadName){
        int roadId = roadNames.get(roadName);
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i=0;i<enterList.size();i+=1) {
            CrossingLane ourLine = enterList.get(i).crossingLane;
            if (ourLine.id == laneId && ourLine.myRoad == roadId) {
                indexes.add(i);
            }
        }
        Collections.shuffle(indexes);
        int index = indexes.get(0);
        return(ways.get(index).get(0));
    }

    public void addExitNeighbour(int laneId,String roadName,Point point){
        int roadId = roadNames.get(roadName);
        ArrayList<Integer> indexes = new ArrayList<>();
        for(int i=0;i<exitList.size();i+=1) {
            CrossingLane ourLine = exitList.get(i).crossingLane;
            if (ourLine.id == laneId && ourLine.myRoad == roadId) {
                indexes.add(i);
            }
        }
        for(int i:indexes){
            ways.get(i).get(ways.get(i).size()-1).addNeighbor(Direction.FRONT,point);
        }

    }
}

