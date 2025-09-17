package proxy;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RealResultsReceiver implements ResultsReceiver {
    private static final Logger logger = Logger.getLogger(RealResultsReceiver.class.getName());

    @Override
    public JTable fetchResultsTable(int userId) {
        String sql = "SELECT * FROM results WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int cols = md.getColumnCount();
                String[] columnNames = new String[cols];
                for (int i = 0; i < cols; i++) columnNames[i] = md.getColumnLabel(i + 1);

                DefaultTableModel model = new DefaultTableModel(columnNames, 0);
                while (rs.next()) {
                    Object[] row = new Object[cols];
                    for (int i = 0; i < cols; i++) row[i] = rs.getObject(i + 1);
                    model.addRow(row);
                }
                return new JTable(model);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to load results for user: " + userId, e);
            DefaultTableModel errorModel = new DefaultTableModel();
            errorModel.addColumn("Error");
            errorModel.addRow(new Object[]{"Failed to load results"});
            return new JTable(errorModel);
        }
    }
}
