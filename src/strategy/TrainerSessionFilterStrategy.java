package strategy;

import model.Session;
import java.util.List;
import java.util.stream.Collectors;

public class TrainerSessionFilterStrategy implements SessionFilterStrategy {
    @Override
    public List<Session> filter(List<Session> sessions) {
        // Only return sessions that are not completed
        return sessions.stream()
                      .filter(session -> !session.isCompleted())
                      .collect(Collectors.toList());
    }
}
