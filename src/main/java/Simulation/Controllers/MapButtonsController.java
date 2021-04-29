package Simulation.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import Simulation.SimulationDrawer;

public class MapButtonsController {

    private SimulationDrawer drawer;

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
        drawer.goNorth();
    }

    public void southPressed() {
        drawer.goNorth();
    }

    public void westPressed() {
        drawer.goNorth();
    }

    public void setSimulationDrawer(SimulationDrawer newDrawer) {
        drawer = newDrawer;
    }
}
