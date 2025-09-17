package adapter;

import java.io.FileWriter;
import java.io.IOException;

public class CSVExportAdapter implements ExportAdapter {
    private String courseData;

    public CSVExportAdapter(String courseData) {
        this.courseData = courseData;
    }

    @Override
    public void export(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(courseData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
