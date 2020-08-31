package by.epam.course.orangery.parser;

import by.epam.course.orangery.entity.*;
import by.epam.course.orangery.exception.ParsingException;
import by.epam.course.orangery.util.DateConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class STAXFlowerParserTest {
    private List<Flower> expectedFlowerList;
    private List<Flower> actualFlowerList;

    private static final String path = "src\\main\\resources\\test.xml";

    @BeforeTest
    public void setUp() {
        Flower flowerOne = new Flower();
        Flower flowerTwo = new Flower();

        flowerOne.setId("a1");
        flowerOne.setName("Amaryllis");
        flowerOne.setDateLanding(DateConverter.convertDate("2019-10-01"));
        flowerOne.setSoil(Soil.GROUND);
        flowerOne.setMultiplying(Multiplying.CUTTING);
        flowerOne.setOrigin("USSR");
        flowerOne.setGrowingTip(new GrowingTip(21, true, 5));
        flowerOne.setVisual(new Visual("green", "black", 25));

        flowerTwo.setId("a2");
        flowerTwo.setName("Flabellata");
        flowerTwo.setDateLanding(DateConverter.convertDate("2019-10-01"));
        flowerTwo.setSoil(Soil.GROUND);
        flowerTwo.setMultiplying(Multiplying.CUTTING);
        flowerTwo.setOrigin("Montenegro");
        flowerTwo.setGrowingTip(new GrowingTip(27, true, 53));
        flowerTwo.setVisual(new Visual("black", "black", 25));


        expectedFlowerList = new ArrayList<>();
        expectedFlowerList.add(flowerOne);
        expectedFlowerList.add(flowerTwo);
    }

    @Test
    public void DomFlowerParserTest() throws ParsingException {
        BaseParser parser = new STAXFlowerParser();
        parser.buildFlowers(path);
        actualFlowerList = parser.getFlowers();
        Assert.assertEquals(actualFlowerList, expectedFlowerList);
    }

}