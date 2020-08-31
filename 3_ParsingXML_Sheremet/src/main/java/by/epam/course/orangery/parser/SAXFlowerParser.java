package by.epam.course.orangery.parser;


import by.epam.course.orangery.exception.ParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXFlowerParser extends BaseParser {
    private static final Logger LOGGER = LogManager.getLogger(SAXFlowerParser.class);

    @Override
    public void buildFlowers(String path) throws ParsingException {
        LOGGER.info("SAXFlowerParser was started");
        if (path == null || path.isEmpty()) {
            LOGGER.error("Wrong path in buildFlowers method");
            throw new ParsingException("Wrong path in buildFlowers method");
        }

        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            FlowerHandler flowerHandler = new FlowerHandler();
            parser.parse(new File(path), flowerHandler);
            flowers = flowerHandler.getFlowers();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            LOGGER.error(e.getStackTrace());
            throw new ParsingException("SAXParser error");
        }
    }
}
