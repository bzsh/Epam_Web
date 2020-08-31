package by.epam.course.shape.specification;

import by.epam.course.shape.entity.Tetrahedron;

public class NameSpecification implements Specification {
    private String name;

    public NameSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean specify(Tetrahedron tetrahedron) {
        return this.name.equals(tetrahedron.getName());
    }
}
