package adapter;

public class TxtExportAdapter implements ExportAdapter {
    @Override
    public void export(String content) {
        System.out.println("[TXT] Exporting content:\n" + content);
        // Implement writing content to a .txt file
    }
}
