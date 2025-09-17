package adapter;

import model.CourseMaterial;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public interface CourseMaterialAdapter {
    void download(CourseMaterial material);
}

class PDFMaterialAdapter implements CourseMaterialAdapter {
    @Override
    public void download(CourseMaterial material) {
        try {
            File file = new File("downloads/" + material.getTitle().replaceAll(" ", "_") + ".pdf");
            file.getParentFile().mkdirs();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(material.getContent().getBytes());
            }
            System.out.println("Downloaded PDF: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class VideoMaterialAdapter implements CourseMaterialAdapter {
    @Override
    public void download(CourseMaterial material) {
        try {
            File file = new File("downloads/" + material.getTitle().replaceAll(" ", "_") + ".mp4");
            file.getParentFile().mkdirs();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(material.getContent().getBytes());
            }
            System.out.println("Downloaded Video: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class TextMaterialAdapter implements CourseMaterialAdapter {
    @Override
    public void download(CourseMaterial material) {
        try {
            File file = new File("downloads/" + material.getTitle().replaceAll(" ", "_") + ".txt");
            file.getParentFile().mkdirs();
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(material.getContent().getBytes());
            }
            System.out.println("Downloaded Text File: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
