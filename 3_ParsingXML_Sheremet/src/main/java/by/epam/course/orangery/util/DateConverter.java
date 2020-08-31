package by.epam.course.orangery.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    public static LocalDate convertDate(String text) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
        return LocalDate.parse(text, inputFormat);
    }
}

