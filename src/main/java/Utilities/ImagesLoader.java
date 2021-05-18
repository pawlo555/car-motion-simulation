package Utilities;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class ImagesLoader {
    private static String path = new File("src/main/resources").getAbsolutePath();

    public static ArrayList<Image> getImages(int zoom, int vertical, int horizontal, int elements) {
        ArrayList<Image> imageArray = new ArrayList<>();

        try {
            for (int i = 0; i < elements; i++) {
                String pathToImage = "file:///" +
                        TilesManager.getHorizontalTilesFile(zoom, vertical, horizontal+i).getAbsolutePath();
                Image image = new Image(pathToImage);
                imageArray.add(image);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return imageArray;
    }

    private static String getTileAsString(int tile) {
        if (tile >= 11 && tile <= 18) {
            return path+"/Tiles/"+tile;
        }
        throw new IllegalStateException("Tile should be between 11 and 18");
    }
}
