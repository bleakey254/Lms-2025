package command;

public interface QuizCommand extends Command {
    // Inherit execute() from Command

    default void log(String commandName) {
        System.out.println("📝 Executed Command: " + commandName + " at " + java.time.LocalDateTime.now());
    }
}
