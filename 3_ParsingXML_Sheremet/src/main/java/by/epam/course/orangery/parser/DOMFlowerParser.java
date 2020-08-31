package by.epam.course.orangery.parser;

import by.epam.course.orangery.entity.*;
import by.epam.course.orangery.exception.ParsingException;
import by.epam.course.orangery.util.DateConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;

public class DOMFlowerParser extends BaseParser {
    private static final Logger LOGGER = LogManager.getLogger(DOMFlowerParser.class);
    private DocumentBuilder builder;

    public DOMFlowerParser() throws ParsingException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Error in DOMFlowerParser constructor");
            throw new ParsingException(e);
        }
        LOGGER.info("DOMFlowerParser was started");
    }

    @Override
    public void buildFlowers(String path) throws ParsingException {
        if (path == null || path.isEmpty()) {
            LOGGER.error("Wrong path in buildFlowers method");
            throw new ParsingException("Wrong path in buildFlowers method");
        }
        Document document;

        try {
            document = builder.parse(path);
            Element element = document.getDocumentElement();
            NodeList flowerNodes = element.getElementsByTagName(FlowerEnum.FLOWER.getField());
            for(int i = 0; i < flowerNodes.getLength(); i++){
                Element flowerElement = (Element)flowerNodes.item(i);
                Flower flower = buildFlower(flowerElement);
                flowers.add(flower);
            }
        } catch (SAXException | IOException e) {
            LOGGER.error("Error in buildFlowers method ", e);
            throw new ParsingException(e);
        }
    }

    private Flower buildFlower(Element element) {
        Flower flower = new Flower();
        String origin = getElementTextContent(element, FlowerEnum.ORIGIN.getField());
        String multiplying = getElementTextContent(element, FlowerEnum.MULTIPLYING.getField());
        String soil = getElementTextContent(element, FlowerEnum.SOIL.getField());
        String leafColor = getElementTextContent(element, FlowerEnum.LEAF_COLOR.getField());
        String stemColor = getElementTextContent(element, FlowerEnum.STEM_COLOR.getField());
        int length = Integer.valueOf(getElementTextContent(element, FlowerEnum.LENGTH.getField()));
        int watering = Integer.valueOf(getElementTextContent(element, FlowerEnum.WATERING.getField()));
        int temperature = Integer.valueOf(getElementTextContent(element, FlowerEnum.TEMPERATURE.getField()));
        boolean lighting = Boolean.valueOf(getElementTextContent(element, FlowerEnum.LIGHTING.getField()));
        LocalDate date = DateConverter.convertDate(getElementTextContent(element, FlowerEnum.DATE_LANDING.getField()));

        Visual visual = new Visual(leafColor, stemColor, length);
        GrowingTip growingTip = new GrowingTip(temperature, lighting, watering);

        flower.setId(element.getAttribute(FlowerEnum.ID.getField()));
        flower.setName(element.getAttribute(FlowerEnum.NAME.getField()));
        flower.setOrigin(origin);
        flower.setVisual(visual);
        flower.setDateLanding(date);
        flower.setGrowingTip(growingTip);
        flower.setSoil(Soil.of(soil));
        flower.setMultiplying(Multiplying.of(multiplying));

        return flower;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
