// package: strategy
package strategy;

public class GradeLetterStrategy implements ResultDisplayStrategy {

    /**
     * Converts raw quiz results into a format with letter grades.
     *
     * @param rawResults The raw results from the quizzes, where each row contains
     *                   quiz data including a score in the third column.
     * @return A 2D array where the third column is replaced with letter grades.
     */
    @Override
    public Object[][] formatResults(Object[][] rawResults) {
        if (rawResults == null || rawResults.length == 0) {
            return new Object[0][];
        }

        // Create a new array to hold the graded results
        // The new array will have the same number of rows, but the third column
        // will be replaced with letter grades.
        // Assuming the third column is at index 2 and contains integer scores.
        Object[][] graded = new Object[rawResults.length][];
        for (int i = 0; i < rawResults.length; i++) {
            graded[i] = rawResults[i].clone();
            int score = Integer.parseInt(graded[i][2].toString());
            String grade;
            if (score >= 90) grade = "A";
            else if (score >= 80) grade = "B";
            else if (score >= 70) grade = "C";
            else if (score >= 60) grade = "D";
            else grade = "F";
            graded[i][2] = grade;
        }
        return graded;
    }
}
