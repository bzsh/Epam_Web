package by.epam.course.task.action;

import by.epam.course.task.composite.Component;
import by.epam.course.task.composite.CompositeType;
import by.epam.course.task.composite.TextComposite;
import by.epam.course.task.exception.TextParseException;
import by.epam.course.task.parser.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class TextSorterTest {
    private TextSorter sorter = new TextSorter();
    private TextComposite textComposite = new TextComposite(CompositeType.TEXT);
    private BaseParser parser = new TextParser(new ParagraphParser(new SentenceParser(new LexemeParser(new WordParser()))));


    @BeforeTest
    public void setUp() throws TextParseException {
        String text = "    It has survived - not only (five) centuries, but also the leap into 13<<2 electronic\n" +
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
        parser.parse(textComposite, text);
    }

    @Test
    public void testSortParagraphsByNumberOfSentences() {
        String expected = " It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a\n" +
                "page when looking at its layout.  Bye.  It has survived - not only (five) centuries, but also the leap into 13<<2 electronic\n" +
                "typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in\n" +
                "the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets\n" +
                "containing Lorem Ipsum passages, and more recently with desktop publishing software\n" +
                "like Aldus PageMaker including versions of Lorem Ipsum.  It is a long established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using\n" +
                "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal\n" +
                "distribution of letters, as opposed to using (Content here), content here', making it look\n" +
                "like readable English. ";

        StringBuilder builder = new StringBuilder();
        List<Component> components = sorter.sortParagraphsByNumberOfSentences(textComposite);
        components.forEach(builder::append);
        String actual = builder.toString();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testSortSentencesByNumberOfWords() {
        String expected = " Bye.  It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a\n" +
                "page when looking at its layout.  It has survived - not only (five) centuries, but also the leap into 13<<2 electronic\n" +
                "typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged.  It is a long established fact that a reader will be distracted by the readable\n" +
                "content of a page when looking at its layout. The point of using\n" +
                "(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal\n" +
                "distribution of letters, as opposed to using (Content here), content here', making it look\n" +
                "like readable English. It was popularised in\n" +
                "the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets\n" +
                "containing Lorem Ipsum passages, and more recently with desktop publishing software\n" +
                "like Aldus PageMaker including versions of Lorem Ipsum. ";

        StringBuilder builder = new StringBuilder();
        List<Component> components = sorter.sortSentencesByNumberOfWords(textComposite);
        components.forEach(builder::append);
        String actuial = builder.toString();
        Assert.assertEquals(actuial, expected);
    }

    @Test
    public void testSortWordsByLength() {
        String expected = "2 3 5 6 9 3 4 5 1 2 3 4 1 5 6 3 4 7 1 a a a 2 3 3 2 1 2 2 2 2 a a 7 5 1 2 2 5 2 a a" +
                " It 13 It in 47 89 42 of of It is be by of at of 71 10 78 is it or of as to it It is 71 be of " +
                "at has not but the was the the and the its The has its Bye only five also leap into with more " +
                "with like long fact that will page when that more less here here look like 1200 fact that will " +
                "page when Lorem Ipsum Aldus Lorem Ipsum point using Ipsum using sheets reader layout normal making" +
                " reader layout release desktop content looking letters opposed Content content English looking " +
                "survived Letraset passages recently software versions readable readable centuries remaining " +
                "unchanged PageMaker including electronic containing publishing distracted typesetting essentially" +
                " popularised established established distribution ";
        StringBuilder builder = new StringBuilder();
        List<Component> components = sorter.sortWordsByLength(textComposite);

        for (Component component : components) {
            builder.append(component);
            builder.append(" ");
        }
        String actuial = builder.toString();
        Assert.assertEquals(actuial, expected);
    }
}