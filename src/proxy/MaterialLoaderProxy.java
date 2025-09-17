package proxy;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MaterialLoaderProxy implements MaterialLoader {
    private static final Logger logger = Logger.getLogger(MaterialLoaderProxy.class.getName());
    private RealMaterialLoader realLoader;

    @Override
    public void loadMaterial(String materialName) {
        if (materialName == null || materialName.trim().isEmpty()) {
            logger.warning("Invalid material name requested.");
            return;
        }

        try {
            if (realLoader == null) {
                realLoader = new RealMaterialLoader(); // Lazy load
                logger.info("RealMaterialLoader initialized.");
            }
            logger.info("Loading material: " + materialName);
            realLoader.loadMaterial(materialName);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to load material: " + materialName, e);
        }
    }
}
