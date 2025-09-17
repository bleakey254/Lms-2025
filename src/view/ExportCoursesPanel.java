package view;

import adapter.CSVExportAdapter;
import adapter.PDFExportAdapter;
import adapter.ExportAdapter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportCoursesPanel extends JPanel {
    private JTextArea courseTextArea;
    private JButton exportCSVButton;
    private JButton exportPDFButton;

    public ExportCoursesPanel() {
        courseTextArea = new JTextArea(10, 30);
        exportCSVButton = new JButton("Export as CSV");
        exportPDFButton = new JButton("Export as PDF");

        add(new JScrollPane(courseTextArea));
        add(exportCSVButton);
        add(exportPDFButton);

        exportCSVButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportData("csv");
            }
        });

        exportPDFButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportData("pdf");
            }
        });
    }

    private void exportData(String format) {
        String data = courseTextArea.getText();
        ExportAdapter adapter;

        if ("csv".equalsIgnoreCase(format)) {
            adapter = new CSVExportAdapter(data);
        } else {
            adapter = new PDFExportAdapter(data);
        }

        try {
            adapter.export("exported_courses." + format);
            JOptionPane.showMessageDialog(this, "Exported successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Export failed: " + ex.getMessage());
        }
    }
}