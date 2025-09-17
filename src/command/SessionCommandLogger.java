// src/command/SessionCommandLogger.java
package command;

import java.time.LocalDateTime;

public class SessionCommandLogger {
    public static void log(SessionCommand command) {
        System.out.println("[LOG] " + command.getClass().getSimpleName() + " executed at " + LocalDateTime.now());
    }
}
