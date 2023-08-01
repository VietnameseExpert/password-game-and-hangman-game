import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class July31st_passwordGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String urPassword;

        // [{rule 1: T/F, unlocked: T/F}]
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        HashMap<String, String> word = new HashMap<>();
        word.put("Status", "pending"); // "unlocked"
        word.put("Description", "Your password must be at least 5 characters. ");
        word.put("Correct", "false");
        list.add(word);

        word = new HashMap<>(); // create a new one!
        word.put("Status", "locked");
        word.put("Description", "Your password must include a number.");
        word.put("Correct", "false");
        list.add(word);

        word = new HashMap<>();
        word.put("Status", "locked");
        word.put("Description", "Your password must include an uppercase letter.");
        word.put("Correct", "false");
        list.add(word);

        word = new HashMap<>();
        word.put("Status", "locked");
        word.put("Description", "Your password must include a special character.");
        word.put("Correct", "false");
        list.add(word);

        word = new HashMap<>();
        word.put("Status", "locked");
        word.put("Description", "The digits in your password must add up to 25.");
        word.put("Correct", "false");
        list.add(word);

        word = new HashMap<>();
        word.put("Status", "locked");
        word.put("Description", "Your password must include a month of the year.");
        word.put("Correct", "false");
        list.add(word);

        word = new HashMap<>();
        word.put("Status", "locked");
        word.put("Description", "Your password must include a roman numeral.");
        word.put("Correct", "false");
        list.add(word);

        boolean win = false;
        while (!win) {
            System.out.println("Enter password: ");
            urPassword = input.nextLine();

            Boolean[] urGuessList = new Boolean[list.size()];
            urGuessList[0] = rule_01(urPassword);
            urGuessList[1] = rule_02(urPassword);
            urGuessList[2] = rule_03(urPassword);
            urGuessList[3] = rule_04(urPassword);
            urGuessList[4] = rule_05(urPassword);
            urGuessList[5] = rule_06(urPassword);
            urGuessList[6] = rule_07(urPassword);

            win = true;
            for (boolean x : urGuessList) {
                if (!x) {
                    win = false;
                    break;
                }
            }
            listBuilder(list, urGuessList);
            display(list);
        }
    }

    public static boolean rule_01(String Password) {
        Password.replace(" ", "");
        return Password.length() >= 5;
    }
    public static boolean rule_02(String Password) {
        char[] passChars = Password.toCharArray();
        for (char character : passChars) {
            try {
                int num = Integer.parseInt(String.valueOf(character));
                return true;
            } catch (NumberFormatException nfe) {
                skip();
            }
        }
        return false;
    }
    public static boolean rule_03(String Password) {
        char[] passChars = Password.toCharArray();
        for (char character : passChars) {
            if (Character.isUpperCase(character)) {
                return true;
            }
        }
        return false;
    }
    public static boolean rule_04(String Password) {
        return Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE).matcher(Password).find();
    }
    public static boolean rule_05(String Password) {
        int sum = 0;
        char[] passChars = Password.toCharArray();
        for (char character : passChars) {
            try {
                int num = Integer.parseInt(String.valueOf(character));
                sum += num;
            } catch (NumberFormatException nfe) {
                skip();
            }
        }
        return sum == 25;
    }

    public static boolean rule_06(String Password) {

        String lowerCased = Password.toLowerCase();
        ArrayList<String> year = new ArrayList<>();
        year.add("january");
        year.add("february");
        year.add("march");
        year.add("april");
        year.add("may");
        year.add("june");
        year.add("july");
        year.add("august");
        year.add("september");
        year.add("october");
        year.add("november");
        year.add("december");

        //O index = 3
        // 3+7 = 10
        // asdOctober
//----------------------------------------
//        for (int i = 0; i < lowerCased.length(); i++) { // char character : Password.toCharArray()
//
//            for (String month : year) {
//
//                if (lowerCased.charAt(i) == month.charAt(0)) {
//                    char[] comparedMonth = new char[month.length()];
//                    int index = i;
//                    int put = 0;
//                    while (index < i+month.length()) {
//                        comparedMonth[put] = lowerCased.charAt(index);
//
//                        put++;
//                        index++;
//
//                        if (index == lowerCased.length()) {
//                            break;
//                        }
//                    }
//                    // test
////                    for (char Character : comparedMonth) {
////                        System.out.print(Character + ", ");
////                    }
////                    System.out.println("");
//                    boolean same = true;
//                    for (int x = 0; x < month.length(); x++) {
//                        if (comparedMonth[x] != month.charAt(x)) {
//                            same = false;
//                            break;
//                        }
//                    }
//                    if (same) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
        return wordInPass(lowerCased, year);

    }

    public static boolean rule_07(String Password) {
        ArrayList<String> romanNumeral = new ArrayList<>();
        romanNumeral.add("I");
        romanNumeral.add("V");
        romanNumeral.add("X");
        romanNumeral.add("L");
        romanNumeral.add("C");
        romanNumeral.add("D");
        romanNumeral.add("M");

        //O index = 3
        // 3+7 = 10
        // asdOctober
//----------------------------------------
//        for (int i = 0; i < lowerCased.length(); i++) { // char character : Password.toCharArray()
//
//            for (String month : year) {
//
//                if (lowerCased.charAt(i) == month.charAt(0)) {
//                    char[] comparedMonth = new char[month.length()];
//                    int index = i;
//                    int put = 0;
//                    while (index < i+month.length()) {
//                        comparedMonth[put] = lowerCased.charAt(index);
//
//                        put++;
//                        index++;
//
//                        if (index == lowerCased.length()) {
//                            break;
//                        }
//                    }
//                    // test
////                    for (char Character : comparedMonth) {
////                        System.out.print(Character + ", ");
////                    }
////                    System.out.println("");
//                    boolean same = true;
//                    for (int x = 0; x < month.length(); x++) {
//                        if (comparedMonth[x] != month.charAt(x)) {
//                            same = false;
//                            break;
//                        }
//                    }
//                    if (same) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
        return wordInPass(Password, romanNumeral);
    }


    public static boolean wordInPass(String Password, ArrayList<String> target) {
        for (int i = 0; i < Password.length(); i++) { // char character : Password.toCharArray()

            for (String element : target) {

                if (Password.charAt(i) == element.charAt(0)) {
                    char[] comparedMonth = new char[element.length()];
                    int index = i;
                    int put = 0;
                    while (index < i+element.length()) {
                        comparedMonth[put] = Password.charAt(index);

                        put++;
                        index++;

                        if (index == Password.length()) {
                            break;
                        }
                    }
                    // test
//                    for (char Character : comparedMonth) {
//                        System.out.print(Character + ", ");
//                    }
//                    System.out.println("");
                    boolean same = true;
                    for (int x = 0; x < element.length(); x++) {
                        if (comparedMonth[x] != element.charAt(x)) {
                            same = false;
                            break;
                        }
                    }
                    if (same) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void listBuilder (ArrayList<HashMap<String, String>> list, Boolean[] rule) {
        HashMap<String, String> editHash = new HashMap<>();

        int streak = 0;
        while (rule[streak].equals(true)) {
            streak++;
            if (streak == rule.length) {
                break;
            }
        }

        int pendingPos = 0;
        HashMap<String, String> miniHashmap = (HashMap<String, String>) list.get(pendingPos);
        while (!miniHashmap.get("Status").equals("pending")) {
            pendingPos++;
            miniHashmap = (HashMap<String, String>) list.get(pendingPos);
        }

        int posToPend = Math.max(streak, pendingPos);

        if (posToPend < list.size()) {
            editHash = new HashMap<>();
            miniHashmap = (HashMap<String, String>) list.get(posToPend);
            editHash.put("Status", "pending");
            editHash.put("Description", miniHashmap.get("Description"));
            editHash.put("Correct", miniHashmap.get("Correct"));
            list.set(posToPend, editHash);
        }

        int index = 0;
        while (index < posToPend) {
            miniHashmap = (HashMap<String, String>) list.get(index);
            editHash = new HashMap<>();

            editHash.put("Status", "unlocked");
            editHash.put("Description", miniHashmap.get("Description"));
            editHash.put("Correct", miniHashmap.get("Correct"));

            list.set(index, editHash);
//            System.out.println(list);

            index++;

            if (index == list.size()) {
                break;
            }
        }
        //--------------status generated

        // key: Correct;
        index = 0;
        miniHashmap = (HashMap<String, String>) list.get(index);
        while (!miniHashmap.get("Status").equals("locked")) {

            miniHashmap = (HashMap<String, String>) list.get(index);
            if (rule[index]) {
                editHash = new HashMap<>();
                editHash.put("Status", miniHashmap.get("Status"));
                editHash.put("Description", miniHashmap.get("Description"));
                editHash.put("Correct", "true");
                list.set(index, editHash);
            } else {
                editHash = new HashMap<>();
                editHash.put("Status", miniHashmap.get("Status"));
                editHash.put("Description", miniHashmap.get("Description"));
                editHash.put("Correct", "false");
                list.set(index, editHash);
            }

            index++;
            if (index == list.size()) {
                break;
            }
        }
    }

    public static void display(ArrayList<HashMap<String, String>> list) {
        ArrayList<String> displayList = new ArrayList<>();
        int i = 0;
        int index =0;
        HashMap<String, String> miniHashmap = (HashMap<String, String>) list.get(index);
        while (!miniHashmap.get("Status").equals("locked")) {
//            miniHashmap = (HashMap<String, String>) list.get(index);
            if (miniHashmap.get("Correct").equals("true")) {
                System.out.println(miniHashmap.get("Description") + "✅✅✅");
            }   else {
                displayList.add(i, miniHashmap.get("Description") + "❌❌❌");
                i++;
            }

            index++;
            if (index == list.size()) {
                break;
            }
            miniHashmap = (HashMap<String, String>) list.get(index);
        }
        for (String description : displayList) {
            System.out.println(description);
        }
    }

    public static void skip () {
    }
}