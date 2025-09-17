package strategy;

import model.Session;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class UpcomingSessionsStrategy implements SessionFilterStrategy {
    @Override
    public List<Session> filter(List<Session> sessions) {
        if (sessions == null) {
            return new ArrayList<>();
        }

        List<Session> upcomingSessions = new ArrayList<>();
        Date now = new Date();

        for (Session session : sessions) {
            if (session != null &&
                session.getScheduledDateTime() != null &&
                session.getScheduledDateTime().after(now)) {
                upcomingSessions.add(session);
            }
        }

        return upcomingSessions;
    }
}
