package command;

import model.Result;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportResultsCommand implements Command {

    private final List<Result> results;

    public ExportResultsCommand(List<Result> results) {
        this.results = results;
    }

    @Override
    public void execute() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Results As");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile())) {
                writer.write("ResultID,UserID,QuizID,Score,Date\n");
                for (Result r : results) {
                    writer.write(r.getId() + "," + r.getUserId() + "," + r.getQuizId() + "," + r.getScore() + "," + r.getDate() + "\n");
                }
                JOptionPane.showMessageDialog(null, "Results exported successfully!");
                CommandLogger.log("Exported " + results.size() + " results.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "❌ Error exporting results: " + e.getMessage());
                CommandLogger.log("Failed to export results: " + e.getMessage());
            }
        }
    }
}
