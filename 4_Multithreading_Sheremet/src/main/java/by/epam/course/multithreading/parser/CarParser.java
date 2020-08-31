package by.epam.course.multithreading.parser;

import by.epam.course.multithreading.entity.Car;
import by.epam.course.multithreading.entity.CarType;
import by.epam.course.multithreading.thread.CarThread;
import by.epam.course.multithreading.util.IdCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CarParser {
    private static final Logger LOGGER = LogManager.getLogger(CarParser.class);
    private static final String SEPARATOR_FOR_CARS = ";\\s+|;";
    private static final String SEPARATOR_FOR_PARAMETERS = "\\s";

    public List<CarThread> parse(List<String> strings) {
        LOGGER.info("CarParser is started");
        return parseCarThreads(strings);
    }

    private List<CarThread> parseCarThreads(List<String> strings) {
        List<CarThread> carThreads = new ArrayList<>();
        for (String string : strings) {
            String[] cars = string.split(SEPARATOR_FOR_CARS);
            CarType type = null;
            int weight;
            int area;

            for (String car : cars) {
                String[] parameters = car.split(SEPARATOR_FOR_PARAMETERS);
                if ("truck".equals(parameters[0])) {
                    type = CarType.TRUCK;
                } else if ("car".equals(parameters[0])) {
                    type = CarType.CAR;
                }

                weight = Integer.parseInt(parameters[1]);
                area = Integer.parseInt(parameters[2]);
                carThreads.add(new CarThread(new Car(IdCreator.createId(), type, weight, area)));
            }
        }
        return carThreads;
    }
}
