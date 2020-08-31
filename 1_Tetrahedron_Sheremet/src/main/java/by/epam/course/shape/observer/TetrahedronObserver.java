package by.epam.course.shape.observer;

import by.epam.course.shape.action.TetrahedronCalc;
import by.epam.course.shape.entity.Tetrahedron;
import by.epam.course.shape.entity.TetrahedronCharacteristic;
import by.epam.course.shape.warehouse.Warehouse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TetrahedronObserver implements Observer {

    private static final Logger LOGGER = LogManager.getLogger(Observer.class);
    private String name;

    public TetrahedronObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Tetrahedron tetrahedron) {
        LOGGER.info("\n" + name + ": Changes detected : " + tetrahedron);
        TetrahedronCalc calculator = new TetrahedronCalc();

        int id = tetrahedron.getId();
        double square = calculator.calculateSquare(tetrahedron);
        double volume = calculator.calculateVolume(tetrahedron);
        double perimeter = calculator.calculatePerimeter(tetrahedron);

        Warehouse warehouse = Warehouse.getInstance();
        warehouse.updateCharacteristic(new TetrahedronCharacteristic(id, square, volume, perimeter));
    }
}
