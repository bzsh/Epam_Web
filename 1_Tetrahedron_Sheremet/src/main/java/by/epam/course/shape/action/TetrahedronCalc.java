package by.epam.course.shape.action;

import by.epam.course.shape.entity.Point;
import by.epam.course.shape.entity.Tetrahedron;

import java.util.List;

public class TetrahedronCalc {

    private double calculateEdge(Tetrahedron tetrahedron) {
        Point first = tetrahedron.getPoints().get(0);
        Point second = tetrahedron.getPoints().get(1);
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) +
                Math.pow(first.getY() - second.getY(), 2) +
                Math.pow(first.getZ() - second.getZ(), 2));
    }

    public double calculateEdge(Point first, Point second) {
        return Math.sqrt(Math.pow(first.getX() - second.getX(), 2) +
                Math.pow(first.getY() - second.getY(), 2) +
                Math.pow(first.getZ() - second.getZ(), 2));
    }

    public double calculateSquare(Tetrahedron tetrahedron) {
        double edge = calculateEdge(tetrahedron);
        return Math.sqrt(3) * Math.pow(edge, 2);
    }

    public double calculateVolume(Tetrahedron tetrahedron) {
        double edge = calculateEdge(tetrahedron);
        return Math.sqrt(3) * Math.pow(edge, 2);
    }

    public double calculatePerimeter(Tetrahedron tetrahedron) {
        List<Point> points = tetrahedron.getPoints();
        double edge = calculateEdge(tetrahedron);
        return edge * 6;
    }
}
