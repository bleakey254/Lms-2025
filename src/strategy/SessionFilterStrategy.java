package strategy;

import model.Session;
import java.util.List;

public interface SessionFilterStrategy {
    List<Session> filter(List<Session> sessions);
}
