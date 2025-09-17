package adapter;

import java.io.FileWriter;
import java.io.IOException;

public class PDFExportAdapter implements ExportAdapter {
    private String courseData;

    public PDFExportAdapter(String courseData) {
        this.courseData = courseData;
    }

    public PDFExportAdapter() {
        // Default constructor for compatibility
    }

    @Override
    public void export(String filePath) {
        // Simulate PDF export by writing plain text (replace with real PDF logic as needed)
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(courseData != null ? courseData : "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
