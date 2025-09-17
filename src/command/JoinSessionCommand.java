// src/command/JoinSessionCommand.java
package command;

import model.Session;

public class JoinSessionCommand implements SessionCommand {

    private final SessionReceiver receiver;
    private final Session session;

    public JoinSessionCommand(SessionReceiver receiver, Session session) {
        this.receiver = receiver;
        this.session = session;
    }

    @Override
    public void execute() {
        receiver.joinSession(session);
    }
}
