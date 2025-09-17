package view;

import command.*;
import composite.CourseModule;
import composite.Lesson;
import decorator.ShadowDecorator;
import decorator.BorderDecorator;
import decorator.BasicPanel;
import decorator.PanelComponent;
import model.CourseMaterial;
import proxy.MaterialLoader;
import proxy.MaterialLoaderProxy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CourseMaterialsPanel extends JPanel {

    private final JTable materialsTable;
    private final DefaultTableModel tableModel;
    private final CourseMaterialReceiver receiver = new CourseMaterialReceiver();
    private final MaterialLoader loader = new MaterialLoaderProxy();

    public CourseMaterialsPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(250, 250, 250));

        // Header
        JLabel header = new JLabel("📚 Course Materials", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 22));
        header.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(header, BorderLayout.NORTH);

        // Content: table + buttons
        JPanel rawContentPanel = new JPanel(new BorderLayout());

        // Table setup
        String[] columns = {"ID", "Title", "Description"};
        tableModel = new DefaultTableModel(columns, 0);
        materialsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(materialsTable);
        rawContentPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("🔁 Refresh");
        JButton downloadButton = new JButton("⬇️ Download Selected");

        refreshButton.addActionListener(e -> loadMaterials());
        downloadButton.addActionListener(e -> downloadSelectedMaterial());

        buttonPanel.add(refreshButton);
        buttonPanel.add(downloadButton);
        rawContentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Apply Decorators
        PanelComponent base = new BasicPanel(rawContentPanel);
        PanelComponent decorated = new ShadowDecorator(new BorderDecorator(base));
        add(decorated.getPanel(), BorderLayout.CENTER);

        // Initial load
        loadMaterials();
    }

    private void loadMaterials() {
        Command viewCommand = new ViewMaterialsCommand(receiver);
        viewCommand.execute();

        tableModel.setRowCount(0);
        List<CourseMaterial> materials = receiver.getAllMaterials();

        // Use Composite structure
        CourseModule root = new CourseModule("All Materials");

        for (CourseMaterial mat : materials) {
            Lesson lesson = new Lesson(String.valueOf(mat.getId()), mat.getTitle(), mat.getDescription());
            root.add(lesson);
        }

        root.display(materialsTable);
    }

    private void downloadSelectedMaterial() {
        int selectedRow = materialsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "❗ Please select a material to download.");
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();
        CourseMaterial material = receiver.getMaterialById(id);

        if (material != null) {
            loader.loadMaterial(material.getTitle());
            JOptionPane.showMessageDialog(this, "✅ " + material.getTitle() + " downloaded successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Material not found.");
        }
    }
}
