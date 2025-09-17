package strategy;

import model.Session;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class PastSessionsStrategy implements SessionFilterStrategy {
    @Override
    public List<Session> filter(List<Session> sessions) {
        List<Session> pastSessions = new ArrayList<>();
        Date now = new Date();

        for (Session session : sessions) {
            if (session.getScheduledDateTime() != null &&
                session.getScheduledDateTime().before(now)) {
                pastSessions.add(session);
            }
        }

        return pastSessions;
    }
}
