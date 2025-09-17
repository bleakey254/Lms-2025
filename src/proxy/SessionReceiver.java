package proxy;

import model.Session;
import strategy.SessionFilterStrategy;
import java.util.List;

public interface SessionReceiver {
    List<Session> filter(SessionFilterStrategy strategy);
    Session getSessionById(String sessionId);
}
