package composite;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class QuizGroup implements QuizComponent {
    private String title;
    private List<QuizComponent> components = new ArrayList<>();

    public QuizGroup(String title) {
        this.title = title;
    }

    public void add(QuizComponent component) {
        components.add(component);
    }

    public void remove(QuizComponent component) {
        components.remove(component);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void display(JPanel panel) {
        JLabel groupLabel = new JLabel("Category: " + title);
        panel.add(groupLabel);

        for (QuizComponent component : components) {
            component.display(panel);
        }
    }
}
