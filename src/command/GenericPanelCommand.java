package command;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class GenericPanelCommand implements Command {
    private final Object receiver;
    private final CardLayout cardLayout;
    private final JPanel contentPanel;
    private final String panelName;

    public GenericPanelCommand(Object receiver, CardLayout cardLayout, JPanel contentPanel, String panelName) {
        this.receiver = receiver;
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.panelName = panelName;
    }

    @Override
    public void execute() {
        cardLayout.show(contentPanel, panelName);
    }
}

