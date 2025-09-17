package strategy;

public class AllQuizzesStrategy extends QuizDisplayStrategy {
    @Override
    protected Object[][] filterQuizzes(Object[][] allQuizzes) {
        return allQuizzes;
    }
}
