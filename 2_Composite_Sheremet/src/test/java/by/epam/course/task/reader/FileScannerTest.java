package by.epam.course.task.reader;

import by.epam.course.task.exception.ReadDataException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FileScannerTest {
    private FileScanner scanner = new FileScanner();
    private final static String PATH = "src\\main\\resources\\file\\text.txt";

    @Test(description = "read data from file")
    public void testFileScanner() throws ReadDataException {
        String expected = "    It has survived - not only (five) centuries, but also the leap into 13<<2 electronic\n" +
                "typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in\n" +
                "the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets\n" +
                "containing Lorem Ipsum passages, and more recently with desktop publishing software\n" +
                "like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "    It is a long established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using\n" +
                "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal\n" +
                "distribution of letters, as opposed to using (Content here), content here', making it look\n" +
                "like readable English.\n" +
                "    It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a\n" +
                "page when looking at its layout.\n" +
                "    Bye.";

        String actual = scanner.readData(PATH);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "file is not exist or empty", expectedExceptions = ReadDataException.class)
    public void testFileExistence() throws ReadDataException {
        String actual = scanner.readData("");
    }
}