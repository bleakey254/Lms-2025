package observer;

import javax.swing.DefaultListModel;

/**
 * Observer that appends notifications into a Swing list model.
 */
public class UINotificationObserver implements Observer {
    private final DefaultListModel<String> listModel;

    public UINotificationObserver(DefaultListModel<String> listModel) {
        this.listModel = listModel;
    }

    @Override
    public void update(Object eventData) {
        String msg = eventData.toString();
        // prepend at top
        listModel.add(0, msg);
    }
}

