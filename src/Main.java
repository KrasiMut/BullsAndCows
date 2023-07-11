import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void playSinglePlayerGame(Scanner scanner) {
        byte digitsCount = 4;
        String secretNumber = generateSecretNumber(digitsCount);

        System.out.println("Computer has selected a secret number.");

        while (true) {
            System.out.println("Player, it's your turn to guess:");

            String guess = getValidGuess(digitsCount, scanner);
            byte[] result = getBullsAndCows(guess, secretNumber);
            System.out.println("Bulls: " + result[0] + ", Cows: " + result[1]);

            if (result[0] == digitsCount) {
                System.out.println("Congratulations, Player! You guessed the secret number!");
                break;
            }
        }
    }
    public static String getValidGuess(byte digitsCount, Scanner scanner) {
        String guess;
        while (true) {
            guess = scanner.nextLine();
            if (guess.length() != digitsCount) {
                System.out.println("Invalid guess length. Please enter a " + digitsCount + "-digit number.");
            } else if (!isUniqueDigits(guess)) {
                System.out.println("Invalid guess. Digits should be unique.");
            } else {
                break;
            }
        }
        return guess;
    }
    public static boolean isUniqueDigits(String number) {
        for (byte i = 0; i < number.length() - 1; i++) {
            if (number.substring(i + 1).contains(String.valueOf(number.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static String generateSecretNumber(byte digitsCount) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        boolean[] usedDigits = new boolean[10];
        byte count = 0;

        while (count < digitsCount) {
            int digit = random.nextInt(10);
            if (!usedDigits[digit]) {
                sb.append(digit);
                usedDigits[digit] = true;
                count++;
            }
        }

        return sb.toString();
    }

    public static byte[] getBullsAndCows(String guess, String number) {
        byte bulls = 0;
        byte cows = 0;

        for (byte i = 0; i < number.length(); i++) {
            char guessDigit = guess.charAt(i);
            char numberDigit = number.charAt(i);

            if (guessDigit == numberDigit) {
                bulls++;
            } else if (number.contains(String.valueOf(guessDigit))) {
                cows++;
            }
        }

        return new byte[]{bulls, cows};
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Welcome to Bulls and Cows Game!");
        System.out.println("Choose a game mode:");
        System.out.println("1. Single Player");
        System.out.println("2. Two Players");
    }
}
