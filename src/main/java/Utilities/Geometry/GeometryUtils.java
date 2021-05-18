package Utilities.Geometry;

public class GeometryUtils {
    public static Vector2d perpendicularProjection(Vector2d v1, Vector2d v2, Vector2d v3){
        // metoda zwraca punkt bedacy rzutem prostopadlum punktu v3 na prosta |v1v2|
        if (v1.x == v2.x){
            return new Vector2d(v1.x, v3.y);
        }
        if (v1.y == v2.y){
            return new Vector2d(v3.x, v1.y);
        }
        double a = (v1.y-v2.y)/(v1.x-v2.x);
        double b = v1.y-a*v1.x;
        double a_1 = -1/a;
        double b_1 = v3.y-a_1*v3.x;
        double x = (b_1-b)/(a-a_1);
        double y = a*x+b;
        return new Vector2d(x, y);
    }

    public static Vector2d[] splitIntoParts(Vector2d v1, Vector2d v2, int nParts){
        double deltaX = (v2.x - v1.x)/nParts;
        double deltaY = (v2.y - v1.y)/nParts;
        Vector2d deltaVector = new Vector2d(deltaX, deltaY);
        Vector2d points[] = new Vector2d[nParts+1];
        points[0] = v1;
        points[nParts] = v2;
        for (int i = 1; i < nParts; i++){
            points[i] = v1.add(deltaVector.multiply_scalar(i));
        }
        return points;
    }

    public static Vector2d[] splitIntoPartsLen(Vector2d v1, Vector2d v2, double partLen){
        //splits into parts with given len as long as can, the last part is shorter
        double len = v1.distance(v2);
        double unitX = (v2.x - v1.x)/len;
        double unitY = (v2.y - v1.y)/len;
        int nParts = (int)(len/partLen);
        Vector2d unitVector = new Vector2d(unitX, unitY);
        Vector2d points[] = new Vector2d[nParts+2];
        points[0] = v1;
        points[nParts+1] = v2;
        for (int i = 1; i < nParts+1; i++){
            points[i] = v1.add(unitVector.multiply_scalar(partLen*i));
        }
        return points;
    }

    public static Vector2d findLinesCrossing(Vector2d v1, Vector2d v2, Vector2d v3, Vector2d v4){
        //finds point where two lines |v1v2| and |v3v4| do cross
        double a1 = (v1.y-v2.y)/(v1.x-v2.x);
        double a2 = (v3.y-v4.y)/(v3.x-v4.x);
        double b1 = v1.y-a1*v1.x;
        double b2 = v3.y-a2*v3.x;
        double midX = (b2-b1)/(a1-a2);
        double midY = a1*midX + b1;
        return new Vector2d(midX, midY);
    }

    public static double countTirangleArea(Vector2d v1, Vector2d v2, Vector2d v3){
        double a = v1.distance(v2);
        double h = distanceFromLine(v1, v2, v3);
        return a*h/2;

    }

    public static double countRectangleArea(Vector2d v1, Vector2d v2, Vector2d v3, Vector2d v4){
        //returns area between vectors, assuming that they are vertices of a rectangle
        double a = v1.distance(v2);
        double b = distanceFromLine(v1, v2, v3);
        return a*b;

    }

    public static double distanceFromLine(Vector2d v1, Vector2d v2, Vector2d v3){
        //method that returns distance from a point v3 to line |v1v2|
        return Math.abs((v2.x-v1.x)*(v1.y-v3.y)-(v1.x-v3.x)*(v2.y-v1.y))/Math.sqrt(Math.pow(v2.x-v1.x, 2)+Math.pow(v2.y-v1.y, 2));
    }
}
