package adapter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportResultsPanel extends JPanel {
    private JTextArea resultArea;
    private JButton exportPdfButton;
    private JButton exportExcelButton;
    private JButton exportTxtButton;

    public ExportResultsPanel() {
        setLayout(new BorderLayout());

        resultArea = new JTextArea("Student Name: John Doe\nScore: 85\nStatus: Passed\n...");
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        exportPdfButton = new JButton("Export as PDF");
        exportExcelButton = new JButton("Export as Excel");
        exportTxtButton = new JButton("Export as TXT");

        buttonPanel.add(exportPdfButton);
        buttonPanel.add(exportExcelButton);
        buttonPanel.add(exportTxtButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Adapter usage
        ExportAdapter pdfAdapter = new PDFExportAdapter();
        ExportAdapter excelAdapter = new ExcelExportAdapter();
        ExportAdapter txtAdapter = new TxtExportAdapter();

        exportPdfButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pdfAdapter.export(resultArea.getText());
            }
        });

        exportExcelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                excelAdapter.export(resultArea.getText());
            }
        });

        exportTxtButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtAdapter.export(resultArea.getText());
            }
        });
    }
}
