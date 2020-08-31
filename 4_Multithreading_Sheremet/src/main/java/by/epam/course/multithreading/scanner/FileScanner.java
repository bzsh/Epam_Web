package by.epam.course.multithreading.scanner;

import by.epam.course.multithreading.exception.ReadDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileScanner {

    private static final Logger LOGGER = LogManager.getLogger(FileScanner.class);

    public List<String> readData(String filePath) throws ReadDataException {
        File file = new File(filePath);
        boolean result = isFileCorrect(file);
        List<String> lines = new ArrayList<>();
        if (!result) {
            Path path = Paths.get(filePath);

            try (Scanner scanner = new Scanner(path)) {
                while (scanner.hasNext())
                    lines.add(scanner.nextLine());
                LOGGER.info("File:" + filePath + " was read");
            } catch (IOException e) {
                LOGGER.warn("File:" + filePath + ". Exception in readData method ", e);
                throw new ReadDataException(e);
            }
        } else {
            LOGGER.warn("File:" + filePath + "  is not exist or empty");
            throw new ReadDataException("File is not exist or empty");
        }
        return lines;
    }

    private boolean isFileCorrect(File file) {
        return file == null
                || !file.exists()
                || file.isDirectory()
                || file.length() == 0;
    }
}