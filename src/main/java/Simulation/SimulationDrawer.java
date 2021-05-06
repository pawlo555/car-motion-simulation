package Simulation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import Utilities.ImagesLoader;

import java.util.ArrayList;

public class SimulationDrawer extends Canvas {
    static private int xSize;
    static private int ySize;

    private int currentX;
    private int currentY;
    private int scale = 11;

    public void zoomIn() {
        System.out.println("Zooming in");
        if (scale < 18) {
            scale = scale + 1;
        }
        paintBackground();
    }

    public void zoomOut() {
        System.out.println("Zooming out");
        if (scale > 11) {
            scale = scale - 1;
        }
        paintBackground();
    }

    public void goNorth() {
        System.out.println("Go north");
    }

    public void goEast() {
        System.out.println("Go east");
    }

    public void goSouth() {
        System.out.println("Go south");
    }

    public void goWest() {
        System.out.println("Go west");
    }

    public void initializeMap() {
        this.setWidth(860);
        this.setHeight(600);
        paintBackground();
    }

    public void paintBackground() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0,0,getWidth(), getHeight());
        for (int i=0; i<4; i++) {
            ArrayList<Image> images = ImagesLoader.getImages(scale, i,0,3);
            paintVerticalLine(images, i);
        }
    }

    private void paintVerticalLine(ArrayList<Image> images, int verticalNumber) {
        GraphicsContext gc = this.getGraphicsContext2D();
        for (int i=0; i<images.size();i++) {
            gc.drawImage(images.get(i), 256*verticalNumber, 256*i);
        }
    }
}
