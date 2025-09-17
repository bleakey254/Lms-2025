package command;

public class CommandLogger {
    public static void log(String commandName) {
        System.out.println("[LOG] Executed command: " + commandName);
    }
}
