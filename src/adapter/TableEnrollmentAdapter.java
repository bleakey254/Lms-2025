// adapter/TableEnrollmentAdapter.java
package adapter;

import model.Enrollment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableEnrollmentAdapter implements EnrollmentViewAdapter {

    @Override
    public JComponent buildView(List<Enrollment> enrollments) {
        String[] columns = {"Enrollment ID", "Course ID", "Enrolled Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Enrollment e : enrollments) {
            model.addRow(new Object[]{e.getId(), e.getCourseId(), e.getEnrolledDate()});
        }

        JTable table = new JTable(model);
        return new JScrollPane(table);
    }
}
