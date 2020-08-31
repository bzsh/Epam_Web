package by.epam.course.task.parser;

import by.epam.course.task.composite.TextComposite;
import by.epam.course.task.exception.TextParseException;

public abstract class BaseParser {
    private BaseParser nextParser;

    BaseParser(BaseParser nextParser) {
        this.nextParser = nextParser;
    }

    BaseParser() {
    }

    BaseParser getNextParser() {
        return nextParser;
    }

    public abstract void parse(TextComposite textComposite, String string) throws TextParseException;
}
