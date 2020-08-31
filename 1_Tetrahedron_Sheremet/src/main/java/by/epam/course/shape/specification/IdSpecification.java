package by.epam.course.shape.specification;

import by.epam.course.shape.entity.Tetrahedron;

public class IdSpecification implements Specification {
    private int id;

    public IdSpecification(int id) {
        this.id = id;
    }

    @Override
    public boolean specify(Tetrahedron tetrahedron) {
        return tetrahedron.getId() == id;
    }
}
