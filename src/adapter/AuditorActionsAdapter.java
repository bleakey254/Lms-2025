// adapter/AuditorActionsAdapter.java
package adapter;

import command.GenericPanelCommand;

import javax.swing.*;
import java.awt.*;

public class AuditorActionsAdapter {

    private final JPanel contentPanel;
    private final CardLayout cardLayout;

    public AuditorActionsAdapter(JPanel contentPanel, CardLayout cardLayout) {
        this.contentPanel = contentPanel;
        this.cardLayout = cardLayout;
    }

    public GenericPanelCommand createPanelCommand(String panelName) {
        return new GenericPanelCommand(null, cardLayout, contentPanel, panelName);
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
