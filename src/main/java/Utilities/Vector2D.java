package Utilities;

import java.util.Objects;


public class Vector2D {
    public final double x;
    public final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return x == vector2D.x && y == vector2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean between(Vector2D upperLeft, Vector2D lowerRight) {
        return (upperLeft.y <= this.y && this.y <= lowerRight.y)
                && (upperLeft.x <= this.x && this.x <= lowerRight.x);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Vector2D vector){
        return Math.sqrt((x- vector.x)*(x- vector.x)+(y- vector.y)*(y- vector.y));
    }

    public Vector2D add(Vector2D vector){
        return new Vector2D(x+vector.x, y+ vector.y);
    }

    public Vector2D multiply_scalar(double s){
        return new Vector2D(x*s, y*s);
    }

    @Override
    public String toString() {
        return "("+ Math.round(x)+"," + Math.round(y) + "),";
    }

}