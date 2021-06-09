package Simulation;

import Utilities.*;
import Vehicles.AbstractVehicle;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class SimulationDrawer extends Canvas implements SimulationObserver {

    private int currentHorizontalTile = 0;
    private int currentVerticalTile = 0;
    private int currentZoom = TilesInfo.FirstZoomNumber;

    private SimulationEngine engine;

    public void zoomIn() {
        System.out.println("Zooming in");
        if (currentZoom < TilesInfo.LastZoomNumber) {
            currentZoom = currentZoom + 1;
            currentVerticalTile = currentVerticalTile*2;
            currentHorizontalTile = currentHorizontalTile*2;
            paintBackground();
        }
    }

    public void zoomOut() {
        System.out.println("Zooming out");
        if (currentZoom > TilesInfo.FirstZoomNumber) {
            currentZoom = currentZoom - 1;
            currentVerticalTile = currentVerticalTile/2;
            currentHorizontalTile = currentHorizontalTile/2;
            paintBackground();
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

    public void paintCars() {
        Vector2D upperLeft = getUpperLeftVector();
        Vector2D lowerRight = getLowerRightVector();
        List<AbstractVehicle> carList = engine.getCarsInSquare(upperLeft, lowerRight);
        GraphicsContext gc = this.getGraphicsContext2D();
        for (AbstractVehicle car: carList) {
            paintCar(gc, car);
        }
    }

    private void paintVerticalLine(ArrayList<Image> images, int verticalNumber) {
        GraphicsContext gc = this.getGraphicsContext2D();
        for (int i=0; i<images.size();i++) {
            gc.drawImage(images.get(i), 256*verticalNumber, 256*i);
        }
    }

    private void paintCar(GraphicsContext gc, AbstractVehicle car) {
        System.out.println("Drawer I should print car on position "+car.getPosition().toString());
//        double radius = 2 / Math.pow(2, getCurrentZoomLevel());
        double radius = 200;
        Vector2D carPosition = car.getPosition();
        gc.fillOval(carPosition.getX()-radius, carPosition.getY()-radius, radius, radius);
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

    public Vector2D getUpperLeftVector() {
        double horizontal = currentHorizontalTile*256*Math.pow(2, getCurrentZoomLevel());
        double vertical = currentVerticalTile*256*Math.pow(2, getCurrentZoomLevel());
        return new Vector2D(horizontal, vertical);
    }

    public Vector2D getLowerRightVector() {
        double horizontal = (currentHorizontalTile+3)*256*Math.pow(2, getCurrentZoomLevel());
        double vertical = (currentVerticalTile+3)*256*Math.pow(2, getCurrentZoomLevel());
        return new Vector2D(horizontal, vertical);
    }

    public void setCurrentTiles(CrossingParser parser) {

        currentHorizontalTile = parser.getHorizontalPosition(Integer.toString(currentZoom));
        currentVerticalTile = parser.getVerticalPosition(Integer.toString(currentZoom));
        paintBackground();
    }

    public void setEngine(SimulationEngine engine){
        this.engine = engine;
    }

    @Override
    public void nextEpoch() {
        System.out.println("In drawer: next epoch!");
        paintCars();
    }
}
