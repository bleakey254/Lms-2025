package model;

import utils.DatabaseConnection;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ResultsModel {
    /**
     * Builds a DefaultTableModel from the results table for the given userId.
     * It queries all columns and constructs the table model dynamically.
     */
    public DefaultTableModel getUserResultsTableModel(int userId) throws SQLException {
        String sql = "SELECT * FROM results WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int cols = md.getColumnCount();
                String[] columnNames = new String[cols];
                for (int i = 0; i < cols; i++) {
                    columnNames[i] = md.getColumnLabel(i + 1);
                }

                DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                while (rs.next()) {
                    Object[] row = new Object[cols];
                    for (int i = 0; i < cols; i++) {
                        row[i] = rs.getObject(i + 1);
                    }
                    model.addRow(row);
                }
                return model;
            }
        }
    }
}
