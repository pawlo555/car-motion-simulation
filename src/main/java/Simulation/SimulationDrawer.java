package Simulation;

import Utilities.TilesInfo;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Utilities.ImagesLoader;

import java.util.ArrayList;

public class SimulationDrawer extends Canvas {

    private int currentHorizontalTile = 0;
    private int currentVerticalTile = 0;
    private int currentZoom = TilesInfo.FirstZoomNumber;

    public void zoomIn() {
        System.out.println("Zooming in");
        if (currentZoom < TilesInfo.LastZoomNumber) {
            currentZoom = currentZoom + 1;
            paintBackground();
            currentVerticalTile = currentVerticalTile*2;
            currentHorizontalTile = currentHorizontalTile*2;
        }
    }

    public void zoomOut() {
        System.out.println("Zooming out");
        if (currentZoom > TilesInfo.FirstZoomNumber) {
            currentZoom = currentZoom - 1;
            paintBackground();
            currentVerticalTile = currentVerticalTile/2;
            currentHorizontalTile = currentHorizontalTile/2;
        }
    }

    public void goNorth() {
        System.out.println("Go north");
        if (currentVerticalTile > 0) {
            currentVerticalTile = currentVerticalTile - 1;
            paintBackground();
        }
    }

    public void goEast() {
        System.out.println("Go east");
        if (currentHorizontalTile < TilesInfo.getHorizontalNumber(currentZoom) - 1) {
            currentHorizontalTile = currentHorizontalTile + 1;
            paintBackground();
        }
    }

    public void goSouth() {
        System.out.println("Go south");
        if (currentVerticalTile < TilesInfo.getVerticalNumber(currentZoom) - 1) {
            currentVerticalTile = currentVerticalTile + 1;
            paintBackground();
        }
    }

    public void goWest() {
        System.out.println("Go west");
        if (currentHorizontalTile > 0) {
            currentHorizontalTile = currentHorizontalTile - 1;
            paintBackground();
        }
    }

    public void initializeMap() {
        this.setWidth(768);
        this.setHeight(620);
        paintBackground();
    }

    public void paintBackground() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0,0,getWidth(), getHeight());
        for (int i=0; i<3; i++) {
            ArrayList<Image> images = ImagesLoader.getImages(currentZoom, currentHorizontalTile + i, currentVerticalTile,3);
            paintVerticalLine(images, i);
        }
    }

    private void paintVerticalLine(ArrayList<Image> images, int verticalNumber) {
        GraphicsContext gc = this.getGraphicsContext2D();
        for (int i=0; i<images.size();i++) {
            gc.drawImage(images.get(i), 256*verticalNumber, 256*i);
        }
    }

    public int getCurrentHorizontalTile() {
        return currentHorizontalTile;
    }

    public int getCurrentVerticalTile() {
        return currentVerticalTile;
    }

    public int getCurrentZoomLevel() {
        return Math.abs(currentZoom-TilesInfo.LastZoomNumber);
    }
}
