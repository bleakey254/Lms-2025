// src/command/CourseMaterialReceiver.java
package command;

import dao.CourseMaterialDAO;
import model.CourseMaterial;

import java.util.List;

public class CourseMaterialReceiver {

    private final CourseMaterialDAO materialDAO = new CourseMaterialDAO();

    public List<CourseMaterial> getAllMaterials() {
        return materialDAO.getAllMaterials();
    }

    public void downloadMaterial(CourseMaterial material) {
        System.out.println("📥 Downloading: " + material.getTitle());
        // Placeholder for real download logic
    }

    public CourseMaterial getMaterialById(String id) {
        return materialDAO.getMaterialById(id);
    }
}
