package command;

import javax.swing.*;
import java.awt.CardLayout;

public class HomeCommand implements Command {
    private final JPanel contentPanel;
    private final JPanel homePanel;

    public HomeCommand(JPanel contentPanel, JPanel homePanel) {
        this.contentPanel = contentPanel;
        this.homePanel = homePanel;
    }

    @Override
    public void execute() {
        CardLayout layout = (CardLayout) contentPanel.getLayout();
        layout.show(contentPanel, "Home");
    }
}
