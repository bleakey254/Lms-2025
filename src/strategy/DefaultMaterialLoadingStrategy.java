package strategy;

import controller.MaterialController;
import model.Material;

import java.util.List;

public class DefaultMaterialLoadingStrategy extends MaterialLoadingStrategy {
    @Override
    public List<Material> loadMaterials(int courseId) {
        MaterialController controller = new MaterialController();
        return controller.getMaterialsByCourseId(courseId);
    }
}
