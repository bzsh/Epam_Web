package by.epam.course.shape.warehouse;

import by.epam.course.shape.entity.Point;
import by.epam.course.shape.entity.Tetrahedron;
import by.epam.course.shape.entity.TetrahedronCharacteristic;
import by.epam.course.shape.util.IdCreator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarehouseTest {
    private Warehouse warehouse;
    private Tetrahedron oneTetrahedron;
    private Tetrahedron twoTetrahedron;
    private Tetrahedron threeTetrahedron;
    private Tetrahedron fourTetrahedron;
    private final static List<Point> ONE = new ArrayList<>();
    private final static List<Point> TWO = new ArrayList<>();
    private final static List<Point> THREE = new ArrayList<>();
    private final static List<Point> FOUR = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        warehouse = Warehouse.getInstance();

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
        Collections.addAll(THREE,
                new Point(1.35, 0.345, 1),
                new Point(-1, 0.1, 4.3),
                new Point(-8.2, 6.9, 0.546),
                new Point(2.8, 1.2, 1.32));
        Collections.addAll(FOUR,
                new Point(0.123, -0.2, 0),
                new Point(-1, 3.3, 4.5),
                new Point(-8.2, 0.583, -0.546),
                new Point(2.4, 0.2, 1.72));

        oneTetrahedron = new Tetrahedron(IdCreator.createId(), ONE, "tetrahedron_1");
        twoTetrahedron = new Tetrahedron(IdCreator.createId(), TWO, "tetrahedron_2");
        threeTetrahedron = new Tetrahedron(IdCreator.createId(), THREE, "tetrahedron_3");
        fourTetrahedron = new Tetrahedron(IdCreator.createId(), FOUR, "tetrahedron_4");

        warehouse.addCharacteristic(new TetrahedronCharacteristic(oneTetrahedron));
        warehouse.addCharacteristic(new TetrahedronCharacteristic(twoTetrahedron));
        warehouse.addCharacteristic(new TetrahedronCharacteristic(threeTetrahedron));
        warehouse.addCharacteristic(new TetrahedronCharacteristic(fourTetrahedron));
    }

    @Test
    public void testUpdateCharacteristic() {
        List<TetrahedronCharacteristic> expected = new ArrayList<>();

        TetrahedronCharacteristic change = new TetrahedronCharacteristic(new Tetrahedron(4, TWO, "tetrahedron_4"));

        expected.add(new TetrahedronCharacteristic(oneTetrahedron));
        expected.add(new TetrahedronCharacteristic(twoTetrahedron));
        expected.add(new TetrahedronCharacteristic(threeTetrahedron));
        expected.add(change);

        fourTetrahedron.setPoints(TWO);
        List<TetrahedronCharacteristic> actual = warehouse.getCharacteristicList();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testRemoveCharacteristic() {
        List<TetrahedronCharacteristic> expected = new ArrayList<>();
        expected.add(new TetrahedronCharacteristic(oneTetrahedron));
        expected.add(new TetrahedronCharacteristic(twoTetrahedron));
        expected.add(new TetrahedronCharacteristic(threeTetrahedron));

        warehouse.removeCharacteristic(new TetrahedronCharacteristic(fourTetrahedron));

        List<TetrahedronCharacteristic> actual = warehouse.getCharacteristicList();
        Assert.assertEquals(actual, expected);

    }
}