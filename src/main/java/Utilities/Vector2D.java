package Utilities;

import java.util.Objects;

public class Vector2D {
    private final double horizontal;
    private final double vertical;

    public Vector2D(double horizontal, double vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return horizontal == vector2D.horizontal && vertical == vector2D.vertical;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, vertical);
    }

    public boolean between(Vector2D upperLeft, Vector2D lowerRight) {
        return (upperLeft.vertical <= this.vertical && this.vertical <= lowerRight.vertical)
                && (upperLeft.horizontal <= this.horizontal && this.horizontal <= lowerRight.horizontal);
    }

    public double getHorizontal() {
        return horizontal;
    }

    public double getVertical() {
        return vertical;
    }
}
