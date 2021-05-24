package Utilities;

import java.io.File;

public class TilesManager {
    private static final String path = new File("src/main/resources").getAbsolutePath();


    private static File getZoomFile(int zoom) {
        return new File(path + "/Tiles/" + zoom);
    }

    public static File getVerticalTilesFile(int zoom, int vertical) {
        File[] verticalArray = getVerticalFileArray(zoom);
        if (vertical >= verticalArray.length || vertical < 0) {
            throw new IndexOutOfBoundsException("Vertical number" + vertical + "is invalid.");
        }
        else {
            return verticalArray[vertical];
        }
    }

    public static File getHorizontalTilesFile(int zoom, int vertical, int horizontal) {
        File[] horizontalArray = getHorizontalFileArray(zoom, vertical);
        if (horizontal >= horizontalArray.length || horizontal < 0) {
            throw new IndexOutOfBoundsException("Horizontal number" + vertical + "is invalid");
        }
        else {
            return horizontalArray[horizontal];
        }
    }

    private static File[] getVerticalFileArray(int zoom) {
        File file = getZoomFile(zoom);
        return file.listFiles();
    }

    private static File[] getHorizontalFileArray(int zoom, int vertical) {
        File horizontalDirectory = getVerticalTilesFile(zoom, vertical);
        return horizontalDirectory.listFiles();
    }

    public static int getVerticalTilesNumber(int zoom) {
        return getVerticalFileArray(zoom).length;
    }

    public static int getHorizontalTilesNumber(int zoom) {
        return getHorizontalFileArray(zoom, 0).length;
    }
}
