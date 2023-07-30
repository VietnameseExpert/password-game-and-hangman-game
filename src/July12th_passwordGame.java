import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class July12th_passwordGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String urPassword;

        // [{rule 1: T/F, unlocked: T/F}]
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        ArrayList<HashMap<String, String>> temp = new ArrayList<>();

        HashMap<String, String> word = new HashMap<>();
        word.put("Status","locked"); // "unlocked"
        word.put("Description","Your password must be at least 5 characters. ");
        list.add(word);

        word = new HashMap<>(); // create a new one!
        word.put("Status","pending");
        word.put("Description","Your password must include a number.");
        list.add(word);

        word = new HashMap<>();
        word.put("Status","locked");
        word.put("Description","Your password must include an uppercase letter.");
        list.add(word);

        word = new HashMap<>();
        word.put("Status","locked");
        word.put("Description","Your password must include a special character.");
        list.add(word);

        word = new HashMap<>();
        word.put("Status","locked");
        word.put("Description","The digits in your password must add up to 25.");
        list.add(word);

        // Testing :))
//        System.out.println(list);
//        statusGenerator(list);
//        System.out.println(list);

        while (true) {
            System.out.println("Enter password: ");
            urPassword = input.nextLine();

            listBuilder(list, rule_01(urPassword), rule_02(urPassword), rule_03(urPassword), rule_04(urPassword), rule_05(urPassword));
            temp = list;
            System.out.println("status list: " + temp);
            statusGenerator(list);
            System.out.println("isLocked list: " + list);
            Display(temp, list);

            // Test
//            System.out.println(rule_01(urPassword));
//            System.out.println(rule_02(urPassword));
//            System.out.println(rule_03(urPassword));
//            System.out.println(rule_04(urPassword));
//            System.out.println(rule_05(urPassword));
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
//        int passwordLength;
//
//        int spaceCounter = 0;
//        char[] passChars = Password.toCharArray();
//        for (char character : passChars) {
//            if (String.valueOf(character).equals(" ")) {
//                spaceCounter++;
//            }
//        }
//
//        passwordLength = Password.length() - spaceCounter;
//        if (passwordLength >= 5) {
//            return true;
//        }
//        return false;
//    }
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

    public static void listBuilder (ArrayList<HashMap<String, String>> list, boolean rule_01, boolean rule_02, boolean rule_03, boolean rule_04, boolean rule_05) {
//        Boolean onStreak = true;
        Boolean[] urGuessList = new Boolean[5];
        urGuessList[0] = rule_01;
        urGuessList[1] = rule_02;
        urGuessList[2] = rule_03;
        urGuessList[3] = rule_04;
        urGuessList[4] = rule_05;

        int index = 0;
        HashMap<String, String> miniHashmap = (HashMap<String, String>) list.get(index);
        while (miniHashmap.get("Status") != "pending") { //|| onStreak) {
//            if (miniHashmap.get("Status") == "locked") {
//                onStreak = false;
//            }
            HashMap<String, String> repHash = new HashMap<>();

            if (urGuessList[index].equals(true)) {
                repHash.put("Status", "unlocked");
            } else {
                repHash.put("Status", "locked");
            }
            repHash.put("Description", miniHashmap.get("Description"));

            list.set(index, repHash);

            index++;
            miniHashmap = (HashMap<String, String>) list.get(index);

//            index++;
//            miniHashmap = (HashMap<String, String>) list.get(index);

//            if (onStreak && miniHashmap.get("Status") == "Pending") {
//                if (urGuessList[index].equals(true)) {
//                    repHash.put("Status", "unlocked");
//                } else {
//                    repHash.put("Status", "locked");
//                }
//                repHash.put("Description", miniHashmap.get("Description"));
//                list.set(index, repHash);
//            }
//
//            repHash = new HashMap<>();
//            if (urGuessList[index].equals(true)) {
//                repHash.put("Status", "pending");
//            }
//
//            repHash.put("Description", miniHashmap.get("Description"));
//            list.set(index, repHash);
        }
    }

    public static void Display(ArrayList<HashMap<String, String>> statusList, ArrayList<HashMap<String, String>> isLockedlist) {
        int index = 0;
        HashMap<String, String> stamap = (HashMap<String, String>) statusList.get(index);
        HashMap<String, String> ismap = (HashMap<String, String>) statusList.get(index);
        while (ismap.get("Status") != "pending") {
            if (stamap.get("Status") == "unlocked") {
                System.out.println(stamap.get("Description") + "✅✅✅");
            }   else {
                System.out.println(stamap.get("Description") + "❌❌❌");
            }
            index++;
            stamap = (HashMap<String, String>) statusList.get(index);
            ismap = (HashMap<String, String>) isLockedlist.get(index);
        }
    }

    public static void statusGenerator(ArrayList<HashMap<String, String>> list) {
        boolean allUnlocked = true;
        int index = 0;
        HashMap<String, String> miniHashmap = (HashMap<String, String>) list.get(index);
        while (miniHashmap.get("Status") != "pending") {
            if (miniHashmap.get("Status") == "locked") {
                allUnlocked = false;
            }
            index++;
            miniHashmap = (HashMap<String, String>) list.get(index);
        }

        HashMap<String, String> repHash = new HashMap<>();
        repHash.put("Status","unlocked");
        repHash.put("Description", miniHashmap.get("Description"));
        if (allUnlocked == true) {
            list.set(index, repHash);

            repHash = new HashMap<>();
            miniHashmap = (HashMap<String, String>) list.get(index+1);

            repHash.put("Status","pending");
            list.set(index+1, repHash);
            repHash.put("Description", miniHashmap.get("Description"));
        }
    }

    public static void skip () {
    }
}
