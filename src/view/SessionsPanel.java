package view;

import repository.MockSessionRepository;
import strategy.SessionFilterStrategy;
import javax.swing.*;
import java.awt.*;

public class SessionsPanel extends JPanel {
    private final MockSessionRepository repository;
    private final SessionFilterStrategy filterStrategy;
    private final JTable sessionsTable;

    public SessionsPanel(MockSessionRepository repository, SessionFilterStrategy filterStrategy) {
        this.repository = repository;
        this.filterStrategy = filterStrategy;

        setLayout(new BorderLayout());

        // Initialize sessions table
        String[] columns = {"Session ID", "Title", "Date", "Time", "Status"};
        sessionsTable = new JTable(new Object[0][5], columns);

        JScrollPane scrollPane = new JScrollPane(sessionsTable);
        add(scrollPane, BorderLayout.CENTER);

        // Add toolbar for actions
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        add(toolbar, BorderLayout.NORTH);

        refreshSessions();
    }

    private void refreshSessions() {
        // Implement session refresh logic using repository and filter strategy
    }
}
