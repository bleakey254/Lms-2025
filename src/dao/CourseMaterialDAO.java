package dao;

import model.CourseMaterial;
import java.util.ArrayList;
import java.util.List;

public class CourseMaterialDAO {
    public List<CourseMaterial> getAllMaterials() {
        // Dummy implementation, replace with actual DB logic
        return new ArrayList<>();
    }

    public CourseMaterial getMaterialById(String id) {
        for (CourseMaterial material : getAllMaterials()) {
            if (String.valueOf(material.getId()).equals(id)) {
                return material;
            }
        }
        return null;
    }

    // Add more methods as needed (e.g., addMaterial, deleteMaterial, etc.)
}
