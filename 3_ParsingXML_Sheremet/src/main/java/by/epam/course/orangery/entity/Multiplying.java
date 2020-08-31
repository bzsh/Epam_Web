package by.epam.course.orangery.entity;

import java.util.stream.Stream;

public enum Multiplying {
    LEAVES("leaves"),
    CUTTING("cutting"),
    SEEDS("seeds");

    private final String type;

    Multiplying(String type) {
        this.type = type;
    }

    public static Multiplying of(String type) {
        return Stream.of(Multiplying.values())
                .filter(s ->  s.type.equalsIgnoreCase(type))
                .findFirst().orElse(CUTTING);
    }

    public String getType() {
        return type;
    }
}
