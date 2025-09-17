package adapter;

public class ExcelExportAdapter implements ExportAdapter {
    @Override
    public void export(String content) {
        System.out.println("[Excel] Exporting content:\n" + content);
        // Add real Excel export logic using Apache POI or similar
    }
}
