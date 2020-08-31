package by.epam.course.shape.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class FileDataValidatorTest {

    @DataProvider(name = "testData")
    public Object[][] getDataFromDataprovider() {
        return new Object[][]{
                {"0.123 0 1.234; 1 -4 0.583; 1.44 0 0; 0.1 1 0.345;", true},
                {"0.77 0 fx; 0 -1.56 7.1; 2.11 7 1; 1.3 2 13;", false},
                {"1.2 1.2 1.2; -1.2 -1.2 1.2; -1.2 1.2 -1.2; 1.2 -1.2 -1.2;", true},
                {"0.123 0 1.234; 1 -4 0.583; 1.44 0 0; 0.1 1 0.345;", true},
                {"0.5 0 1.5; 0 -1 0q; 2.1 0 0; 0 1.1 0;", false},
                {"1.2 1.2 1.2; -1.2 -1.2 1.2; -1.2 1.2 -1.2; 1.2 -1.2 -1.2;", true}
        };
    }

    @Test(dataProvider = "testData")
    public void testData(String string, boolean expected) {
        FileDataValidator validator = new FileDataValidator();
        boolean actual = validator.isDataCorrect(string);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "fileTest")
    public void testFile() {
        FileDataValidator validator = new FileDataValidator();
        String path = "src\\main\\resources\\file\\testFile.txt";
        File file = new File(path);
        boolean expected = false;
        boolean actual = validator.isFileCorrect(file);
        Assert.assertEquals(actual, expected);
    }
}