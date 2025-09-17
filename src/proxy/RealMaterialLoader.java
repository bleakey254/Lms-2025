// File: RealMaterialLoader.java
package proxy;

public class RealMaterialLoader implements MaterialLoader {
    @Override
    public void loadMaterial(String materialName) {
        System.out.println("[Real] Loading course material: " + materialName);
    }
}