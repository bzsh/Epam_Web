package by.epam.course.orangery.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ValidatorXMLTest {

    private static final String xmlPath = "src\\main\\resources\\orangery.xml";
    private static final String xsdPath = "src\\main\\resources\\orangery.xsd";
    private static final String xmlPath2 = "src\\main\\resources\\test.xml";
    private static final String xsdPath2 = "src\\main\\resources\\test.xsd";

    @Test
    public void validate_inputStream_trueTest() {
        File xmlfile = new File(xmlPath);
        File xsdfile = new File(xsdPath);

        boolean actual = ValidatorXML.validateXML(xmlfile, xsdfile);
        Assert.assertTrue(actual);
    }

    @Test
    public void validate_inputStream_falseTset() {
        File xmlfile = new File(xmlPath2);
        File xsdfile = new File(xsdPath);

        boolean actual = ValidatorXML.validateXML(xmlfile, xsdfile);
        Assert.assertFalse(actual);

    }

}