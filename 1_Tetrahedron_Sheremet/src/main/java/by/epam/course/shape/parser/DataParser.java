package by.epam.course.shape.parser;

import by.epam.course.shape.entity.Point;
import by.epam.course.shape.exception.IncorrectDataException;
import by.epam.course.shape.validator.FileDataValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DataParser {
    private static final Logger LOGGER = LogManager.getLogger(DataParser.class);
    private static final String SEPARATOR_FOR_POINTS = ";\\s+|;";
    private static final String SEPARATOR_FOR_COORDINATES = "\\s";

    public List<Point> parse(String string) throws IncorrectDataException {
        FileDataValidator validator = new FileDataValidator();
        boolean result = validator.isDataCorrect(string);
        if (result) {
            List<Point> points = parseStringToPoints(string);
            LOGGER.info("String was successfully parsed");
            return points;
        } else {
            LOGGER.warn("Incorrect data:" + string);
            throw new IncorrectDataException("Incorrect data" + string);
        }
    }

    private List<Point> parseStringToPoints(String string) {
        List<Point> points = new ArrayList<>();
        String[] arr = string.split(SEPARATOR_FOR_POINTS);
        List<Double> coordinates;
        for (String point : arr) {
            coordinates = new ArrayList<>();
            String[] strings = point.split(SEPARATOR_FOR_COORDINATES);
            for (String str : strings) {
                coordinates.add(Double.parseDouble(str));
            }
            points.add(new Point(coordinates));
            coordinates.clear();
        }
        return points;
    }

}
