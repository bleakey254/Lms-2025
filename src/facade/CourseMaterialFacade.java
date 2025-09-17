package facade;

import strategy.MaterialLoadingStrategy;

import java.util.List;

public class CourseMaterialFacade {

    private final Object[][] allMaterials = {
            {"M101", "Java Basics", "Trainer A"},
            {"M102", "Design Patterns", "Trainer B"},
            {"M103", "Spring Boot", "Trainer A"}
    };

    public Object[][] getFilteredMaterials(MaterialLoadingStrategy strategy, int courseId) {
        // Use the strategy to load materials for the given courseId
        List<model.Material> materials = strategy.loadMaterials(courseId);
        Object[][] result = new Object[materials.size()][3];
        for (int i = 0; i < materials.size(); i++) {
            model.Material m = materials.get(i);
            result[i][0] = m.getId();
            result[i][1] = m.getTitle();
            result[i][2] = m.getType(); // Use type as the Author column
        }
        return result;
    }

    public String[] getMaterialColumnHeaders() {
        return new String[]{"Material ID", "Title", "Author"};
    }
}
