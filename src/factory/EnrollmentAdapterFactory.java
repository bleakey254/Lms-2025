package factory;

import adapter.CourseEnrollmentAdapter;
import adapter.DefaultCourseEnrollmentAdapter;

public class EnrollmentAdapterFactory {
    public static CourseEnrollmentAdapter createAdapter(int auditorId) {
        return new DefaultCourseEnrollmentAdapter(auditorId);
    }
}
