package by.epam.course.task.composite;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements Component {
    private List<Component> components = new ArrayList<>();
    private CompositeType compositeType;


    public TextComposite(CompositeType type) {
        this.compositeType = type;
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public CompositeType getCompositeType() {
        return compositeType;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (Component component : components) {
            if (CompositeType.PARAGRAPH.equals(component.getCompositeType())) {
                stringBuilder.append("   ");
                stringBuilder.append(component.toString());
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(component.toString());
            }
            if (CompositeType.LEXEME.equals(component.getCompositeType())) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
