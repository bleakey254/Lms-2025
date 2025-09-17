// src/command/DownloadMaterialCommand.java
package command;

import model.CourseMaterial;

public class DownloadMaterialCommand implements Command {

    private final CourseMaterialReceiver receiver;
    private final CourseMaterial material;

    public DownloadMaterialCommand(CourseMaterialReceiver receiver, CourseMaterial material) {
        this.receiver = receiver;
        this.material = material;
    }

    @Override
    public void execute() {
        receiver.downloadMaterial(material);
    }
}
