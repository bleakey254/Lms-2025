// src/command/ViewMaterialsCommand.java
package command;

import model.CourseMaterial;

import java.util.List;

public class ViewMaterialsCommand implements Command {
    private final CourseMaterialReceiver receiver;

    public ViewMaterialsCommand(CourseMaterialReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        List<CourseMaterial> materials = receiver.getAllMaterials();
        System.out.println("📚 All Materials:");
        for (CourseMaterial m : materials) {
            System.out.println(" - " + m.getTitle());
        }
    }
}
