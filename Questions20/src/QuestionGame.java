import java.io.PrintStream;
import java.util.Scanner;
public class QuestionGame {

    public class QuestionNode {
        private QuestionNode left;
        private String question;
        private QuestionNode right;

        public QuestionNode(QuestionNode left, String question, QuestionNode right) {
            this.left = left;
            this.question = question;
            this.right = right;
        }

        public QuestionNode(String question) {
            this(null, question, null);
        }
        public boolean isAnswer() {
            return left == null && right == null;
        }

    }

    QuestionNode root;

    public QuestionGame(String object) {
        root = new QuestionNode(object);
    }

    public QuestionGame(Scanner input) {
        root = QTreeBuilder(input);
    }

    public QuestionNode QTreeBuilder(Scanner console) {
        if(console.nextLine().equals("A:")) {
            return new QuestionNode(console.nextLine());
        } else {
            return new QuestionNode(QTreeBuilder(console),
                    console.nextLine(),
                    QTreeBuilder(console));
        }
    }
    public void saveQuestions(QuestionNode ref, PrintStream output) {
        if(ref.isAnswer()) {
            output.print("A:\n" + ref.question);
        } else {
            output.print("Q:\n" + ref.question);
            saveQuestions(ref.left, output);
            saveQuestions(ref.right, output);
        }
    }
    public void saveQuestions(PrintStream output) throws IllegalArgumentException {
        saveQuestions(root, output);
    }

    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
    public boolean yesTo(String prompt, Scanner console) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    public void play(Scanner console) {
        play(console, root);
    }

    public void play(Scanner console, QuestionNode ref) {
        while(!ref.isAnswer()) {
            boolean yesNo = yesTo(ref.question, console);
            if(yesNo) {
                ref = ref.left;
            } else {
                ref = ref.right;
            }
        }
        System.out.println("I guess that your object is " + ref.question + "!");
        boolean winner = yesTo("Am I right?", console);

        if(winner) {
            System.out.println("Awesome! I win!");
        } else {
            System.out.println("Boo! I Lose. Please help me get better!");
            System.out.println("What is your object?");
            String answer = console.nextLine().toLowerCase().trim();
            System.out.println("Please give me a yes/no question that distinguishes between "
                    + answer + " and " + ref.question + ".");
            System.out.println("Q: ");
            String question = console.nextLine().toLowerCase().trim();
            boolean child = yesTo("Is the answer \"yes\" for " + answer + "?", console);
            String placeholder = ref.question;
            ref.question = question;
            if(child) {
                ref.left = new QuestionNode(answer);
                ref.right = new QuestionNode(placeholder);
            } else {
                ref.left = new QuestionNode(placeholder);
                ref.right = new QuestionNode(answer);
            }
        }
    }

}
