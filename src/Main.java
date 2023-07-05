import java.util.Random;
import java.util.Scanner;

public class Main {
    public static boolean isUniqueDigits(String number) {
        for (byte i = 0; i < number.length() - 1; i++) {
            if (number.substring(i + 1).contains(String.valueOf(number.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Welcome to Bulls and Cows Game!");
        System.out.println("Choose a game mode:");
        System.out.println("1. Single Player");
        System.out.println("2. Two Players");
    }
}