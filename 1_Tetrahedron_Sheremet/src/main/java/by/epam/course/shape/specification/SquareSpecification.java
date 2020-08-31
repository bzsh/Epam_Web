package by.epam.course.shape.specification;

import by.epam.course.shape.action.TetrahedronCalc;
import by.epam.course.shape.entity.Tetrahedron;

public class SquareSpecification implements Specification {
    double minSquare;
    double maxSquare;

    public SquareSpecification(double minSquare, double maxSquare) {
        this.minSquare = minSquare;
        this.maxSquare = maxSquare;
    }

    @Override
    public boolean specify(Tetrahedron tetrahedron) {
        TetrahedronCalc calculator = new TetrahedronCalc();
        double square = calculator.calculateSquare(tetrahedron);
        return minSquare < square && square < maxSquare;
    }
}
