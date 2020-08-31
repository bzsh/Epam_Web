package by.epam.course.shape.validator;

import by.epam.course.shape.action.TetrahedronCalc;
import by.epam.course.shape.entity.Point;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TetrahedronValidator {
    private static final Logger LOGGER = LogManager.getLogger(TetrahedronValidator.class);
    public boolean isTetrahedron(List<Point> points) {
        TetrahedronCalc action = new TetrahedronCalc();
        Double edge;
        List<Double> allEdges = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = 1; j < points.size(); j++) {
                if (i >= j) {
                    continue;
                }
                edge = action.calculateEdge(points.get(i), points.get(j));
                allEdges.add(edge);
            }
        }
        return this.isAllEdgesAreEquals(allEdges);
    }

    private boolean isAllEdgesAreEquals(List<Double> allEdges) {
        for (int i = 0; i < allEdges.size(); i++) {
            for (int j = 1; j < allEdges.size(); j++) {
                if (i >= j) {
                    continue;
                }
                if (allEdges.get(0) - allEdges.get(j) != 0) {
                    LOGGER.info("All edges of the tetrahedron are not equal");
                    return false;
                }
            }
        }
        LOGGER.info("All edges of the tetrahedron are equal");
        return true;
    }
}
