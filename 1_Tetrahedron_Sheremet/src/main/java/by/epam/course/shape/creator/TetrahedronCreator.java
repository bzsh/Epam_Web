package by.epam.course.shape.creator;

import by.epam.course.shape.entity.Point;
import by.epam.course.shape.entity.Tetrahedron;
import by.epam.course.shape.entity.TetrahedronCharacteristic;
import by.epam.course.shape.exception.IncorrectDataException;
import by.epam.course.shape.exception.ReadDataException;
import by.epam.course.shape.parser.DataParser;
import by.epam.course.shape.reader.FileScanner;
import by.epam.course.shape.repository.TetrahedronRepository;
import by.epam.course.shape.validator.TetrahedronValidator;
import by.epam.course.shape.warehouse.Warehouse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TetrahedronCreator {
    private static final Logger LOGGER = LogManager.getLogger(TetrahedronCreator.class);
    private static final TetrahedronCreator INSTANCE = new TetrahedronCreator();
    private TetrahedronRepository repository = TetrahedronRepository.getInstance();
    private TetrahedronValidator validator = new TetrahedronValidator();
    private DataParser parser = new DataParser();
    private Warehouse warehouse = Warehouse.getInstance();

    private TetrahedronCreator() {
    }

    public static TetrahedronCreator getInstance() {
        return INSTANCE;
    }

    public List<Tetrahedron> createTetrahedronList(String path) {
        Tetrahedron tetrahedron;
        List<String> strings = this.getDataFromFile(path);
        List<Point> pointList;
        List<Tetrahedron> tetrahedronList = new ArrayList<>();
        for (String string : strings) {
            try {
                pointList = parser.parse(string);
                if (validator.isTetrahedron(pointList)) {
                    tetrahedron = Tetrahedron.createTetrahedron(pointList);
                    LOGGER.info("Tetrahedron id = " + tetrahedron.getId() + " was successfully created." + pointList);
                    tetrahedronList.add(tetrahedron);
                    repository.addTetrahedron(tetrahedron);
                    addCharacteristicToWarehouse(tetrahedron);
                } else {
                    LOGGER.warn("Tetrahedron cannot exist :" + pointList);
                }
            } catch (IncorrectDataException e) {
                LOGGER.warn("Incorrect data to parse:" + string);
            }
        }
        LOGGER.info("Tetrahedrons was successfully created.");
        return tetrahedronList;
    }

    private List<String> getDataFromFile(String path) {
        FileScanner scanner = new FileScanner();
        try {
            return scanner.readData(path);
        } catch (ReadDataException e) {
            LOGGER.fatal(e);
            throw new RuntimeException(e);
        }
    }

    private void addCharacteristicToWarehouse(Tetrahedron tetrahedron) {
        warehouse.addCharacteristic(new TetrahedronCharacteristic(tetrahedron));
    }
}