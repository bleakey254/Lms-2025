package command;

import model.Result;

import javax.swing.*;
import java.util.List;

public class PrintResultSummaryCommand implements Command {

    private final List<Result> results;

    public PrintResultSummaryCommand(List<Result> results) {
        this.results = results;
    }

    @Override
    public void execute() {
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No results to summarize.");
            CommandLogger.log("Attempted to print summary with no results.");
            return;
        }

        StringBuilder summary = new StringBuilder("Quiz Results Summary:\n\n");
        for (Result r : results) {
            summary.append("Result ID: ").append(r.getId())
                    .append(" | Quiz ID: ").append(r.getQuizId())
                    .append(" | Score: ").append(r.getScore())
                    .append(" | Date: ").append(r.getDate())
                    .append("\n");
        }

        JTextArea textArea = new JTextArea(summary.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(600, 300));

        JOptionPane.showMessageDialog(null, scrollPane, "Results Summary", JOptionPane.INFORMATION_MESSAGE);
        CommandLogger.log("Printed results summary for " + results.size() + " entries.");
    }
}
