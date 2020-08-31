package by.epam.course.multithreading.runner;

import by.epam.course.multithreading.exception.ReadDataException;
import by.epam.course.multithreading.parser.CarParser;
import by.epam.course.multithreading.scanner.FileScanner;
import by.epam.course.multithreading.thread.CarThread;
import by.epam.course.multithreading.thread.FerryThread;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Start {

    public static final String PATH = "src\\main\\resources\\data\\cars.txt";

    public static void main(String[] args) {
        List<CarThread> cars = null;
        FileScanner scanner = new FileScanner();
        CarParser parser = new CarParser();

        ExecutorService service = Executors.newCachedThreadPool();

        try {
            cars = parser.parse(scanner.readData(PATH));
        } catch (ReadDataException e) {
            e.printStackTrace();
        }

        for (CarThread car : cars) {
            service.submit(car);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        service.submit(FerryThread.getInstance());
        service.shutdown();
    }
}

