package Simulation;

import Simulation.Controllers.MapButtonsController;
import Simulation.Controllers.MenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SimulationApplication extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader mapLoader = getLoader("Map.fxml");
        Pane mapPane = mapLoader.load();
        MapButtonsController mapButtonsController = mapLoader.getController();
        mapButtonsController.setSimulationDrawer(new SimulationDrawer());

        FXMLLoader parametersLoader = getLoader("Parameters.fxml");
        VBox statBox = parametersLoader.load();


        FXMLLoader menuLoader = getLoader("Menu.fxml");
        HBox MenuBox = menuLoader.load();
        MenuController menuController = menuLoader.getController();
        menuController.setApplication(this);
        menuController.setEngine(new SimulationEngine());
        menuController.addCrossings();

        Group group = new Group();
        group.getChildren().add(mapPane);
        group.getChildren().add(statBox);
        group.getChildren().add(MenuBox);
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

    public void loadStatistics() {
        System.out.println("Loading statistics");
    }

    public void loadParameters() {
        System.out.println("Loading parameters");
    }
}