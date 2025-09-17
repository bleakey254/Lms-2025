// EnrollmentDisplayStrategy.java
package strategy;

import javax.swing.*;
import java.util.List;
import model.Enrollment;

public interface EnrollmentDisplayStrategy {
    JComponent createEnrollmentView(List<Enrollment> enrollments);
}
