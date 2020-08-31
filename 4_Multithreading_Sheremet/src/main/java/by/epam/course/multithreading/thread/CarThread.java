package by.epam.course.multithreading.thread;

import by.epam.course.multithreading.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CarThread implements Runnable{
    private static final Logger LOGGER = LogManager.getLogger(CarThread.class);
    private Car car;

    public CarThread(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public void run() {
        LOGGER.info("Car " + car.getId() + "  drove up to the ferry and waiting for registration");

       FerryThread.getInstance().registerCar(this);

        LOGGER.info("Сar " + car.getId() + " was successfully transported");
    }
}
