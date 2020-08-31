package by.epam.course.shape.entity;


import java.util.List;

public class Point {
    private double x;
    private double y;
    private double z;

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point(List<Double> coordinates) {
        this.x = coordinates.get(0);
        this.y = coordinates.get(1);
        this.z = coordinates.get(2);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y &&
                z == point.z;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result *= PRIME + Double.valueOf(x).hashCode();
        result *= PRIME + Double.valueOf(y).hashCode();
        result *= PRIME + Double.valueOf(z).hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(x);
        sb.append("; ").append(y);
        sb.append("; ").append(z);
        sb.append(")");
        return sb.toString();
    }
}
