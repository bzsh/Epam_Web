package by.epam.course.shape.warehouse;

import by.epam.course.shape.entity.TetrahedronCharacteristic;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private static final Warehouse INSTANCE = new Warehouse();
    private List<TetrahedronCharacteristic> characteristicList = new ArrayList<>();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    public void addCharacteristic(TetrahedronCharacteristic characteristic) {
        characteristicList.add(characteristic);
    }

    public void removeCharacteristic(TetrahedronCharacteristic characteristic) {
        characteristicList.remove(characteristic);
    }

    public void updateCharacteristic(TetrahedronCharacteristic characteristic) {

        characteristicList.removeIf(characteristicFromList -> characteristicFromList.getId() == characteristic.getId());
        characteristicList.add(characteristic);
    }

    public List<TetrahedronCharacteristic> getCharacteristicList() {
        return characteristicList;
    }
}
