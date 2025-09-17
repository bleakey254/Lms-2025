package strategy;

import model.Session;
import java.util.List;
import java.util.ArrayList;

public class AuditorSessionFilterStrategy implements SessionFilterStrategy {
    @Override
    public List<Session> filter(List<Session> sessions) {
        // By default, auditors can see all active sessions
        // You can add specific filtering logic here if needed
        return new ArrayList<>(sessions);
    }
}
