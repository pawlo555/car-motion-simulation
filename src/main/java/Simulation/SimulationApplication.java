package Simulation;

import Simulation.Controllers.MapController;
import Simulation.Controllers.MenuController;
import Simulation.Controllers.ParametersController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class SimulationApplication extends javafx.application.Application {

    private Pane mapPane;
    private HBox menuBox;
    private VBox statisticsBox;
    private VBox parametersBox;

    @Override
    public void start(Stage stage) throws Exception {
        loadSimulationElements();

        Group group = new Group();
        group.getChildren().add(mapPane);
        group.getChildren().add(statisticsBox);
        group.getChildren().add(menuBox);
        group.getChildren().add(parametersBox);
        showParameters();

        Scene scene = new Scene(group, 1280, 720);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("SimulationStyle.css")));
        stage.setTitle("Simulation");
        stage.setScene(scene);
        stage.show();
    }

    static public void main(String[] args) {
        launch(args);
    }

    private FXMLLoader getLoader(String name) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(name));
        return loader;
    }

    public void showStatistics() {
        System.out.println("Showing statistics");
        parametersBox.setVisible(false);
        statisticsBox.setVisible(true);
    }

    public void showParameters() {
        System.out.println("Showing parameters");
        parametersBox.setVisible(true);
        statisticsBox.setVisible(false);
    }

    private void loadSimulationElements() throws IOException, ParseException {
        MapController mapController = loadMap();
        MenuController menuController = loadMenu();
        ParametersController parametersController = loadParameters();
        loadStatistics();

        menuController.setParametersController(parametersController);
    }

    private MenuController loadMenu() throws IOException, ParseException {
        FXMLLoader menuLoader = getLoader("Menu.fxml");
        menuBox = menuLoader.load();
        MenuController menuController = menuLoader.getController();
        menuController.setApplication(this);
        menuController.setEngine(new SimulationEngine());
        menuController.addCrossings();
        return menuController;
    }

    private MapController loadMap() throws IOException {
        FXMLLoader mapLoader = getLoader("Map.fxml");
        mapPane = mapLoader.load();
        MapController controller = mapLoader.getController();
        controller.getSimulationDrawer().initializeMap();

        return controller;
    }

    private ParametersController loadParameters() throws IOException {
        FXMLLoader parametersLoader = getLoader("Parameters.fxml");
        parametersBox = parametersLoader.load();

        return parametersLoader.getController();
    }

    private void loadStatistics() throws IOException {
        FXMLLoader parametersLoader = getLoader("Statistics.fxml");
        statisticsBox = parametersLoader.load();
    }
}