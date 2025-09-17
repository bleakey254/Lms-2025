// src/command/FilterSessionsCommand.java
package command;

import model.Session;
import strategy.SessionFilterStrategy;

import java.util.List;

public class FilterSessionsCommand implements SessionCommand {

    private final SessionReceiver receiver;
    private final SessionFilterStrategy strategy;

    public FilterSessionsCommand(SessionReceiver receiver, SessionFilterStrategy strategy) {
        this.receiver = receiver;
        this.strategy = strategy;
    }

    @Override
    public void execute() {
        List<Session> filtered = receiver.filterSessions(strategy);
        System.out.println("🔍 Filtered Sessions:");
        for (Session session : filtered) {
            System.out.println(" - " + session.getTitle());
        }
    }
}
