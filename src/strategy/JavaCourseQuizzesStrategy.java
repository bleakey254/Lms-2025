package strategy;

public class JavaCourseQuizzesStrategy extends QuizDisplayStrategy {
    /**
     * Filters quizzes to only include those related to Java Programming.
     *
     * @param allQuizzes The complete list of quizzes.
     * @return A filtered array of quizzes related to Java Programming.
     */
    @Override
    protected Object[][] filterQuizzes(Object[][] allQuizzes) {
        return java.util.Arrays.stream(allQuizzes)
                .filter(row -> row[1].toString().equalsIgnoreCase("Java Programming"))
                .toArray(Object[][]::new);
    }
}
