package by.epam.course.orangery.entity;

import java.util.Optional;
import java.util.stream.Stream;

public enum FlowerEnum {

    // FLOWERS("flowers"),
    FLOWER("flower"),
    ID("id"),
    SOIL("soil"),
    NAME("name"),
    DATE_LANDING("date-landing"),
    ORIGIN("origin"),
    MULTIPLYING("multiplying"),
    LEAF_COLOR("leaf-color"),
    STEM_COLOR("stem-color"),
    LENGTH("length"),
    TEMPERATURE("temperature"),
    LIGHTING("lighting"),
    WATERING("watering"),
    VISUAL("visual-parameters"),
    GROWING_TIPS("growing-tips");

    private final String field;

    FlowerEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public static Optional<FlowerEnum> of(String field) {
        return Stream.of(FlowerEnum.values()).filter(f ->  f.field.equalsIgnoreCase(field)).findFirst();
    }
}
