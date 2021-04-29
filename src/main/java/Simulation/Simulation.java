package Simulation;

import Simulation.Controllers.MapButtonsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Simulation extends Application {
    private MapButtonsController mapButtonsController;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader mapLoader = getLoader("Map.fxml");
        Pane mapPane = mapLoader.load();
        mapButtonsController = mapLoader.getController();
        mapButtonsController.setSimulationDrawer(new SimulationDrawer());
        FXMLLoader statisticsLoader = getLoader("Statistics.fxml");
        VBox statBox = statisticsLoader.load();
        FXMLLoader menuLoader = getLoader("Menu.fxml");
        HBox MenuBox = menuLoader.load();

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

    private FXMLLoader getLoader(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(name));
        return loader.load();
    }
}