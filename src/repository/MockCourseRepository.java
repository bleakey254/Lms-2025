package repository;

import model.Course;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockCourseRepository implements CourseRepository {
    private List<Course> courses;
    private int nextId;

    public MockCourseRepository() {
        courses = new ArrayList<>();
        nextId = 1;
        Date now = new Date();
        // Initialize mock data with categories
        courses.add(new Course(nextId++, 1, "Algebra", "Beginner", 99.99,
                "Intro to Algebra", "Mathematics", now, new Date(now.getTime() + 7776000000L), now));
        courses.add(new Course(nextId++, 1, "Calculus", "Advanced", 149.99,
                "Advanced Calculus", "Mathematics", now, new Date(now.getTime() + 7776000000L), now));
        courses.add(new Course(nextId++, 2, "Mechanics", "Intermediate", 129.99,
                "Classical Mechanics", "Physics", now, new Date(now.getTime() + 7776000000L), now));
        courses.add(new Course(nextId++, 2, "World History", "Beginner", 89.99,
                "Global Events", "History", now, new Date(now.getTime() + 7776000000L), now));
    }

    @Override
    public DefaultTableModel getCourseTableModel(int auditorId) {
        String[] columns = {"ID", "Name", "Description", "Level", "Cost"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Course course : courses) {
            model.addRow(new Object[]{
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getLevel(),
                course.getCost()
            });
        }
        return model;
    }

    @Override
    public void enrollCourse(int auditorId, int courseId) {
        // TODO: Implement proper enrollment logic
        // For now, we just find the course - actual enrollment should be handled by enrollment service/repository
        for (Course course : courses) {
            if (course.getId() == courseId) {
                break;
            }
        }
    }

    @Override
    public void addCourse(Course course) {
        Course newCourse = new Course(
            nextId++,
            course.getTrainerId(),
            course.getName(),
            course.getLevel(),
            course.getCost(),
            course.getDescription(),
            course.getCategory(),
            course.getStartDate(),
            course.getEndDate(),
            course.getCreatedAt()
        );
        courses.add(newCourse);
    }

    @Override
    public Map<String, List<Course>> getCoursesByCategory() {
        Map<String, List<Course>> coursesByCategory = new HashMap<>();

        for (Course course : courses) {
            String category = course.getCategory();
            coursesByCategory.computeIfAbsent(category, k -> new ArrayList<>()).add(course);
        }

        return coursesByCategory;
    }
}