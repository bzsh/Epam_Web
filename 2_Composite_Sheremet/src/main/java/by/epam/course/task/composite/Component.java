package by.epam.course.task.composite;

import java.util.List;

public interface Component {
    void add(Component component);

    CompositeType getCompositeType();

    List<Component> getComponents();
}
