package by.epam.course.task.parser;

import by.epam.course.task.composite.TextComposite;
import by.epam.course.task.composite.CompositeType;
import by.epam.course.task.exception.TextParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser extends BaseParser {
    private static final Logger LOGGER = LogManager.getLogger(TextParser.class);
    private final String PARAGRAPH_SPLITTER = "(?=([ \\t]{4,}))";

    public TextParser(BaseParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parse(TextComposite textComposite, String string) throws TextParseException {

        if (textComposite.getCompositeType().equals(CompositeType.TEXT)) {
            String[] paragraphs = string.split(PARAGRAPH_SPLITTER);
            LOGGER.info("Text was successfully parsed");
            for (String paragraph : paragraphs) {
                TextComposite paragraphTextComposite = new TextComposite(CompositeType.PARAGRAPH);
                textComposite.add(paragraphTextComposite);
                getNextParser().parse(paragraphTextComposite, paragraph);
            }
        } else {
            LOGGER.error("Wrong composite type in TextParser");
            throw new TextParseException("Invalid composite type " + textComposite.getCompositeType());

        }
    }
}
