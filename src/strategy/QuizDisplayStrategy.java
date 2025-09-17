package strategy;

public abstract class QuizDisplayStrategy {

    // Template Method: defines skeleton of filtering logic
    public final Object[][] processQuizData(Object[][] allQuizzes) {
        if (allQuizzes == null || allQuizzes.length == 0) {
            return new Object[0][];
        }

        preProcess(allQuizzes);
        return filterQuizzes(allQuizzes);
    }

    // Optional hook method
    protected void preProcess(Object[][] allQuizzes) {
        // Default no-op; subclasses may override for logging, etc.
    }

    // Abstract step to be implemented by subclasses
    protected abstract Object[][] filterQuizzes(Object[][] allQuizzes);
}
