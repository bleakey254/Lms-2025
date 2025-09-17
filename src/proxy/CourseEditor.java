// File: CourseEditor.java
package proxy;

public interface CourseEditor {
    void editCourse(int courseId, String newTitle);
    void deleteCourse(int courseId);
}