package by.epam.course.task.parser;

import by.epam.course.task.composite.TextComposite;
import by.epam.course.task.composite.CompositeType;
import by.epam.course.task.exception.TextParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser extends BaseParser {
    private static final Logger LOGGER = LogManager.getLogger(ParagraphParser.class);
    private final String SENTENCE_SPLITTER = "(?<=([\\p{Alnum}][.!?…]))\\s";

    public ParagraphParser(BaseParser nextParser) {
        super(nextParser);
    }


    @Override
    public void parse(TextComposite paragraphTextComposite, String string) throws TextParseException {

        if (paragraphTextComposite.getCompositeType().equals(CompositeType.PARAGRAPH)) {
            String[] sentences = string.split(SENTENCE_SPLITTER);
            LOGGER.info("Paragraph was successfully parsed");
            for (String sentence : sentences) {
                TextComposite sentenceTextComposite = new TextComposite(CompositeType.SENTENCE);
                paragraphTextComposite.add(sentenceTextComposite);
                getNextParser().parse(sentenceTextComposite, sentence);
            }
        } else {
            LOGGER.error("Wrong composite type in ParagraphParser");
            throw new TextParseException("Invalid composite type " + paragraphTextComposite.getCompositeType());

        }
    }
}
