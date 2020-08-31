package by.epam.course.task.parser;

import by.epam.course.task.composite.TextComposite;
import by.epam.course.task.composite.CompositeType;
import by.epam.course.task.composite.Symbol;
import by.epam.course.task.composite.SymbolType;
import by.epam.course.task.exception.TextParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LexemeParser extends BaseParser {
    private static final Logger LOGGER = LogManager.getLogger(LexemeParser.class);

    public LexemeParser(BaseParser nextParser) {
        super(nextParser);
    }

    @Override
    public void parse(TextComposite lexemeTextComposite, String string) throws TextParseException {
        if (lexemeTextComposite.getCompositeType().equals(CompositeType.LEXEME)) {
            char[] chars = string.toCharArray();
            LOGGER.info("Lexeme was successfully parsed");
            StringBuilder builder = new StringBuilder();
            for (char character : chars) {
                if (Character.isLetterOrDigit(character)) {
                    builder.append(character);
                } else if (!Character.isLetterOrDigit(character)) {
                    if (builder.length() > 0) {
                        TextComposite wordTextComposite = new TextComposite(CompositeType.WORD);
                        lexemeTextComposite.add(wordTextComposite);
                        getNextParser().parse(wordTextComposite, builder.toString());
                        builder.delete(0, builder.length());
                    }
                    lexemeTextComposite.add(new Symbol(character, SymbolType.SIGN));
                }
            }
            if (builder.length() > 0) {
                TextComposite wordTextComposite = new TextComposite(CompositeType.WORD);
                lexemeTextComposite.add(wordTextComposite);
                getNextParser().parse(wordTextComposite, builder.toString());
            }
        } else {
            LOGGER.error("Wrong composite type in LexemeParser");
            throw new TextParseException("Invalid composite type " + lexemeTextComposite.getCompositeType());
        }
    }
}

