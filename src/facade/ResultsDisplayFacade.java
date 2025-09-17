package facade;

import strategy.ResultDisplayStrategy;

public class ResultsDisplayFacade {

    private ResultDisplayStrategy strategy;

    public ResultsDisplayFacade(ResultDisplayStrategy strategy) {
        this.strategy = strategy;
    }

    public Object[][] getFormattedResults() {
        // In a real implementation, replace this with DB fetch logic
        Object[][] rawData = {
                {"Q101", "Java Programming", "85", "2023-10-05"},
                {"Q102", "Design Patterns", "75", "2023-10-12"}
        };
        return strategy.formatResults(rawData);
    }

    public String[] getColumnNames() {
        return new String[]{"Quiz ID", "Course", "Score", "Date"};
    }
}
