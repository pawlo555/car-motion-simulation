package Simulation;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Utilities.ImagesLoader;

import java.util.ArrayList;

public class SimulationDrawer extends Canvas {
    static private int xSize = 100;
    static private int ySize = 100;

    private int currentX = 0;
    private int currentY = 0;
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
        currentY = currentY - 1;
        paintBackground();
    }

    public void goEast() {
        System.out.println("Go east");
        currentX = currentX + 1;
        paintBackground();
    }

    public void goSouth() {
        System.out.println("Go south");
        currentY = currentY + 1;
        paintBackground();
    }

    public void goWest() {
        System.out.println("Go west");
        currentX = currentX - 1;
        paintBackground();
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
            ArrayList<Image> images = ImagesLoader.getImages(scale, currentX + i,currentY,3);
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
