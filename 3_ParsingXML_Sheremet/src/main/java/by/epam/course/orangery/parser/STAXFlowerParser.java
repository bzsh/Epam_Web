package by.epam.course.orangery.parser;

import by.epam.course.orangery.entity.*;
import by.epam.course.orangery.exception.ParsingException;
import by.epam.course.orangery.util.DateConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class STAXFlowerParser extends BaseParser {
    private static final Logger LOGGER = LogManager.getLogger(STAXFlowerParser.class);
    private XMLInputFactory inputFactory;

    public STAXFlowerParser() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildFlowers(String path) throws ParsingException {
        LOGGER.info("STAXFlowerParser was started");
        FileInputStream inputStream;
        XMLStreamReader reader;
        String name;

        try {
            inputStream = new FileInputStream(new File(path));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamReader.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(FlowerEnum.FLOWER.getField())) {
                        Flower flower = buildFlower(reader);
                        flowers.add(flower);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            LOGGER.error("Error in buildFlowers method", e);
            throw new ParsingException("Error in buildFlowers method", e);
        }

    }

    private Flower buildFlower(XMLStreamReader reader) throws ParsingException {
        Flower flower = new Flower();
        flower.setId(reader.getAttributeValue(null, FlowerEnum.ID.getField()));
        flower.setName(reader.getAttributeValue(null, FlowerEnum.NAME.getField()));
        String name;
        int type;

        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        if (FlowerEnum.of(name).isPresent()) {
                            FlowerEnum flowerEnum = FlowerEnum.of(name).get();

                            switch (flowerEnum) {
                                case DATE_LANDING:
                                    flower.setDateLanding(DateConverter.convertDate(getXMLText(reader)));
                                    break;
                                case ORIGIN:
                                    flower.setOrigin(getXMLText(reader));
                                    break;
                                case SOIL:
                                    flower.setSoil(Soil.of(getXMLText(reader)));
                                    break;
                                case MULTIPLYING:
                                    flower.setMultiplying(Multiplying.of(getXMLText(reader)));
                                    break;
                                case VISUAL:
                                    flower.setVisual(buildVisualFromXML(reader));
                                    break;
                                case GROWING_TIPS:
                                    flower.setGrowingTip(buildGrowingTipFromXML(reader));
                                    break;
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (name.equals(FlowerEnum.FLOWER.getField())) {
                            return flower;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error("Error in buildFlower method", e);
            throw new ParsingException("Unknown element in tag Flower");
        }
        return flower;
    }

    private Visual buildVisualFromXML(XMLStreamReader reader) throws ParsingException {
        Visual visual = new Visual();
        String name;
        int type;

        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        if (FlowerEnum.of(name).isPresent()) {
                            FlowerEnum flowerEnum = FlowerEnum.of(name).get();
                            switch (flowerEnum) {
                                case LEAF_COLOR:
                                    visual.setLeafColor(getXMLText(reader));
                                    break;
                                case STEM_COLOR:
                                    visual.setStemColor(getXMLText(reader));
                                    break;
                                case LENGTH:
                                    visual.setLength(Integer.valueOf(getXMLText(reader)));
                                    break;
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (name.equals(FlowerEnum.VISUAL.getField())) {
                            return visual;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error("Error in buildVisualFromXML method", e);
            throw new ParsingException("Unknown element in visual");
        }
        return visual;
    }

    private GrowingTip buildGrowingTipFromXML(XMLStreamReader reader) throws ParsingException {
        GrowingTip growingTip = new GrowingTip();
        String name;
        int type;

        try {
            while (reader.hasNext()) {
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        if (FlowerEnum.of(name).isPresent()) {
                            FlowerEnum flowerEnum = FlowerEnum.of(name).get();
                            switch (flowerEnum) {
                                case LIGHTING:
                                    growingTip.setLighting(Boolean.valueOf(getXMLText(reader)));
                                    break;
                                case WATERING:
                                    growingTip.setWatering(Integer.valueOf(getXMLText(reader)));
                                    break;
                                case TEMPERATURE:
                                    growingTip.setTemperature(Integer.valueOf(getXMLText(reader)));
                                    break;
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (name.equals(FlowerEnum.GROWING_TIPS.getField())) {
                            return growingTip;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            LOGGER.error("Error in buildGrowingTipFromXML method", e);
            throw new ParsingException("Unknown element in growingTop", e);
        }
        return growingTip;
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String nextText = "";
        if (reader.hasNext()) {
            reader.next();
            nextText = reader.getText();
        }
        return nextText;
    }
}
