//package Objects;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Collections;
//
//import static java.lang.StrictMath.abs;
//
//public class Crossing {
//    // list of roads that are connected with crossing
//    public ArrayList<Road> roads;
//    // preliminary sizes of intersection -> we should change it later with some kind of bounds inside crossing and outside crossing (How?)
//    public int heightTop;
//    public int heightBottom;
//    public int widthTop;
//    public int widthBottom;
//    private Cell[][] field;
//    public ArrayList<Cell> enterList = new ArrayList<Cell>();
//    public ArrayList<Cell> exitList = new ArrayList<Cell>();
//    public ArrayList<ArrayList<Cell>> waysThroughIntersection = new ArrayList<ArrayList<Cell>>();
//
//
//
//
//    public Crossing(ArrayList<Road> roads, int mapMaxH, int mapMinH, int mapMaxW, int mapMinW){
//        this.roads = roads;
//        int maxHeight = mapMinH;
//        int minHeight  = mapMaxH;
//        int maxWidth = mapMinW;
//        int minWidth  = mapMaxW;
//        for(Road i: roads){
//            for(Line j: i.lines){
//                if(j.y > maxHeight){
//                    maxHeight = j.y;
//                }
//                if(j.y < minHeight){
//                    minHeight = j.y;
//                }
//                if(j.x > maxWidth){
//                    maxHeight = j.x;
//                }
//                if(j.x < minWidth){
//                    minWidth = j.x;
//                }
//            }
//        }
//        this.heightTop = maxHeight;
//        this.heightBottom = minHeight;
//        this.widthTop = maxWidth;
//        this.widthBottom = minWidth;
//        initCrossing();
//
//    }
//    // create data structure with path from crossing
//    private void initCrossing() {
//        ArrayList<Cell> enters = new ArrayList<Cell>();
//        ArrayList<Cell> exits = new ArrayList<Cell>();
//        // towrzy pole field wypełnione komórkami
//        for (int i = this.heightBottom; i < this.heightTop; i += 1) {
//            for (int j = this.widthBottom; j < this.widthTop; j += 1) {
//                Line accurateToCell = isLine(i, j);
//                field[i - heightBottom][j - widthBottom] = new Cell(accurateToCell);
//                if (accurateToCell != null && accurateToCell.ways.size() != 0) {
//                    enters.add(field[i - heightBottom][j - widthBottom]);
//                }
//                if (accurateToCell != null && accurateToCell.ways.size() == 0) {
//                    exits.add(field[i - heightBottom][j - widthBottom]);
//                }
//            }
//        }
//        // dla każdego wejścia wyszukuje najbliższe mu odpowiednie wyjście
//        for (Cell i : enters) {
//            for (int a : i.line.ways) {
//                ArrayList<Cell> possibleExits = new ArrayList<Cell>();
//                for (Cell j : exits) {
//                    if (a == j.line.myRoad) {
//                        possibleExits.add(j);
//                    }
//                }
//                Cell myExit = null;
//                if (possibleExits.size() == 1) {
//                    myExit = possibleExits.get(0);
//                } else {
//                    Collections.shuffle(possibleExits);
//                    myExit = possibleExits.get(0);
//                    int x0 = abs(myExit.line.x - i.line.x);
//                    int y0 = abs(myExit.line.y - i.line.y);
//                    for (Cell j : possibleExits) {
//                        int x1 = abs(j.line.x - i.line.x);
//                        int y1 = abs(j.line.x - i.line.x);
//                        if((x1 *x1) + (y1*y1) < (x0*x0) + (y0*y0)){
//                            myExit = j;
//                            x0 = x1;
//                            y0 = y1;
//                        }
//                    }
//                }
//                if (myExit != null) {
//                    this.enterList.add(i);
//                    this.exitList.add(myExit);
//                }
//
//            }
//        }
//        // for tworzący liste komórek po jakich powinien przejechac samochód
//        for(int a=0;a<enterList.size();a+=1) {
//            ArrayList<Cell> wayThrough = new ArrayList<Cell>();
//            int xEnter = enterList.get(a).line.x;
//            int yEnter = enterList.get(a).line.y;
//            int xExit = exitList.get(a).line.x;
//            int yExit = exitList.get(a).line.y;
//            int filedXEntry = 0;
//            int filedYEntry = 0;
//            int filedXExit = 0;
//            int filedYExit = 0;
//            int goThroughX = 0;
//            int goThroughY = 0;
//            for (int i = this.heightBottom; i < this.heightTop; i += 1) {
//                for (int j = this.widthBottom; j < this.widthTop; j += 1) {
//                    Cell possibleCell = field[i - heightBottom][j - widthBottom];
//                    if(possibleCell.line.x == xEnter && possibleCell.line.y == yExit){
//                        if((i != this.heightBottom || j != this.widthBottom) && (i != this.heightBottom || j != this.widthTop-1) && (i != this.heightTop -1 || j != this.widthTop -1) && (i != this.heightTop -1 || j != this.widthBottom)){
//                            goThroughX = i;
//                            goThroughY = j;
//                        }
//
//                    } else if(possibleCell.line.x == xExit && possibleCell.line.y == yEnter){
//                        if((i != this.heightBottom || j != this.widthBottom) && (i != this.heightBottom || j != this.widthTop-1) && (i != this.heightTop -1 || j != this.widthTop -1) && (i != this.heightTop -1 || j != this.widthBottom)){
//                            goThroughX = i;
//                            goThroughY = j;
//                        }
//
//                    } else if(possibleCell.line.x == xEnter && possibleCell.line.y == yEnter){
//                        filedXEntry = i;
//                        filedYEntry = j;
//
//                    } else if(possibleCell.line.x == xExit && possibleCell.line.y == yExit){
//                        filedXExit = i;
//                        filedYExit = j;
//
//                    }
//                }
//            }
//            int x1 = filedXEntry;
//            int y1 = filedYEntry;
//
//            // potężny if tworzący liste komórek po jakich powinien przejechać samochód
//
//            if(filedXEntry == filedXExit){
//                if(filedYEntry > filedYExit){
//                    while(y1 != filedYExit){
//                        wayThrough.add(field[x1][y1]);
//                        y1 -= 1;
//                    }
//                } else{
//                    while(y1 != filedYExit){
//                        wayThrough.add(field[x1][y1]);
//                        y1 += 1;
//                    }
//                }
//            } else if(filedYEntry == filedYExit){
//                if(filedXEntry > filedXExit){
//                    while(x1 != filedXExit){
//                        wayThrough.add(field[x1][y1]);
//                        x1 -= 1;
//                    }
//                    wayThrough.add(field[x1][y1]);
//                } else{
//                    while(x1 != filedXExit){
//                        wayThrough.add(field[x1][y1]);
//                        x1 += 1;
//                    }
//                    wayThrough.add(field[x1][y1]);
//                }
//            } else{
//                if(goThroughX == filedXEntry){
//                    if(filedYEntry > goThroughY){
//                        while(y1 != filedYExit){
//                            wayThrough.add(field[x1][y1]);
//                            y1 -= 1;
//                        }
//                        wayThrough.add(field[x1][y1]);
//                        if(filedXEntry > goThroughX){
//                            while(x1 != filedYExit){
//                                wayThrough.add(field[x1][y1]);
//                                x1 -= 1;
//                            }
//                        } else {
//                            while(x1 != filedXExit){
//                                wayThrough.add(field[x1][y1]);
//                                x1 += 1;
//                            }
//                        }
//                        wayThrough.add(field[x1][y1]);
//                    } else{
//                        while(y1 != filedYExit){
//                            wayThrough.add(field[x1][y1]);
//                            y1 += 1;
//                        }
//                        wayThrough.add(field[x1][y1]);
//                        if(filedXEntry > goThroughX){
//                            while(x1 != filedYExit){
//                                wayThrough.add(field[x1][y1]);
//                                x1 -= 1;
//                            }
//                        } else {
//                            while(x1 != filedXExit){
//                                wayThrough.add(field[x1][y1]);
//                                x1 += 1;
//                            }
//                        }
//                        wayThrough.add(field[x1][y1]);
//                    }
//                } else if(goThroughY == filedYEntry){
//                    if(filedXEntry > goThroughX){
//                        while(x1 != filedXExit){
//                            wayThrough.add(field[x1][y1]);
//                            x1 -= 1;
//                        }
//                        wayThrough.add(field[x1][y1]);
//                        if(filedYEntry > goThroughY){
//                            while(y1 != filedYExit){
//                                wayThrough.add(field[x1][y1]);
//                                y1 -= 1;
//                            }
//                        } else {
//                            while(y1 != filedYExit){
//                                wayThrough.add(field[x1][y1]);
//                                y1 += 1;
//                            }
//                        }
//                        wayThrough.add(field[x1][y1]);
//                    } else{
//                        while(x1 != filedXExit){
//                            wayThrough.add(field[x1][y1]);
//                            x1 += 1;
//                        }
//                        wayThrough.add(field[x1][y1]);
//                        if(filedYEntry > goThroughY){
//                            while(y1 != filedYExit){
//                                wayThrough.add(field[x1][y1]);
//                                y1 -= 1;
//                            }
//                        } else {
//                            while(y1 != filedYExit){
//                                wayThrough.add(field[x1][y1]);
//                                y1 += 1;
//                            }
//                        }
//                        wayThrough.add(field[x1][y1]);
//                    }
//                }
//            }
//            waysThroughIntersection.add(wayThrough);
//        }
//            // create a file with full moore neighbors
//        /*
//        for(int i = 0; i < field.length; i+=1){
//            for(int j = 0; j < field[i].length; j+=1){
//
//                if(i - 1 > 0) {
//                    Cell n1 = field[i - 1][j];
//                    field[i][j].neighbors.add(n1);
//                }
//                if(i + 1 < field.length ) {
//                    Cell n1 = field[i + 1][j];
//                    field[i][j].neighbors.add(n1);
//                }
//                if(j - 1 > 0) {
//                    Cell n1 = field[i][j - 1];
//                    field[i][j].neighbors.add(n1);
//                }
//                if(j + 1 < field[i].length) {
//                    Cell n1 = field[i][j + 1];
//                    field[i][j].neighbors.add(n1);
//                }
//            }
//        }
//        */
//
//    }
//    //returns line on which car should ends if it drives on road myRoad through this intersection to afterIntersection road
//    public Line whichLine(int myRoadNumber,int afterIntersectionRoadNumber){
//        ArrayList<Line> choice = new ArrayList<Line>();
//        Road road1 = null;
//        Road road2 = null;
//        for(Road i: roads){
//            if(i.number == myRoadNumber){
//                road1 = i;
//            } else if(i.number == afterIntersectionRoadNumber){
//                road2 = i;
//            }
//        }
//        if(road1 != null && road2 != null) {
//            for(Line i: road1.lines){
//                for(int j: i.ways){
//                    if(j == afterIntersectionRoadNumber){
//                        choice.add(i);
//                        break;
//                    }
//                }
//            }
//            if(choice.size() != 0){
//                Collections.shuffle(choice);
//                return choice.get(0);
//            }
//        }
//        return null;
//    }
//
//    // function returning a way that car will drive
//    public Line isLine(int x,int y){
//        for(Road i: roads){
//            for(Line j:i.lines){
//                if(j.x == x && j.y == y){
//                    return j;
//                }
//            }
//        }
//        return null;
//    }
//    public int returnWay(Line myLine,int goingTo){
//        return 0;
//    }
//}
