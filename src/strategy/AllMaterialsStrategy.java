package strategy;

import model.Material;
import service.MaterialService;

import java.util.List;

/**
 * Loads all materials regardless of course.
 */
public class AllMaterialsStrategy extends MaterialLoadingStrategy {

    private final MaterialService materialService = new MaterialService();

    @Override
    public List<Material> loadMaterials(int courseId) {
        return materialService.getAllMaterials();
    }
}
