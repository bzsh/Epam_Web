package by.epam.course.shape.validator;

import by.epam.course.shape.entity.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TetrahedronValidatorTest {
    private final static List<Point> ONE = new ArrayList<>();
    private final static List<Point> TWO = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        Collections.addAll(ONE,
                new Point(1.2, 1.2, 1.2),
                new Point(-1.2, -1.2, 1.2),
                new Point(-1.2, 1.2, -1.2),
                new Point(1.2, -1.2, -1.2));
        Collections.addAll(TWO,
                new Point(1.35, -0.2, 0),
                new Point(-1, 3.3, 4.5),
                new Point(-8.2, 6.2, 0.546),
                new Point(2.4, 0.2, 1.72));
    }

    @DataProvider(name = "pointsToValidate")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]{
                {true, ONE},
                {false, TWO},
        };
    }

    @Test(dataProvider = "pointsToValidate")
    public void testIsTetrahedron(boolean expected, List<Point> points) {
        TetrahedronValidator validator = new TetrahedronValidator();
        boolean actual = validator.isTetrahedron(points);
        Assert.assertEquals(actual, expected);
    }
}