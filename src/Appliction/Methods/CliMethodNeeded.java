package Appliction.Methods;

import java.util.ArrayList;

public class CliMethodNeeded {
    public boolean IsItFirstSpace(String a) {
        return a.charAt(0) == ' ';
    }

    public boolean IsIsLastSpace(String a) {
        return a.charAt(a.length() - 1) == ' ';
    }

    public boolean IsTwoWord(String a) {
        int SpaceNumber = 0;
        for (char c : a.toCharArray()) {
            if (c == ' ') {
                SpaceNumber++;
            }
        }
        return SpaceNumber == 1;
    }

    public static String ReturnFirstWord(String a) {
        ArrayList<Character> first = new ArrayList<>();
        for (char c : a.toCharArray()) {
            if (c == ' ') {
                break;
            }
            first.add(c);
        }
        String result = "";
        for (char c : first) {
            result = result + c;
        }
        return result;
    }

    public static String ReturnSecondWord(String a) {
        boolean residam = false;
        ArrayList<Character> Second = new ArrayList<>();
        for (char c : a.toCharArray()) {
            if (c == ' ') {
                residam = true;
                continue;
            }
            if (residam) {
                Second.add(c);
            }
        }
        String result = "";
        for (char c : Second) {
            result = result + c;
        }
        return result;
    }
}
