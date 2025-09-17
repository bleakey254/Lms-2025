// command/CommandInvoker.java
package command;

public class CommandInvoker {

    public void executeCommand(EnrollmentCommand command) {
        command.execute();
    }
}

