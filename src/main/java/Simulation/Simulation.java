package Simulation;

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
    @Override
    public void start(Stage stage) throws Exception {
        Pane mapPane = (Pane) loadFXML("Map.fxml");
        VBox statBox = (VBox) loadFXML("Statistics.fxml");
        HBox MenuBox = (HBox) loadFXML("Menu.fxml");

        Group group = new Group();
        group.getChildren().add(mapPane);
        group.getChildren().add(statBox);
        group.getChildren().add(MenuBox);
        Scene scene = new Scene(group, 1280, 720);
        stage.setTitle("Simulation");
        stage.setScene(scene);
        stage.show();
    }

    static public void main(String[] args) {
        launch(args);
    }

    private Node loadFXML(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(name));
        return loader.load();
    }
}