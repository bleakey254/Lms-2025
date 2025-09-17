package command;

import model.Result;

import javax.swing.*;

public class ViewResultDetailsCommand implements Command {

    private final Result result;

    public ViewResultDetailsCommand(Result result) {
        this.result = result;
    }

    @Override
    public void execute() {
        StringBuilder details = new StringBuilder();
        details.append("📘 Result Details\n")
                .append("➤ Result ID: ").append(result.getId()).append("\n")
                .append("➤ User ID: ").append(result.getUserId()).append("\n")
                .append("➤ Quiz ID: ").append(result.getQuizId()).append("\n")
                .append("➤ Score: ").append(result.getScore()).append("\n")
                .append("➤ Date: ").append(result.getDate()).append("\n");

        JOptionPane.showMessageDialog(null, details.toString(), "Result Details", JOptionPane.INFORMATION_MESSAGE);

        CommandLogger.log("Viewed details for Result ID: " + result.getId());
    }
}
