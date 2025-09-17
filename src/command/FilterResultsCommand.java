package command;

import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collectors;

public class FilterResultsCommand<T> implements Command {

    private final List<T> originalResults;
    private final Predicate<T> filterPredicate;
    private List<T> filteredResults;

    public FilterResultsCommand(List<T> originalResults, Predicate<T> filterPredicate) {
        this.originalResults = originalResults;
        this.filterPredicate = filterPredicate;
    }

    @Override
    public void execute() {
        filteredResults = originalResults.stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());
        CommandLogger.log("Executed FilterResultsCommand: " + filteredResults.size() + " results found.");
    }

    public List<T> getFilteredResults() {
        return filteredResults;
    }
}

