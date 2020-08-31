package by.epam.course.shape.validator;

import java.io.File;

public class FileDataValidator {
    private static final String NORMAL_STRING = "\\s*((-?\\d+(.\\d+)?\\s+){2}(-?\\d+(.\\d+)?;\\s*)){4}";

    public boolean isDataCorrect(String string) {
        return string.matches(NORMAL_STRING);
    }

    public boolean isFileCorrect(File file) {
        return file == null
                || !file.exists()
                || file.isDirectory()
                || file.length() == 0;
    }
}
