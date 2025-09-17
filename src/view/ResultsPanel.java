package view;

import adapter.ResultAdapter;
import adapter.ResultAdapterImpl;
import command.CommandLogger;
import model.Result;
import model.User;
import session.SessionManager;
import decorator.BasicPanel;
import decorator.PanelComponent;
import decorator.BorderDecorator;
import decorator.ShadowDecorator;
import proxy.ResultsAccessProxy;
import proxy.ResultsReceiver;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ResultsPanel extends JPanel {

    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private final ResultAdapter resultAdapter;
    private final ResultsReceiver resultsProxy;

    public ResultsPanel() {
        this.resultAdapter = new ResultAdapterImpl();
        this.resultsProxy = new ResultsAccessProxy();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("📊 Quiz Results", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        initResultsTable();
        loadResults();

        JScrollPane scrollPane = new JScrollPane(resultsTable);

        // Wrap table inside decorated panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        PanelComponent base = new BasicPanel(contentPanel);
        PanelComponent decorated = new ShadowDecorator(new BorderDecorator(base));
        add(decorated.getPanel(), BorderLayout.CENTER);

        CommandLogger.log("ResultsPanel loaded");
    }

    private void initResultsTable() {
        String[] columnNames = {"Quiz Title", "Course", "Score", "Max Score", "Date Taken"};
        tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        resultsTable.setRowHeight(25);
        resultsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    private void loadResults() {
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "No user logged in!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTable proxyTable = resultsProxy.fetchResultsTable(currentUser.getId());
        tableModel.setRowCount(0);

        for (int i = 0; i < proxyTable.getRowCount(); i++) {
            Object[] row = new Object[proxyTable.getColumnCount()];
            for (int j = 0; j < proxyTable.getColumnCount(); j++) {
                row[j] = proxyTable.getValueAt(i, j);
            }
            tableModel.addRow(row);
        }
    }
}
