package Utilities;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class ImagesLoader {
    private static String path = new File("src/main/resources").getAbsolutePath();

    public static ArrayList<Image> getImages(int tile, int folderNumber, int fileNumber, int elements) {
        ArrayList<Image> imageArray = new ArrayList<>();

        System.out.println("XD");
        File tileDirectory = new File(getTileAsString(tile));
        System.out.println("XD");
        System.out.println(tileDirectory.toString());
        System.out.println(tileDirectory.listFiles());
        File verticalDirectory = tileDirectory.listFiles()[folderNumber];
        System.out.println("XD");
        String verticalFilesNames[] = verticalDirectory.list();
        System.out.println("XD");
        try {
            for (int i = 0; i < elements; i++) {
                imageArray.add(new Image(verticalFilesNames[i + fileNumber]));
            }
        }
        catch (Exception e) {
            System.out.println("List out of range");
        }
        return imageArray;
    }

    private static String getTileAsString(int tile) {
        if (tile >= 15 && tile <= 18) {
            return path+"/Tiles/"+tile;
        }
        throw new IllegalStateException("Tile should be between 15 and 18");
    }
}
