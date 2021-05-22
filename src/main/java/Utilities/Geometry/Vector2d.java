//package Utilities;
//
//import Objects.Utils.RoundUtils;
//
//import java.util.Objects;
//
//public class Vector2d {
//    public double x;
//    public double y;
//
//    public Vector2d(double x, double y){
//        this.x = x;
//        this.y = y;
//    }
//
//    public double distance(Vector2d vector){
//        return Math.sqrt((x- vector.x)*(x- vector.x)+(y- vector.y)*(y- vector.y));
//    }
//
//    public Vector2d add(Vector2d vector){
//        return new Vector2d(x+vector.x, y+ vector.y);
//    }
//
//    public Vector2d multiply_scalar(double s){
//        return new Vector2d(x*s, y*s);
//    }
//
//    @Override
//    public String toString() {
//        return "("+ RoundUtils.round(x,2)+"," + RoundUtils.round(y,2) + "),";
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Vector2d vector2d = (Vector2d) o;
//        return Double.compare(vector2d.x, x) == 0 &&
//                Double.compare(vector2d.y, y) == 0;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(x, y);
//    }
//}
