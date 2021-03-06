package Simulation.Controllers;

import Simulation.SimulationDrawer;
import Utilities.CrossingParser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.DecimalFormat;


public class MapController {

    @FXML private SimulationDrawer drawer;
    @FXML private Label position;

    public void zoomInPressed() {
        drawer.zoomIn();
    }

    public void zoomOutPressed() {
        drawer.zoomOut();
    }

    public void northPressed() {
        drawer.goNorth();
    }

    public void eastPressed() {
        drawer.goEast();
    }

    public void southPressed() {
        drawer.goSouth();
    }

    public void westPressed() {
        drawer.goWest();
    }

    public void setSimulationDrawer(SimulationDrawer newDrawer) {
        drawer = newDrawer;
    }

    public SimulationDrawer getSimulationDrawer() {
        return drawer;
    }

    public void onMouseMoved(MouseEvent event) {
        setPositionLabel(event.getX(), event.getY());
    }

    private void setPositionLabel(double x, double y) {
        double zoomLevelFactor = Math.pow(2.0, drawer.getCurrentZoomLevel());
        double globalX = drawer.getCurrentHorizontalTile()*256*zoomLevelFactor+x*zoomLevelFactor;
        double globalY = drawer.getCurrentVerticalTile()*256*zoomLevelFactor+y*zoomLevelFactor;

        String xPosition = String.format("%.2f", globalX);
        String yPosition = String.format("%.2f", globalY);
        position.setText("Current position: (" + yPosition + ":" + xPosition + ")");
    }

    public void crossingSet(String crossingFilename) throws IOException, ParseException {
        CrossingParser parser = new CrossingParser(crossingFilename);
        drawer.setCurrentTiles(parser);
    }
}
