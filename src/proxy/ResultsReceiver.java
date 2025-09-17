package proxy;

import javax.swing.JTable;

public interface ResultsReceiver {
    JTable fetchResultsTable(int userId);
}
