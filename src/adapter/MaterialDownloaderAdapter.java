package adapter;

import model.CourseMaterial;
import javax.swing.JOptionPane;

public class MaterialDownloaderAdapter implements MaterialDownloader {
    @Override
    public void download(CourseMaterial material) {
        // Simulate download logic
        JOptionPane.showMessageDialog(null, "Downloading: " + material.getTitle());
        // Add actual file download logic here if needed
    }
}

