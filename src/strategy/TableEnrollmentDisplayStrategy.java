package strategy;

import command.EnrollmentCommand;
import command.EnrollmentReceiver;
import command.UnenrollCommand;
import model.Enrollment;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TableEnrollmentDisplayStrategy implements EnrollmentDisplayStrategy {

    @Override
    public JComponent createEnrollmentView(List<Enrollment> enrollments) {
        EnrollmentReceiver receiver = new EnrollmentReceiver();

        JTable table = new JTable(new EnrollmentTableModel(enrollments, receiver));
        table.setRowHeight(30);
        table.getColumn("Action").setCellRenderer(new ButtonRenderer());
        table.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox()));

        return new JScrollPane(table);
    }

    // Table Model with Unenroll Command
    static class EnrollmentTableModel extends AbstractTableModel {
        private final String[] columns = {"Enrollment ID", "Course ID", "Enrolled Date", "Action"};
        private final List<Enrollment> enrollments;
        private final EnrollmentReceiver receiver;

        public EnrollmentTableModel(List<Enrollment> enrollments, EnrollmentReceiver receiver) {
            this.enrollments = enrollments;
            this.receiver = receiver;
        }

        @Override
        public int getRowCount() {
            return enrollments.size();
        }

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Enrollment e = enrollments.get(rowIndex);
            switch (columnIndex) {
                case 0: return e.getId();
                case 1: return e.getCourseId();
                case 2: return e.getEnrolledDate();
                case 3: return new UnenrollCommand(receiver, e); // Command
                default: return null;
            }
        }

        @Override
        public String getColumnName(int col) {
            return columns[col];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 3;
        }
    }

    // Button Renderer
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setText("Unenroll");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            return this;
        }
    }

    // Button Editor
    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private EnrollmentCommand command;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton("Unenroll");
            button.addActionListener((ActionEvent e) -> {
                if (command != null) {
                    command.execute();
                }
                fireEditingStopped(); // Refresh table
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (value instanceof EnrollmentCommand) {
                this.command = (EnrollmentCommand) value;
            }
            return button;
        }
    }
}
