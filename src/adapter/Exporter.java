package adapter;

import javax.swing.JTable;

public interface Exporter {
    void export(JTable table, String filePath) throws Exception;
}

