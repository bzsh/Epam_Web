package by.epam.course.orangery.parser;

import by.epam.course.orangery.entity.Flower;
import by.epam.course.orangery.exception.ParsingException;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseParser {
    public List<Flower> flowers = new ArrayList<>();

    public abstract void buildFlowers(String path) throws ParsingException;

    public List<Flower> getFlowers() {
        return flowers;
    }
}
