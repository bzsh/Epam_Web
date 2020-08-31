package by.epam.course.task.composite;

import by.epam.course.task.exception.ReadDataException;
import by.epam.course.task.exception.TextParseException;
import by.epam.course.task.parser.*;
import by.epam.course.task.reader.FileScanner;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CompositeTest {
    private TextComposite textComposite = new TextComposite(CompositeType.TEXT);
    private BaseParser parser = new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser(new WordParser()))));
    private FileScanner scanner = new FileScanner();
    private final static String PATH = "src\\main\\resources\\file\\text.txt";

    @BeforeTest
    public void setUp() throws TextParseException, ReadDataException {
        String data = scanner.readData(PATH);
        parser.parse(textComposite, data);
    }

    @Test
    public void testComposite() {
        String expected = "    It has survived - not only (five) centuries, but also the leap into 13<<2 electronic\n" +
                "typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in\n" +
                "the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets\n" +
                "containing Lorem Ipsum passages, and more recently with desktop publishing software\n" +
                "like Aldus PageMaker including versions of Lorem Ipsum. \n" +
                "    It is a long established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using\n" +
                "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal\n" +
                "distribution of letters, as opposed to using (Content here), content here', making it look\n" +
                "like readable English. \n" +
                "    It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a\n" +
                "page when looking at its layout. \n" +
                "    Bye. \n";
        String actual = textComposite.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test(description = "Wrong composite type in TextParser", expectedExceptions = TextParseException.class)
    public void testFileExistence() throws TextParseException {
        parser.parse(new TextComposite(CompositeType.LEXEME), "");
    }
}