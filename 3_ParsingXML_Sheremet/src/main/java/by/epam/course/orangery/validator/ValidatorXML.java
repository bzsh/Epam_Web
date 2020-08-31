package by.epam.course.orangery.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class ValidatorXML {
    private static final Logger LOGGER = LogManager.getLogger(ValidatorXML.class);

    public static boolean validateXML(File xml, File xsd) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(xsd)
                    .newValidator()
                    .validate(new StreamSource(xml));
        } catch (IOException | SAXException e) {
            LOGGER.error("ValidatorXML : Error in validateXML method", e);
            return false;
        }
        LOGGER.info("ValidatorXML : XML file is valid");
        return true;
    }
}
