package by.epam.course.task.parser;

import by.epam.course.task.composite.TextComposite;
import by.epam.course.task.composite.CompositeType;
import by.epam.course.task.exception.TextParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser extends BaseParser {
    private static final Logger LOGGER = LogManager.getLogger(SentenceParser.class);
    private final String LEXEME_SPLITTER = "\\p{Blank}+";

    public SentenceParser(BaseParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parse(TextComposite sentenceTextComposite, String string) throws TextParseException {
        if (sentenceTextComposite.getCompositeType().equals(CompositeType.SENTENCE)) {
            String[] lexemes = string.split(LEXEME_SPLITTER);
            LOGGER.info("Sentence was successfully parsed");
            for (String lexeme : lexemes) {
                TextComposite lexemeTextComposite = new TextComposite(CompositeType.LEXEME);
                sentenceTextComposite.add(lexemeTextComposite);
                getNextParser().parse(lexemeTextComposite, lexeme);
            }
        } else {
            LOGGER.error("Wrong composite type in SentenceParser");
            throw new TextParseException("Invalid composite type " + sentenceTextComposite.getCompositeType());
        }
    }
}
