// File: RealActionPerformer.java
package proxy;

public class RealActionPerformer implements ActionPerformer {
    @Override
    public void performAction(String action) {
        System.out.println("[Real] Performing action: " + action);
    }
}