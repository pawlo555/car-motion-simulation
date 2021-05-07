package Utilities;

import java.util.ArrayList;

public class TilesInfo {


    public static ArrayList<Integer> tilesVerticalNumbers = loadTilesVerticalNumbers();
    public static ArrayList<Integer> tilesHorizontalNumbers = loadTilesHorizontalNumbers();
    public static final int FirstZoomNumber = getFirstZoomNumber();
    public static final int LastZoomNumber = getLastZoomNumber();

    public int getVerticalNumber(int zoom) {
        return tilesVerticalNumbers.get(zoom- FirstZoomNumber);
    }

    public int getHorizontalNumber(int zoom) {
        return tilesHorizontalNumbers.get(zoom- FirstZoomNumber);
    }

    private static ArrayList<Integer> loadTilesVerticalNumbers() {
        ArrayList<Integer> heights = new ArrayList<>();
        for (int i = FirstZoomNumber; i < LastZoomNumber; i++) {
            heights.add(TilesManager.getVerticalTilesNumber(i));
        }
        return heights;

    }

    private static ArrayList<Integer> loadTilesHorizontalNumbers() {
        ArrayList<Integer> widths = new ArrayList<>();
        for (int i = FirstZoomNumber; i < LastZoomNumber; i++) {
            widths.add(TilesManager.getHorizontalTilesNumber(i));
        }
        return widths;
    }

    private static int getFirstZoomNumber() {
        return 11;
    }

    private static int getLastZoomNumber() {
        return 18;
    }
}
