import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizWithTimer {
    
    private static final int TIME_LIMIT = 10; // time limit in seconds for each question
    private static boolean answered = false;
    private static Timer timer;

    public static void main(String[] args) {
        String[] questions = {
            "1. What is the capital of France?",
            "2. Who developed Java programming language?",s
            "3. What is the square root of 64?"
        };

        String[][] options = {
            {"a) Paris", "b) Rome", "c) Berlin", "d) Madrid"},
            {"a) James Gosling", "b) Guido van Rossum", "c) Dennis Ritchie", "d) Bjarne Stroustrup"},
            {"a) 6", "b) 7", "c) 8", "d) 9"}
        };

        String[] answers = {"a", "a", "c"};
        int score = 0;
        
        Scanner scanner = new Scanner(System.in);

        // Loop through questions
        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }

            answered = false;
            timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    if (!answered) {
                        System.out.println("\nTime's up!");
                        System.out.println("Moving to next question...\n");
                        timer.cancel();
                    }
                }
            }, TIME_LIMIT * 1000);  // Timer set for each question

            String userAnswer = getUserAnswer(scanner);
            if (answered && userAnswer.equalsIgnoreCase(answers[i])) {
                score++;
                System.out.println("Correct!\n");
            } else if (answered) {
                System.out.println("Wrong answer.\n");
            }
        }

        // Final score
        System.out.println("Quiz finished! Your score is: " + score + "/" + questions.length);
        scanner.close();
    }

    // Method to get the user's answer and check time limit
    private static String getUserAnswer(Scanner scanner) {
        String input = null;
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < TIME_LIMIT * 1000 && !answered) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
                answered = true;
                timer.cancel();  // Stop the timer if the user answers on time
            }
        }
        return input;
}
}
