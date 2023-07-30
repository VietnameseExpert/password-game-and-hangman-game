import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class July14th_passwordGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String urPassword;

        // [{rule 1: T/F, unlocked: T/F}]
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        HashMap<String, String> word = new HashMap<>();
        word.put("Status", "pending"); // "unlocked"
        word.put("Description", "Your password must be at least 5 characters. ");
        list.add(word);

        word = new HashMap<>(); // create a new one!
        word.put("Status", "locked");
        word.put("Description", "Your password must include a number.");
        list.add(word);

        word = new HashMap<>();
        word.put("Status", "locked");
        word.put("Description", "Your password must include an uppercase letter.");
        list.add(word);

        word = new HashMap<>();
        word.put("Status", "locked");
        word.put("Description", "Your password must include a special character.");
        list.add(word);

        word = new HashMap<>();
        word.put("Status", "locked");
        word.put("Description", "The digits in your password must add up to 25.");
        list.add(word);

        while (true) {
            System.out.println("Enter password: ");
            urPassword = input.nextLine();
            listBuilder(list, rule_01(urPassword), rule_02(urPassword), rule_03(urPassword), rule_04(urPassword), rule_05(urPassword));
            System.out.println(list);
        }
    }

    public static boolean rule_01(String Password) {
        String editPass = Password;
        editPass.replace(" ", "");
        if (editPass.length() >= 5) {
            return true;
        }
        return false;
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
    public static boolean rule_04(String Password) {;
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
        if (sum == 25) {
            return true;
        }
        return false;
    }
    public static void skip () {
    }

    public static void listBuilder (ArrayList<HashMap<String, String>> list1, boolean rule_01, boolean rule_02, boolean rule_03, boolean rule_04, boolean rule_05) {
        ArrayList<HashMap<String, String>> list = list1;
        boolean mem;
        boolean onStreak = true;
        Boolean[] urGuessList = new Boolean[5];
        urGuessList[0] = rule_01;
        urGuessList[1] = rule_02;
        urGuessList[2] = rule_03;
        urGuessList[3] = rule_04;
        urGuessList[4] = rule_05;

        int index = 0;
        HashMap<String, String> miniHashmap = (HashMap<String, String>) list.get(index);
        while (miniHashmap.get("Status") != "pending" || onStreak) {
            HashMap<String, String> editHash = new HashMap<>();

            mem = miniHashmap.get("Status").equals("pending");
            System.out.println(miniHashmap.get("Status"));
            System.out.println(mem);
            if (mem) {
                if (urGuessList[index]) {
                    editHash.put("Status", "unlocked");
                }   else {
                    editHash.put("Status", "locked");
                }
        }   else {
                if (urGuessList[index]) {
                    editHash.put("Status", "unlocked");
                }   else {
                    editHash.put("Status", "locked");
                }
            }
            editHash.put("Description", miniHashmap.get("Description"));

            list.set(index, editHash);

            if (miniHashmap.get("Status").equals("locked")) {
                onStreak = false;
            }

            index++;
            miniHashmap = (HashMap<String, String>) list.get(index);

            editHash = new HashMap<>();
            if (mem) {
                editHash.put("Status","pending");
                editHash.put("Description", miniHashmap.get("Description"));
                list.set(index, editHash);
            }
            System.out.println(editHash);
            System.out.println(list);
        }
    }
}