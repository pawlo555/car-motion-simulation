package Utilities.Crossing;

import Utilities.Vector2D;

import java.util.ArrayList;

public class drawRectTest {


    public static void example(){
        CrossingCell field[][];
        int rangeX = 12;
        int rangeY = 6;
        ArrayList<Vector2D> points= new ArrayList<>();
        Vector2D cell1 = new Vector2D(20,40);
        Vector2D cell2 = new Vector2D(10,20);
        Vector2D cell3 = new Vector2D(30,40);
        Vector2D cell4 = new Vector2D(20,15);
        points.add(cell1);
        points.add(cell2);
        points.add(cell3);
        points.add(cell4);


        field = new CrossingCell[rangeX][rangeY];
        for(int i=0;i<rangeX;i+=1){
            for(int j=0;j<rangeY;j+=1){
                field[i][j] = new CrossingCell(null,i,j);
            }
        }

        convertRectangle.convert(field,points);

        for(int i=0;i<rangeX;i+=1){
            for(int j=0;j<rangeY;j+=1){
                System.out.println("(" + field[i][j].mapX + "," + field[i][j].mapY + ")");
            }
        }

        System.out.println("-------------------------");

        System.out.println("(" + field[0][0].mapX + "," + field[0][0].mapY + ")" + ",");
        System.out.println("(" + field[0][rangeY - 1].mapX + "," + field[0][rangeY - 1].mapY + ")");
        System.out.println("(" + field[rangeX - 1][0].mapX + "," + field[rangeX - 1][0].mapY + ")");
        System.out.println("(" + field[rangeX - 1][rangeY - 1].mapX + "," + field[rangeX - 1][rangeY - 1].mapY + ")");
    }

    public static void draw(Crossing example){
        System.out.println(example.waysThroughIntersection.size());
        for(int i = 0;i<example.enterList.size();i+=1){
            System.out.println("-----------------");
            System.out.println(example.enterList.get(i) + " " + example.exitList.get(i));
            System.out.println(example.waysThroughIntersection.get(i));
        }
    }


}
