package Simulation.Controllers;

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
}
