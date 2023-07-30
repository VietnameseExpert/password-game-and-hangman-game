import java.util.ArrayList;
import java.util.HashMap;
import java.util. Scanner;

public class June16MyHangman {
    public static void main(String[] args) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> word = new HashMap<>();
        word.put("wordToGuess","banana");
        word.put("description","This is what my monkey usually eat");
        list.add(word);
        word = new HashMap<>(); // create a new one!
        word.put("wordToGuess","apple");
        word.put("description", "The name of a phone company");
        list.add(word);
        word = new HashMap<>();
        word.put("wordToGuess","morning");
        word.put("description", "Kia ...");
        list.add(word);
        System.out.println(list);

        while (list.size()>0) {
            Scanner input = new Scanner(System.in);
//            int random_int = (int) Math.random()*list.size();
//            System.out.println(list.get(random_int));
//            String wordToGuess = list.get(random_int);
//            String wordToGuess = "banana";
            int index = (int) (Math.random()*list.size());
            HashMap<String, String> randomMap = (HashMap<String, String>) list.get(index);

            String wordToGuess = randomMap.get("wordToGuess");
            String description = randomMap.get("description");
            String urGuess = "";

            String memory = Generate(wordToGuess);
            String changes = "";
            String temp = "";

            int lives = 3;
            boolean wordFound = false;
            boolean playing = true;

            // looping through every word in the wordToGuess (the correct word) to see if it matches ur guess, and append it
            // into the changes; if urGuess is found at index i then append that word, else append _ + ""

            while (playing) {
                playing = false;
                wordFound = false;

                System.out.println(description);
                System.out.print("Input a character: ");
                urGuess = input.nextLine();
                while (urGuess.length() > 1) {
                    System.out.println(description);
                    System.out.print("Re-Input a character (should be a char): ");
                    urGuess = input.nextLine();
                }

                for (int i = 0; i < wordToGuess.length(); i++) {
                    if (String.valueOf(wordToGuess.charAt(i)).equals(urGuess)) {
                        wordFound = true;
                        changes += urGuess + " ";
                    } else {
                        changes += "_ ";
                    }
                }

                for (int i = 0; i < memory.length(); i++) {
                    if (String.valueOf(memory.charAt(i)).equals("_")) {
                        temp += changes.charAt(i);
                    } else {
                        temp += memory.charAt(i);
                    }
                }

                memory = temp;
                temp = "";
                changes = "";
                System.out.println(memory);

                for (int i = 0; i < memory.length(); i++) {
                    if (String.valueOf(memory.charAt(i)).equals("_")) {
                        playing = true;
                    }
                }

                if (wordFound == true) {
                    System.out.println("you guessed right");
                    ;
                } else {
                    System.out.println("you guessed wrong");
                    lives--;
                }

                if (lives == 0) {
                    System.out.println("you loose");
                    playing = false;
                }

                System.out.println();
                printLives(lives);
            }
            if (lives > 0) {
                System.out.println("you win, Congrats :))");
                list.remove(index);
            }
        }
    }

    public static String Generate (String wordToGuess) {
        String mem = "";
        for (int i = 0; i < wordToGuess.length(); i++) {
            mem += "_ ";
        }
        return mem;
    }

    public static void printLives(int lives) {
        switch (lives) {
            case 3:
                System.out.println("""
                        |===========
                        |          
                        |          
                        |          
                        |
                        |
                        |
                        |===============
                        """);
                break;
            case 2:
                System.out.println("""
                        |============
                        |          |
                        |          |
                        |          O  
                        |          
                        |         
                        |          
                        |         
                        |        
                        |===============
                        """);
                break;
            case 1:
                System.out.println("""
                        |============
                        |          |
                        |          |
                        |          O   
                        |          |
                        |         / \\
                        |          
                        |         
                        |        
                        |===============
                        """);
                break;
            case 0:
                System.out.println("""
                        |============
                        |          |
                        |          |
                        |          O   
                        |          |
                        |         /|\\
                        |          |
                        |         / \\
                        |        /   \\
                        |===============
                        """);
                break;
        }

    }
}

// UI
// wordToGuess: banana

// If urChoice in wordToGuess

// description
// lives: ❤️❤️❤️
// Input character (input a character): b
// b _ _ _ _ _
// you guessed correct

// If urChoice is not in wordToGuess
// description
// lives: ❤️❤️❤️
// Input character (input a character): p
// b _ _ _ _ _
// you guessed incorrect
