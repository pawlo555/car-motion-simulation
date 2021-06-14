package Utilities.Crossing;

import Utilities.Vector2D;

import java.util.ArrayList;



public class convertRectangle {

    public static void convert(CrossingCell[][] field, ArrayList<Vector2D> points){
        int xSize = field.length;
        int ySize = field[0].length;
        double currentCoordsX = 0;
        double currentCoordsY = 0;

        //set rectangle corners map coordinates
        field[0][0].setMapCoords(points.get(0).x,points.get(0).y);
        field[xSize-1][0].setMapCoords(points.get(1).x,points.get(1).y);
        field[0][ySize-1].setMapCoords(points.get(2).x,points.get(2).y);
        field[xSize-1][ySize-1].setMapCoords(points.get(3).x,points.get(3).y);

        //set top and bottom line coordinates due to corners coordinates
        // top
        for(int i=1;i<xSize ;i+=1){
            currentCoordsX = (field[0][0].mapX * (xSize - i) + field[xSize-1][0].mapX * i) / xSize;
            currentCoordsY = (field[0][0].mapY * (xSize - i) + field[xSize-1][0].mapY * i) / xSize;
            field[i][0].setMapCoords(currentCoordsX,currentCoordsY);
        }
        // bottom
        for(int i=1;i<xSize ;i+=1){
            currentCoordsX = (field[0][ySize - 1].mapX * (xSize - i) + field[xSize-1][ySize - 1].mapX * i) / xSize;
            currentCoordsY = (field[0][ySize - 1].mapY * (xSize - i) + field[xSize-1][ySize - 1].mapY * i) / xSize;
            field[i][ySize - 1].setMapCoords(currentCoordsX,currentCoordsY);
        }

        //set rest coordinates  due to top and bottom lines
        for(int i=0;i<xSize;i+=1){
            for(int j=1;j<ySize;j+=1){
                currentCoordsX = (field[i][0].mapX * (ySize - j) + field[i][ySize - 1].mapX * j) / ySize;
                currentCoordsY = (field[i][0].mapY * (ySize - j) + field[i][ySize - 1].mapY * j) / ySize;
                field[i][j].setMapCoords(currentCoordsX,currentCoordsY);
            }
        }

    }
}
