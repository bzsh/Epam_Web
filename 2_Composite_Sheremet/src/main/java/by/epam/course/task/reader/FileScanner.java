package by.epam.course.task.reader;

import by.epam.course.task.exception.ReadDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileScanner {
    private static final Logger LOGGER = LogManager.getLogger(FileScanner.class);

    public String readData(String filePath) throws ReadDataException {
        StringBuilder builder = new StringBuilder();
        File file = new File(filePath);
        boolean result = isFileCorrect(file);
        if (!result) {
            Path path = Paths.get(filePath);
            try (Scanner scanner = new Scanner(path)) {
                while (scanner.hasNext()) {
                    builder.append(scanner.nextLine());
                    builder.append(scanner.hasNext() ? "\n" : "");
                }
                LOGGER.info("File:" + filePath + " was read");
            } catch (IOException e) {
                LOGGER.error("File:" + filePath + " Exception in readData method", e);
                throw new ReadDataException(e);
            }
        } else {
            LOGGER.error("File:" + filePath + "  is not exist or empty");
            throw new ReadDataException("File is not exist or empty");
        }
        return builder.toString();
    }

    private boolean isFileCorrect(File file) {
        return file == null
                || !file.exists()
                || file.isDirectory()
                || file.length() == 0;
    }
}
