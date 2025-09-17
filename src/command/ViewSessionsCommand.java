// src/command/ViewSessionsCommand.java
package command;

import model.Session;

import java.util.List;

public class ViewSessionsCommand implements SessionCommand {

    private final SessionReceiver receiver;

    public ViewSessionsCommand(SessionReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        List<Session> sessions = receiver.getAllSessions();
        System.out.println("📋 All Sessions:");
        for (Session s : sessions) {
            System.out.println(" - " + s.getTitle());
        }
    }
}
