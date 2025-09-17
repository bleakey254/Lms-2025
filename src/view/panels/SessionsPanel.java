package view.panels;

import model.Session;
import repository.MockSessionRepository;
import strategy.SessionFilterStrategy;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;

public class SessionsPanel extends JPanel {
    private final MockSessionRepository repository;
    private final SessionFilterStrategy filterStrategy;
    private final JTable sessionsTable;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public SessionsPanel(MockSessionRepository repository, SessionFilterStrategy filterStrategy) {
        this.repository = repository;
        this.filterStrategy = filterStrategy;

        setLayout(new BorderLayout());

        String[] columns = {"Session ID", "Title", "Date", "Status"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        sessionsTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(sessionsTable);
        add(scrollPane, BorderLayout.CENTER);

        setupToolbar();
        refreshSessions();
    }

    private void setupToolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshSessions());
        toolbar.add(refreshButton);

        add(toolbar, BorderLayout.NORTH);
    }

    private void refreshSessions() {
        DefaultTableModel model = (DefaultTableModel) sessionsTable.getModel();
        model.setRowCount(0);

        // Changed filterSessions to filter to match the interface
        for (Session session : filterStrategy.filter(repository.getAllSessions())) {
            String dateTimeStr = dateFormatter.format(session.getScheduledDateTime());
            model.addRow(new Object[]{
                session.getId(),
                session.getTitle(),
                dateTimeStr,
                session.isCompleted() ? "Completed" : "Upcoming"
            });
        }
    }

    public JTable getSessionsTable() {
        return sessionsTable;
    }
}
