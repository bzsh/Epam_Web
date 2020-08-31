package by.epam.course.multithreading.thread;

import by.epam.course.multithreading.entity.Ferry;
import by.epam.course.multithreading.state.CarState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class FerryThread implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(FerryThread.class);
    private Ferry ferry;
    private static FerryThread instance;
    private static AtomicBoolean isStarted = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private CopyOnWriteArrayList<CarThread> registeredCars =  new CopyOnWriteArrayList<>();

    private FerryThread(Ferry ferry) {
        this.ferry = ferry;
    }

    public static FerryThread getInstance() {
        if (!isStarted.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new FerryThread(new Ferry());
                    isStarted.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public static FerryThread getInstance(int ferryCarrying, int ferryArea ) {
        if (!isStarted.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new FerryThread(new Ferry(ferryCarrying, ferryArea));
                    isStarted.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    @Override
    public void run() {
        while (registeredCars.size() > 0) {

            int actualCarring = ferry.getferryCarrying();
            int actualArea = ferry.getferryArea();
            int loadedCarsIndex = 0;

            while (true) {
                for (CarThread carThread : registeredCars) {
                    if (carThread.getCar().getState() == CarState.LOADED) {
                        continue;
                    }

                    if (actualCarring > carThread.getCar().getWeight() && actualArea > carThread.getCar().getArea()) {

                        carThread.getCar().setState(CarState.LOADED);
                        LOGGER.info("Car " + carThread.getCar().getId() + " was loaded to platform ");

                        actualCarring -= carThread.getCar().getWeight();
                        actualArea -= carThread.getCar().getArea();
                    } else {
                        LOGGER.info("Сar " + carThread.getCar().getId() + " cannot be taken to the platform.");
                    }
                }

                int currentLoadedCarsIndex = 0;

                for (CarThread carThread : registeredCars) {
                    if (carThread.getCar().getState() == CarState.LOADED)
                        currentLoadedCarsIndex++;
                }

                if (loadedCarsIndex == currentLoadedCarsIndex) {
                    break;
                } else {
                    loadedCarsIndex = currentLoadedCarsIndex;
                }

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    LOGGER.error("Error in run() method", e);
                }
            }
            try {
                LOGGER.info("Ferry is sailed");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                LOGGER.error("Error in run() method", e);
            }

            LOGGER.info("Ferry on the other side");

            for (CarThread carThread : registeredCars) {
                if (carThread.getCar().getState() == CarState.LOADED) {
                    carThread.getCar().setState(CarState.FERRIED);
                }
            }

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                LOGGER.error("Error in run() method", e);
            }

            LOGGER.info("Ferry returned");
        }
    }

    public void registerCar(CarThread carThread) {
        if (carThread.getCar().getState() == CarState.WAITING_FOR_LOADING) {
            registeredCars.add(carThread);
            LOGGER.info("Car " + carThread.getCar().getId() + " was registered to ferrying");
        }

        while (carThread.getCar().getState() != CarState.FERRIED) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                LOGGER.error("error in registerCar method", e);
            }
        }

        for (CarThread carThreadOnFerry : registeredCars) {
            if (carThreadOnFerry.getCar().getState() == CarState.FERRIED) {
                registeredCars.remove(carThreadOnFerry);
            }
        }
    }
}
