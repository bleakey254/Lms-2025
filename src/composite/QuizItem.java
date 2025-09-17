package composite;

import javax.swing.*;
import java.awt.*;

public class QuizItem implements QuizComponent {
    private String title;

    public QuizItem(String title) {
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void display(JPanel panel) {
        JButton quizButton = new JButton(title);
        quizButton.setPreferredSize(new Dimension(200, 40));
        panel.add(quizButton);
    }
}
