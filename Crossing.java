import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.StrictMath.abs;

public class Crossing {
    // list of roads that are connected with crossing
    public ArrayList<Road> roads;
    // preliminary sizes of intersection -> we should change it later with some kind of bounds inside crossing and outside crossing (How?)
    public int heightTop;
    public int heightBottom;
    public int widthTop;
    public int widthBottom;
    public int number;
    private Cell[][] field;
    public ArrayList<Cell> enterList = new ArrayList<Cell>();
    public ArrayList<Cell> exitList = new ArrayList<Cell>();
    public ArrayList<ArrayList<Cell>> waysThroughIntersection = new ArrayList<ArrayList<Cell>>();




    public Crossing(ArrayList<Road> roads, int mapMaxH, int mapMinH, int mapMaxW, int mapMinW,int number){
        this.number = number;
        this.roads = roads;
        int maxX = mapMinH;
        int maxY = mapMinW;
        int minX = mapMaxH;
        int minY = mapMaxW;

        for (Road i : roads) {
            for (Line j : i.lines) {
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

        System.out.println("max x: " + heightTop);
        System.out.println("min x: " + heightBottom);
        System.out.println("max y: " + widthTop);
        System.out.println("min y: " + widthBottom);
        ArrayList<Cell> enters = new ArrayList<Cell>();
        ArrayList<Cell> exits = new ArrayList<Cell>();
        field = new Cell[1+this.heightTop - this.heightBottom][1+this.widthTop - this.widthBottom];
        for (int i = this.heightBottom; i < this.heightTop+1; i += 1) {
            for (int j = this.widthBottom; j < this.widthTop+1; j += 1) {
                Line accurateToCell = isLine(i, j);
                field[i - heightBottom][j - widthBottom] = new Cell(accurateToCell,i,j);
                if (accurateToCell != null && accurateToCell.ways.size() != 0) {
                    enters.add(field[i - heightBottom][j - widthBottom]);
                }
                if (accurateToCell != null && accurateToCell.ways.size() == 0) {
                    exits.add(field[i - heightBottom][j - widthBottom]);
                }
            }
        }
        for(Cell i : enters){
            System.out.println("coords line: " + i.line.x + " " + i.line.y + " Coords board " +  i.x + " " + i.y);
        }
        for(Cell i : exits){
            System.out.println("coords line: " + i.line.x + " " + i.line.y + " Coords board " +  i.x + " " + i.y);
        }
        // dla każdego wejścia wyszukuje najbliższe mu odpowiednie wyjście
        for (Cell i : enters) {
            for (int a : i.line.ways) {
                ArrayList<Cell> possibleExits = new ArrayList<Cell>();
                for (Cell j : exits) {
                    if (a == j.line.myRoad) {
                        possibleExits.add(j);
                    }
                }
                Cell myExit = null;
                if (possibleExits.size() == 1) {
                    myExit = possibleExits.get(0);

                } else if(possibleExits.size() != 0) {
                    Collections.shuffle(possibleExits);
                    myExit = possibleExits.get(0); // wywala się tutaj
                    int x0 = abs(myExit.line.x - i.line.x);
                    int y0 = abs(myExit.line.y - i.line.y);
                    for (Cell j : possibleExits) {
                        int x1 = abs(j.line.x - i.line.x);
                        int y1 = abs(j.line.x - i.line.x);
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

        for(int w=0;w<enterList.size();w+=1){

            System.out.println("enter " + enterList.get(w).x + " " + enterList.get(w).y + " exit " + exitList.get(w).x + " " + exitList.get(w).y);
        }
        System.out.println("------------");
        System.out.println(heightTop + " bot " + heightBottom);
        System.out.println(widthTop + " bot " + widthBottom);
        System.out.println("------------");
        // for tworzący liste komórek po jakich powinien przejechac samochód no to lecimy X D


        for(int a=0;a<enterList.size();a+=1) {
            ArrayList<Cell> wayThrough = new ArrayList<Cell>();
            int xEnter = enterList.get(a).line.x;
            int yEnter = enterList.get(a).line.y;
            int xExit = exitList.get(a).line.x;
            int yExit = exitList.get(a).line.y;

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
            System.out.println("------------");
            System.out.println("coords line str: " + x1 + " " + y1);
            System.out.println("coords line end: " + xExit + " " + yExit);
            if(goThroughX != -1){
                System.out.println("coords go through: " + goThroughX + " " + goThroughY);
            }

            if(xEnter == xExit) {
                if (yEnter > yExit) {
                    wayThrough = interYMinus(y1,yExit,x1,wayThrough);
                    wayThrough.add(field[xExit - heightBottom][yExit - widthBottom]);
                } else {
                    wayThrough = interYPlus(y1,yExit,x1,wayThrough);
                    wayThrough.add(field[xExit - heightBottom][yExit - widthBottom]);
                }
            }
            else if(yEnter == yExit){
                if(xEnter > xExit){
                    wayThrough = interXMinus(x1,xExit,y1,wayThrough);
                    wayThrough.add(field[xExit - heightBottom][yExit - widthBottom]);
                } else{
                    wayThrough = interXPlus(x1,xExit,y1,wayThrough);
                    wayThrough.add(field[xExit - heightBottom][yExit - widthBottom]);
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
                        wayThrough.add(field[xExit - heightBottom][yExit - widthBottom]);
                    } else if(yEnter < goThroughY) {
                        wayThrough = interYPlus(y1,goThroughY,x1,wayThrough);
                        if(xExit > goThroughX){
                            wayThrough = interXPlus(goThroughX,xExit,goThroughY,wayThrough);
                        } else {
                            wayThrough = interXMinus(goThroughX,xExit,goThroughY,wayThrough);
                        }
                        wayThrough.add(field[xExit - heightBottom][yExit - widthBottom]);
                    }
                } else if(goThroughY == yEnter){
                    if(xEnter > goThroughX){
                        wayThrough = interXMinus(x1,goThroughX,y1,wayThrough);
                        if(yExit < goThroughY){
                            wayThrough = interYMinus(goThroughY,yExit,goThroughX,wayThrough);
                        } else {
                            wayThrough = interYPlus(goThroughY,yExit,goThroughX,wayThrough);
                        }
                        wayThrough.add(field[xExit - heightBottom][yExit - widthBottom]);
                    } else{
                        wayThrough = interXPlus(x1,goThroughX,y1,wayThrough);
                        if(yExit < goThroughY){
                            wayThrough = interYMinus(goThroughY,yExit,goThroughX,wayThrough);
                        } else {
                            wayThrough = interYPlus(goThroughY,yExit,goThroughX,wayThrough);
                        }
                        wayThrough.add(field[xExit - heightBottom][yExit - widthBottom]);
                    }
                }
            }
            waysThroughIntersection.add(wayThrough);
        }
    }

    //returns line on which car should ends if it drives on road myRoad through this intersection to afterIntersection road
    public Line whichLine(int myRoadNumber,int afterIntersectionRoadNumber){
        ArrayList<Line> choice = new ArrayList<Line>();
        Road road1 = null;
        Road road2 = null;
        for(Road i: roads){
            if(i.number == myRoadNumber){
                road1 = i;
            } else if(i.number == afterIntersectionRoadNumber){
                road2 = i;
            }
        }
        if(road1 != null && road2 != null) {
            for(Line i: road1.lines){
                for(int j: i.ways){
                    if(j == afterIntersectionRoadNumber){
                        choice.add(i);
                        break;
                    }
                }
            }
            if(choice.size() != 0){
                Collections.shuffle(choice);
                return choice.get(0);
            }
        }
        return null;
    }

    // function returning a way that car will drive
    public Line isLine(int x,int y){
        for(Road i: roads){
            for(Line j:i.lines){
                if(j.x == x && j.y == y){
                    return j;
                }
            }
        }
        return null;
    }

    public int returnWay(Line myLine,int goingTo){
        return 0;
    }

    public ArrayList<Cell> interYPlus(int fromVal,int toVal,int onX,ArrayList<Cell> list){
        while(fromVal != toVal){
            list.add(field[onX - heightBottom][fromVal - widthBottom]);
            fromVal += 1;
        }
        return list;
    }

    public ArrayList<Cell> interYMinus(int fromVal,int toVal,int onX,ArrayList<Cell> list){
        while(fromVal != toVal){
            list.add(field[onX - heightBottom][fromVal - widthBottom]);
            fromVal -= 1;
        }
        return list;
    }

    public ArrayList<Cell> interXPlus(int fromVal,int toVal,int onY,ArrayList<Cell> list){
        while(fromVal != toVal){
            list.add(field[fromVal - heightBottom][onY - widthBottom]);
            fromVal += 1;
        }
        return list;
    }

    public ArrayList<Cell> interXMinus(int fromVal,int toVal,int onY,ArrayList<Cell> list){
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
}

