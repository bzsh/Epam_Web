package by.epam.course.orangery.parser;

import by.epam.course.orangery.entity.*;
import by.epam.course.orangery.util.DateConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class FlowerHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(FlowerHandler.class);
    private List<Flower> flowers;
    private Flower flower;
    private GrowingTip growingTip;
    private Visual visual;
    private String content;

    public FlowerHandler() {
        LOGGER.info("FlowerHandler was created");
        flowers = new ArrayList<>();
    }


    public List<Flower> getFlowers() {
        return flowers;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(FlowerEnum.FLOWER.getField())) {
            flower = new Flower();
            visual = new Visual();
            growingTip = new GrowingTip();
            flower.setId(attributes.getValue(FlowerEnum.ID.getField()));
            flower.setName(attributes.getValue(FlowerEnum.NAME.getField()));
            LOGGER.info("flower id: " + flower.getId() + ", name: " + flower.getName() + " successfully created.");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (FlowerEnum.of(qName).isPresent()) {
            FlowerEnum flowerEnum = FlowerEnum.of(qName).get();
            switch (flowerEnum) {
                case FLOWER:
                    flowers.add(flower);
                    break;
                case SOIL:
                    flower.setSoil(Soil.of(content));
                    break;
                case ORIGIN:
                    flower.setOrigin(content);
                    break;
                case LEAF_COLOR:
                    visual.setLeafColor(content);
                    break;
                case STEM_COLOR:
                    visual.setStemColor(content);
                    break;
                case LENGTH:
                    visual.setLength(Integer.valueOf(content));
                    break;
                case VISUAL:
                    flower.setVisual(visual);
                    break;
                case LIGHTING:
                    growingTip.setLighting(Boolean.valueOf(content));
                    break;
                case WATERING:
                    growingTip.setWatering(Integer.valueOf(content));
                    break;
                case TEMPERATURE:
                    growingTip.setTemperature(Integer.valueOf(content));
                    break;
                case GROWING_TIPS:
                    flower.setGrowingTip(growingTip);
                    break;
                case DATE_LANDING:
                    flower.setDateLanding(DateConverter.convertDate(content));
                    break;
                case MULTIPLYING:
                    flower.setMultiplying(Multiplying.of(content));
                    break;
            default:
                LOGGER.error("Constant not present.");
                throw new EnumConstantNotPresentException(flowerEnum.getDeclaringClass(), flowerEnum.name());
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        content = String.copyValueOf(ch, start, length).trim();
    }
}
