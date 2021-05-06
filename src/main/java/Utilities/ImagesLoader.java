package Utilities;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class ImagesLoader {
    private static String path = new File("src/main/resources").getAbsolutePath();

    public static ArrayList<Image> getImages(int tile, int folderNumber, int fileNumber, int elements) {
        ArrayList<Image> imageArray = new ArrayList<>();

        File tileDirectory = new File(getTileAsString(tile));
        System.out.println(tileDirectory.toString());
        try {
            File verticalDirectory = tileDirectory.listFiles()[folderNumber];
            String verticalDirectoryPath = verticalDirectory.getPath();
            String[] verticalFilesNames = verticalDirectory.list();

            for (int i = 0; i < elements; i++) {
                System.out.println(i);
                String pathToImage = "file:///" + verticalDirectoryPath+"\\"+verticalFilesNames[i + fileNumber];
                System.out.println(pathToImage);
                Image image = new Image(pathToImage);
                System.out.println("Image:" + image);
                imageArray.add(image);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("List out of range");
            System.out.println(imageArray);
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
