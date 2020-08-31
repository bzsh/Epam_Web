package by.epam.course.shape.reader;

import by.epam.course.shape.exception.ReadDataException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileScannerTest {
    private FileScanner scanner = new FileScanner();

    @Test(description = "read data from file")
    public void testFileScanner() throws ReadDataException {
        List<String> expected = new ArrayList<>();
        Collections.addAll(expected,
                "0.123 0 1.234; 1 -4 0.583; 1.44 0 0; 0.1 1 0.345;",
                "0.77 0 fx; 0 -1.56 7.1; 2.11 7 1; 1.3 2 13;",
                "1.2 1.2 1.2; -1.2 -1.2 1.2; -1.2 1.2 -1.2; 1.2 -1.2 -1.2;",
                "0.123 0 1.234; 1 -4 0.583; 1.44 0 0; 0.1 1 0.345;",
                "0.5 0 1.5; 0 -1 0q; 2.1 0 0; 0 1.1 0;",
                "1.2 1.2 1.2; -1.2 -1.2 1.2; -1.2 1.2 -1.2; 1.2 -1.2 -1.2;");
        List<String> actual = scanner.readData("src\\main\\resources\\file\\testFile.txt");
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "file is not exist or empty", expectedExceptions = ReadDataException.class)
    public void testFileExistence() throws ReadDataException {
        List<String> actual = scanner.readData("src\\main\\resources\\file\\someFile.txt");
    }
}