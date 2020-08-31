package by.epam.course.multithreading.util;

public class IdCreator {
    private static int idCount;

    public static int createId() {
        return ++idCount;
    }
}
