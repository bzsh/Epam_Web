package by.epam.course.shape.repository;

import by.epam.course.shape.entity.Point;
import by.epam.course.shape.entity.Tetrahedron;
import by.epam.course.shape.specification.*;
import by.epam.course.shape.util.IdCreator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TetrahedronRepositoryTest {
    private TetrahedronRepository repository;
    private Tetrahedron oneTetrahedron;
    private Tetrahedron twoTetrahedron;
    private Tetrahedron threeTetrahedron;
    private Tetrahedron fourTetrahedron;
    private final static List<Point> ONE = new ArrayList<>();
    private final static List<Point> TWO = new ArrayList<>();
    private final static List<Point> THREE = new ArrayList<>();
    private final static List<Point> FOUR = new ArrayList<>();

    @BeforeTest
    public void setUp() {
        repository = TetrahedronRepository.getInstance();
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
                new Point(1, 0.1, 4.3),
                new Point(8.2, 6.9, 0.546),
                new Point(2.8, 1.2, 1.32));
        Collections.addAll(FOUR,
                new Point(0.123, -0.2, 0),
                new Point(1, 3.3, 4.5),
                new Point(8.2, -0.583, 0.546),
                new Point(2.4, 0.2, 1.72));

        oneTetrahedron = new Tetrahedron(IdCreator.createId(), ONE, "tetrahedron_1");
        twoTetrahedron = new Tetrahedron(IdCreator.createId(), TWO, "tetrahedron_2");
        threeTetrahedron = new Tetrahedron(IdCreator.createId(), THREE, "tetrahedron_3");
        fourTetrahedron = new Tetrahedron(IdCreator.createId(), FOUR, "tetrahedron_4");

        repository.addTetrahedron(oneTetrahedron);
        repository.addTetrahedron(twoTetrahedron);
        repository.addTetrahedron(threeTetrahedron);

    }

    @Test
    public void testAddTetrahedron() {
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron, fourTetrahedron);
        repository.addTetrahedron(fourTetrahedron);
        List<Tetrahedron> actual = repository.getAllTetrahedrons();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testRemoveTetrahedron() {
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        repository.removeTetrahedron(fourTetrahedron);
        List<Tetrahedron> actual = repository.getAllTetrahedrons();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testUpdateTetrahedron() {
        twoTetrahedron.setName("someName");
        Tetrahedron actual = repository.getTetrahedron(1);
        Assert.assertEquals(actual, twoTetrahedron);
    }

    @Test
    public void testQueryByVolume() {
        Specification specification = new VolumeSpecification(2.0, 80.0);
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        List<Tetrahedron> actual = repository.query(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testQueryBySquare() {
        Specification specification = new SquareSpecification(2.0, 90.0);
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        List<Tetrahedron> actual = repository.query(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testQueryById() {
        Specification specification = new IdSpecification(1);
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron);
        List<Tetrahedron> actual = repository.query(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testQueryByName() {
        Specification specification = new NameSpecification("tetrahedron_2");
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, twoTetrahedron);
        List<Tetrahedron> actual = repository.query(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testQueryByFirstQuadrant() {
        Specification specification = new FirstQuadrantSpecification();
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, threeTetrahedron);
        List<Tetrahedron> actual = repository.query(specification);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTetrahedronById() {
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        List<Tetrahedron> actual = repository.sortTetrahedronById();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTetrahedronByName() {
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        List<Tetrahedron> actual = repository.sortTetrahedronByName();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTetrahedronPointOneByX() {
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        List<Tetrahedron> actual = repository.sortTetrahedronPointOneByX();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTetrahedronPointOneByY() {
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        List<Tetrahedron> actual = repository.sortTetrahedronPointOneByY();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTetrahedronPointOneByZ() {
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        List<Tetrahedron> actual = repository.sortTetrahedronPointOneByZ();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTetrahedronPointTwoByX() {
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        List<Tetrahedron> actual = repository.sortTetrahedronPointTwoByX();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTetrahedronPointTwoByY() {
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        List<Tetrahedron> actual = repository.sortTetrahedronPointTwoByY();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortTetrahedronPointTwoByZ() {
        List<Tetrahedron> expected = new ArrayList<>();
        Collections.addAll(expected, oneTetrahedron, twoTetrahedron, threeTetrahedron);
        List<Tetrahedron> actual = repository.sortTetrahedronPointTwoByZ();
        Assert.assertEquals(actual, expected);
    }
}