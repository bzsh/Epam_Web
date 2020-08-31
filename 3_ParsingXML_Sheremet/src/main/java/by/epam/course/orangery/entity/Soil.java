package by.epam.course.orangery.entity;

import java.util.stream.Stream;

public enum Soil {
    PODZOL("podzol"),
    GROUND("ground"),
    SOD_PODZOLIC("sod-podzolic");

    private final String type;

    Soil(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Soil of(String type) {
        return Stream.of(Soil.values())
                .filter(s -> s.type.equalsIgnoreCase(type))
                .findFirst().orElse(PODZOL);
    }
}
