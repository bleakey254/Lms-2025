package command;

import javax.swing.*;
import java.util.function.Supplier;

public class ViewResultsCommand implements Command {

    private final Supplier<JComponent> resultsSupplier;
    private final JPanel targetPanel;

    public ViewResultsCommand(Supplier<JComponent> resultsSupplier, JPanel targetPanel) {
        this.resultsSupplier = resultsSupplier;
        this.targetPanel = targetPanel;
    }

    @Override
    public void execute() {
        targetPanel.removeAll();
        targetPanel.add(resultsSupplier.get());
        targetPanel.revalidate();
        targetPanel.repaint();
        CommandLogger.log("Executed ViewResultsCommand");
    }
}
