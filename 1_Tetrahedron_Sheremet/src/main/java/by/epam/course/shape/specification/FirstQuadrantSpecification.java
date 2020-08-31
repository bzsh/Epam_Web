package by.epam.course.shape.specification;

import by.epam.course.shape.entity.Point;
import by.epam.course.shape.entity.Tetrahedron;

import java.util.List;

public class FirstQuadrantSpecification implements Specification {

    @Override
    public boolean specify(Tetrahedron tetrahedron) {
        List<Point> points = tetrahedron.getPoints();
        for (Point point : points) {
            boolean result = point.getX() >= 0 &&
                    point.getY() >= 0 &&
                    point.getZ() >= 0;
            if (!result) {
                return false;
            }
        }
        return true;
    }
}
