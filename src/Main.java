import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void playSinglePlayerGame(Scanner scanner) {
        byte digitsCount = 4;
        String secretNumber = generateSecretNumber(digitsCount);
        List<String> history = new ArrayList<>();

        System.out.println("Computer has selected a secret number.");
        System.out.println("Secret number: " + secretNumber); //for testing purposes only

        System.out.println("Player, please enter your name:");
        String playerName = scanner.nextLine();

        while (true) {
            System.out.println("--------------------------------------");
            System.out.println(playerName + ", it's your turn to guess:");
            System.out.println("Your history: " + String.join(", ", history));

            String guess = getValidGuess(digitsCount, scanner);
            byte[] result = getBullsAndCows(guess, secretNumber);
            System.out.println("Bulls: " + result[0] + ", Cows: " + result[1] + "\n" + "--------------------------------------");

            history.add(guess + "(B" + result[0] + "|" + "C" + result[1] + ")");

            if (result[0] == digitsCount) {
                System.out.println("******************************\n    CONGRATULATIONS, " + playerName + "!" +  "\n******************************");
                break;
            }
        }

        System.out.println("Game history:");
        for (String guess : history) {
            System.out.println(guess + ", ");
        }
    }

    public static void playTwoPlayerGame(Scanner scanner) {
        byte digitsCount = 4;
        String secretNumberPlayer1 = generateSecretNumber(digitsCount);
        String secretNumberPlayer2 = generateSecretNumber(digitsCount);
        System.out.println("Secret Number of Player 1: " + secretNumberPlayer1);   // For testing
        System.out.println("Secret Number of Player 2: " + secretNumberPlayer2);  // purposes only!

        List<String> historyPlayer1 = new ArrayList<>();
        List<String> historyPlayer2 = new ArrayList<>();

        System.out.println("Player 1, please enter your name:");
        String player1Name = scanner.nextLine();

        System.out.println("Player 2, please enter your name:");
        String player2Name = scanner.nextLine();

        while (true) {
            System.out.println("--------------------------------------");
            System.out.println(player1Name + ", it's your turn to guess:");
            System.out.println("History of " + player1Name + ": " + String.join(", ", historyPlayer1));

            String guess1 = getValidGuess(digitsCount, scanner);
            byte[] result1 = getBullsAndCows(guess1, secretNumberPlayer1);
            System.out.println("Bulls: " + result1[0] + ", Cows: " + result1[1] + "\n" + "--------------------------------------");

            historyPlayer1.add(guess1 + "(B" + result1[0] + "|" + "C" + result1[1] + ")");

            if (result1[0] == digitsCount) {
                System.out.println("******************************\n    CONGRATULATIONS, " + player1Name + "!" + "\n******************************");
                break;
            }

            System.out.println(player2Name + ", it's your turn to guess:");
            System.out.println("History of " + player2Name + ": " + String.join(", ", historyPlayer2));

            String guess2 = getValidGuess(digitsCount, scanner);
            byte[] result2 = getBullsAndCows(guess2, secretNumberPlayer2);
            System.out.println("Bulls: " + result2[0] + ", Cows: " + result2[1] + "\n" + "--------------------------------------");

            historyPlayer2.add(guess2 + "(B" + result2[0] + "|" + "C" + result2[1] + ")");

            if (result2[0] == digitsCount) {
                System.out.println("******************************\n    CONGRATULATIONS, " + player2Name + "!" + "\n******************************");
                break;
            }
        }

        System.out.println("Game history:");
        System.out.println(player1Name + ":");
        for (String guess : historyPlayer1) {
            System.out.print(guess + ", ");
        }
        System.out.println();
        System.out.println(player2Name + ":");
        for (String guess : historyPlayer2) {
            System.out.print(guess + ", ");
        }
    }

    public static String getValidGuess(byte digitsCount, Scanner scanner) {
        String guess;
        while (true) {
            guess = scanner.nextLine();
            if (guess.length() != digitsCount) {
                System.out.println("Invalid guess length. Please enter a " + digitsCount + "-digit number.");
            } else if (!guess.matches("[0-9]+")) {
            System.out.println("Invalid guess. Please enter only digits.");
            }else if (!isUniqueDigits(guess)) {
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

        byte gameMode;
        while (true) {
            if (scn.hasNextByte()) {
                gameMode = scn.nextByte();
                if (gameMode == 1 || gameMode == 2) {
                    break;
                } else {
                    System.out.println("Invalid game mode. Please choose 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scn.nextLine();
            }
        }

        scn.nextLine();
        System.out.println("--------------------------------------");

        if (gameMode == 1) {
            playSinglePlayerGame(scn);
        } else {
            playTwoPlayerGame(scn);
        }

        scn.close();
    }
}


