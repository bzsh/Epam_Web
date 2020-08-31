package by.epam.course.task.parser;

import by.epam.course.task.composite.TextComposite;
import by.epam.course.task.composite.CompositeType;
import by.epam.course.task.composite.Symbol;
import by.epam.course.task.composite.SymbolType;
import by.epam.course.task.exception.TextParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser extends BaseParser {
    private static final Logger LOGGER = LogManager.getLogger(WordParser.class);

    public WordParser() {
    }

    @Override
    public void parse(TextComposite wordTextComposite, String string) throws TextParseException {
        if (wordTextComposite.getCompositeType().equals(CompositeType.WORD)) {
            char[] chars = string.toCharArray();
            LOGGER.info("Word was successfully parsed");
            for (char character : chars) {
                wordTextComposite.add(new Symbol(character, SymbolType.LETTER));
            }
        } else {
            LOGGER.error("Wrong composite type in wordParser");
            throw new TextParseException("Invalid composite type " + wordTextComposite.getCompositeType());
        }
    }
}
