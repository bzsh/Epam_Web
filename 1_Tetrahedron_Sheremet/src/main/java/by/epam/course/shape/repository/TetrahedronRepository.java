package by.epam.course.shape.repository;

import by.epam.course.shape.entity.Tetrahedron;
import by.epam.course.shape.specification.Specification;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TetrahedronRepository {
    private final static TetrahedronRepository INSTANCE = new TetrahedronRepository();
    private List<Tetrahedron> tetrahedronList = new ArrayList<>();

    private TetrahedronRepository() {
    }

    public static TetrahedronRepository getInstance() {
        return INSTANCE;
    }

    public void addTetrahedron(Tetrahedron tetrahedron) {
        tetrahedronList.add(tetrahedron);
    }

    public void removeTetrahedron(Tetrahedron tetrahedron) {
        tetrahedronList.remove(tetrahedron);
    }

    public void updateTetrahedron(Tetrahedron tetrahedron) {
        tetrahedronList.removeIf(tetrahedron1 -> tetrahedron1.getId() == tetrahedron.getId());
        tetrahedronList.add(tetrahedron);
    }

    public List<Tetrahedron> query(Specification specification) {
        List<Tetrahedron> result = new ArrayList<>();
        for (Tetrahedron tetrahedron : tetrahedronList) {
            if (specification.specify(tetrahedron)) {
                result.add(tetrahedron);
            }
        }
        return result;
    }

    public List<Tetrahedron> sortTetrahedronById() {
        List<Tetrahedron> sortList = new ArrayList<>(tetrahedronList);
        sortList.sort(Comparator.comparingInt(Tetrahedron::getId));
        return sortList;
    }

    public List<Tetrahedron> sortTetrahedronByName() {
        List<Tetrahedron> sortList = new ArrayList<>(tetrahedronList);
        sortList.sort(Comparator.comparing(Tetrahedron::getName));
        return sortList;
    }

    public List<Tetrahedron> sortTetrahedronPointOneByX() {
        List<Tetrahedron> sortList = new ArrayList<>(tetrahedronList);
        sortList.sort(Comparator.comparing(tetrahedron -> tetrahedron.getPointFromList(0).getX()));
        return tetrahedronList;
    }

    public List<Tetrahedron> sortTetrahedronPointOneByY() {
        List<Tetrahedron> sortList = new ArrayList<>(tetrahedronList);
        sortList.sort(Comparator.comparing(tetrahedron -> tetrahedron.getPointFromList(0).getY()));
        return tetrahedronList;
    }

    public List<Tetrahedron> sortTetrahedronPointOneByZ() {
        List<Tetrahedron> sortList = new ArrayList<>(tetrahedronList);
        sortList.sort(Comparator.comparing(tetrahedron -> tetrahedron.getPointFromList(0).getZ()));
        return tetrahedronList;
    }

    public List<Tetrahedron> sortTetrahedronPointTwoByX() {
        List<Tetrahedron> sortList = new ArrayList<>(tetrahedronList);
        sortList.sort(Comparator.comparing(tetrahedron -> tetrahedron.getPointFromList(1).getX()));
        return tetrahedronList;
    }

    public List<Tetrahedron> sortTetrahedronPointTwoByY() {
        List<Tetrahedron> sortList = new ArrayList<>(tetrahedronList);
        sortList.sort(Comparator.comparing(tetrahedron -> tetrahedron.getPointFromList(1).getY()));
        return tetrahedronList;
    }

    public List<Tetrahedron> sortTetrahedronPointTwoByZ() {
        List<Tetrahedron> sortList = new ArrayList<>(tetrahedronList);
        sortList.sort(Comparator.comparing(tetrahedron -> tetrahedron.getPointFromList(1).getZ()));
        return tetrahedronList;
    }


    public Tetrahedron getTetrahedron(int index) {
        return tetrahedronList.get(index);
    }

    public List<Tetrahedron> getAllTetrahedrons() {
        return tetrahedronList;
    }

}
