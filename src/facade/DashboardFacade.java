package facade;

import model.Course;
import repository.CourseRepository;
import repository.QuizRepository;
import repository.PaymentRepository;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public class DashboardFacade {
    private CourseRepository courseRepository;
    private QuizRepository quizRepository;
    private PaymentRepository paymentRepository;

    public DashboardFacade(CourseRepository courseRepository, QuizRepository quizRepository, PaymentRepository paymentRepository) {
        this.courseRepository = courseRepository;
        this.quizRepository = quizRepository;
        this.paymentRepository = paymentRepository;
    }

    public DefaultTableModel getCourseTableModel(int auditorId) {
        return courseRepository.getCourseTableModel(auditorId);
    }

    public DefaultTableModel getQuizTableModel() {
        return quizRepository.getQuizTableModel();
    }

    public DefaultTableModel getPaymentTableModel(int auditorId) {
        return paymentRepository.getPaymentTableModel(auditorId);
    }

    public void enrollCourse(int auditorId, int courseId) {
        courseRepository.enrollCourse(auditorId, courseId);
    }

    public void addCourse(Course course) {
        courseRepository.addCourse(course);
    }

    public Map<String, List<Course>> getCoursesByCategory() {
        return courseRepository.getCoursesByCategory();
    }
}