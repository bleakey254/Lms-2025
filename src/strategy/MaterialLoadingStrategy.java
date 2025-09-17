package strategy;

import model.Material;
import java.util.List;

public abstract class MaterialLoadingStrategy {
    public abstract List<Material> loadMaterials(int courseId);
}

