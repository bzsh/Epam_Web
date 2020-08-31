package by.epam.course.shape.creator;

import by.epam.course.shape.entity.Point;
import by.epam.course.shape.entity.Tetrahedron;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TetrahedronCreatorTest {

    @Test(description = "Tetrahedron creator test")
    public void testCreateTetrahedronList() {
        TetrahedronCreator creator = TetrahedronCreator.getInstance();
        List<Tetrahedron> actual = creator.createTetrahedronList("src\\main\\resources\\file\\testFile.txt");
        List<Tetrahedron> expected = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        Collections.addAll(points, new Point(1.2, 1.2, 1.2), new Point(-1.2, -1.2, 1.2), new Point(-1.2, 1.2, -1.2), new Point(1.2, -1.2, -1.2));
        for(Tetrahedron tetra : actual){
            int id = tetra.getId();
            String name = "Tetrahedron_" + id;
            expected.add(new Tetrahedron(id, points, name));
        }
        Assert.assertEquals(actual, expected);
    }
}