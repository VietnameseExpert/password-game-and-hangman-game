import java.util.Arrays;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        // store
        // Word to guess
        String wordToGuess = "banana";
        String[] urGuessList = new String[wordToGuess.length()];

        boolean gameNotFinished = true;
        boolean wordFound = false;
        String urGuess = "youstupid";

        int lives = 3;

        System.out.println(urGuessList);

        // word description
        String description = "This is what my monkey usually eat";


        while (gameNotFinished) {
            System.out.println("________________");
            System.out.print(description);

            // input

            System.out.print("\nInput a character: ");
            urGuess = sc.nextLine();
            while (urGuess.length() > 1) {
                System.out.print("Re-Input a character (should be a char): ");
                urGuess = sc.nextLine();
            }


            wordFound = false;
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (String.valueOf(wordToGuess.charAt(i)).equals(urGuess)) {
                    urGuessList[i] = urGuess;
                    wordFound = true;
                }
            }
            if (wordFound != true) {
                lives--;
                System.out.println("there is no letter " + urGuess + " in the word :((");
            } else {
                System.out.println("you guessed right :)), there is letter " + urGuess + " in the word :))");
            }

            gameNotFinished = false;
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (urGuessList[i] != null) {
                    System.out.print(urGuessList[i] + " ");
                } else {
                    System.out.print("_ ");
                    gameNotFinished = true;
                }
            }
            System.out.println();
            System.out.println("lives: " + lives);

            if (lives == 0) {
                gameNotFinished = false;
                System.out.println("you loose. Go and get a life, you Lbozo so stupid, ");
                System.out.println("spending so much time on this game and still manage to loose");
                System.out.println("haiyaa :(( so sadd");
            }
        }

        if (lives > 0) {
            System.out.println("you win, Congrats :))");
        }
    }

//  validation
//  lives
//  gui


    public void display () {

    }

    public void generate () {

    }

//    This is what my monkey usually eat
//    lives: 3
//    Input a character: n
//    _ _ n _ n _
//      n is the word

}
