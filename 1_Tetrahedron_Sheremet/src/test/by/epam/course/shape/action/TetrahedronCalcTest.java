package by.epam.course.shape.action;

import by.epam.course.shape.entity.Point;
import by.epam.course.shape.entity.Tetrahedron;
import by.epam.course.shape.util.IdCreator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TetrahedronCalcTest {
    private final static double DELTA = 0.02;
    private TetrahedronCalc calculator;
    private Tetrahedron tetrahedron;
    private List<Point> points;

    @BeforeClass
    public void setUp() {
        points = new ArrayList<>();
        Collections.addAll(points,
                new Point(1.2, 1.2, 1.2),
                new Point(-1.2, -1.2, 1.2),
                new Point(-1.2, 1.2, -1.2),
                new Point(1.2, -1.2, -1.2));
        calculator = new TetrahedronCalc();
        tetrahedron = new Tetrahedron(IdCreator.createId(), points, "TestTetrahedron");
    }

    @AfterClass
    public void tierDown() {
        calculator = null;
        points = null;
    }

    @Test
    public void testCalculateEdge() {
        double expected = 6.55;
        double actual = calculator.calculateEdge(new Point(12, 3, -7), new Point(8, 5, -2.2));
        Assert.assertEquals(actual, expected, DELTA);
    }

    @Test
    public void testCalculateSquare() {
        double expected = 19.95;
        double actual = calculator.calculateSquare(tetrahedron);
        Assert.assertEquals(actual, expected, DELTA);
    }

    @Test
    public void testCalculateVolume() {
        double expected = 20.36;
        double actual = calculator.calculatePerimeter(tetrahedron);
        Assert.assertEquals(actual, expected, DELTA);
    }

    @Test
    public void testCalculatePerimeter() {
        double expected = 19.95;
        double actual = calculator.calculateVolume(tetrahedron);
        Assert.assertEquals(actual, expected, DELTA);
    }
}