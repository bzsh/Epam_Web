package by.epam.course.shape.util;

public class IdCreator {
    private static int idCount;

    public static int createId() {
        return ++idCount;
    }
}
