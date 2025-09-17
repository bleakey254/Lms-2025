package service;

import model.Session;
import java.util.ArrayList;
import java.util.List;

public class SessionService {
    private static SessionService instance;

    private SessionService() {}

    public static SessionService getInstance() {
        if (instance == null) {
            instance = new SessionService();
        }
        return instance;
    }

    public List<Session> getAllSessions() {
        // Dummy implementation, replace with actual DB logic
        return new ArrayList<>();
    }
}

