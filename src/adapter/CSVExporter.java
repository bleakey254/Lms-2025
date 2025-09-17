package adapter;

import javax.swing.JTable;
import java.io.FileWriter;

public class CSVExporter implements Exporter {
    @Override
    public void export(JTable table, String filePath) throws Exception {
        FileWriter csv = new FileWriter(filePath);
        int columnCount = table.getColumnCount();
        // Write header
        for (int i = 0; i < columnCount; i++) {
            csv.write(table.getColumnName(i));
            if (i < columnCount - 1) csv.write(",");
        }
        csv.write("\n");
        // Write rows
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < columnCount; j++) {
                Object value = table.getValueAt(i, j);
                csv.write(value != null ? value.toString() : "");
                if (j < columnCount - 1) csv.write(",");
            }
            csv.write("\n");
        }
        csv.close();
    }
}

