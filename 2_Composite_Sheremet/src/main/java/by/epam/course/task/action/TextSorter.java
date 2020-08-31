package by.epam.course.task.action;

import by.epam.course.task.composite.Component;
import by.epam.course.task.composite.CompositeType;
import by.epam.course.task.composite.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextSorter {
    private static final Logger LOGGER = LogManager.getLogger(TextSorter.class);

    public List<Component> sortParagraphsByNumberOfSentences(TextComposite textComposite) {
        List<Component> paragraphs = new ArrayList<>(textComposite.getComponents());
        paragraphs.sort(Comparator.comparingInt(o -> o.getComponents().size()));
        LOGGER.info("Text successfully sorted by sentence number.");
        return paragraphs;
    }

    public List<Component> sortSentencesByNumberOfWords(TextComposite textComposite) {
        List<Component> sentences = new ArrayList<>();
        for (Component paragraph : textComposite.getComponents()) {
            sentences.addAll(paragraph.getComponents());
        }
        sentences.sort(Comparator.comparingInt(s -> s.getComponents().size()));
        LOGGER.info("Sentences successfully sorted by number of words.");
        return sentences;
    }

    public List<Component> sortWordsByLength(TextComposite textComposite) {
        List<Component> words = new ArrayList<>();
        for (Component paragraph : textComposite.getComponents()) {
            for (Component sentence : paragraph.getComponents()) {
                for (Component lexeme : sentence.getComponents()) {
                    for (Component component : lexeme.getComponents()) {
                        if (CompositeType.WORD.equals(component.getCompositeType())) {
                            words.add(component);
                        }
                    }
                }
            }
        }
        words.sort(Comparator.comparingInt(w -> w.getComponents().size()));
        LOGGER.info("Sentences successfully sorted by words length.");
        return words;
    }

}
