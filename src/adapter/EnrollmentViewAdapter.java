// adapter/EnrollmentViewAdapter.java
package adapter;

import model.Enrollment;

import javax.swing.*;
import java.util.List;

public interface EnrollmentViewAdapter {
    JComponent buildView(List<Enrollment> enrollments);
}
