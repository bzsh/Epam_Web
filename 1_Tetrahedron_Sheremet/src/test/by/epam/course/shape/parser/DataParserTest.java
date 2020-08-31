package by.epam.course.shape.parser;

import by.epam.course.shape.entity.Point;
import by.epam.course.shape.exception.IncorrectDataException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataParserTest {
    private DataParser parser;

    @BeforeClass
    public void setUp() {
        parser = new DataParser();
    }

    @AfterClass
    public void tierDown() {
        parser = null;
    }

    @Test(description = "data is valid")
    public void testParse()  throws IncorrectDataException{
        List<Point> expected = new ArrayList<>();
        Collections.addAll(expected,
                new Point(1.2, 1.2, 1.2),
                new Point(-1.2, -1.2, 1.2),
                new Point(-1.2, 1.2, -1.2),
                new Point(1.2, -1.2, -1.2));
        List<Point> actual = parser.parse( "1.2 1.2 1.2; -1.2 -1.2 1.2; -1.2 1.2 -1.2; 1.2 -1.2 -1.2;");
        Assert.assertEquals(actual,expected);
    }

    @Test(description = "data is incorrect", expectedExceptions =  IncorrectDataException.class)
    public void testParseIncorrectData() throws IncorrectDataException {
        List<Point> actual = parser.parse("1.2 1.2 1.2; -1.2 -1.2 1.2; -1.2 1.2 -1.2; 1.2 -1.2 -1.2");

    }
}