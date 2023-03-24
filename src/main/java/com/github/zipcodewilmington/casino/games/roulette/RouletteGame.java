package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.casino.gameTools.WheelThing;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;


import java.util.HashMap;
import java.util.Map;

import java.util.Scanner;

public class RouletteGame extends Game implements GamblingGameInterface {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
    private final IOConsole errorConsole = new IOConsole(AnsiColor.RED);
    private final IOConsole successConsole = new IOConsole(AnsiColor.YELLOW);
    Map<Integer, String> numberToColor = new HashMap<>();
    private RoulettePlayer currentPlayer;
    private BettingPayout betHandler;
    WheelThing wheel;
    Integer payout;

   public RouletteGame(){
       wheel = new WheelThing();
       betHandler = new BettingPayout(5,1000);
       payout = 0;
    }

    public static void Roulette() {
         //created wheel object to stimulate roulette wheel
        int balance = 0;

        Scanner scanner = new Scanner(System.in); // scanner to read suer input

        System.out.println("Welcome to Roulette!"); // Welcome player to game

        while (balance > 0) { // while loop to keep playing until the player runs out of money

            System.out.println("Balance: " + balance);
            System.out.println("Enter your bet (To quit: 0");
            int bet = scanner.nextInt();

            if (bet == 0) { // quit game if bet is 0
                break;
            }
            if (bet > balance) { // verify that bet is valid
                System.out.println("Invalid bet: Not enough money.");
                continue;
            }
            System.out.println("Choose a bet type:"); //ask player to choose bet type
            System.out.println("1. Number (pays 38 to 1)");
            System.out.println("2. Even or Odd (pays 1 to 1)");
            System.out.println("3. Red or Black (pays 1 to 1)");
            System.out.println("4. Green 0 & 00 (pays 1 to 3)");
            int betType = scanner.nextInt();

            int result = Integer.parseInt(wheel.spin()); //spin wheel to get random number
            String color= numberToColor.get(result); // get color of the number it lands on
            int payOut = 0;

            switch (betType) {
                case 1: //Straight up
                    System.out.print("Enter a number between 0 and 36: ");
                    int number = scanner.nextInt();
                    if (number == result) {
                        payOut = bet * 35;
                        System.out.println("Congratulations! The ball landed on " + result +"("+ color+").");
                    } else {
                        System.out.println("Sorry, the ball landed on " + result + "("+ color+").");
                    }
                    break;
                case 2:
                    System.out.println("Enter '1' for even and '2' for odd: ");
                    int evenOdd = scanner.nextInt();
                    if (result == 0 || (result % 2 == 0 && evenOdd == 1) || result % 2 == 1 && evenOdd == 2) {
                        payOut = bet;
                        System.out.println("Congratulations! The ball landed on " + result +"("+ color+").");
                    } else {
                        System.out.println("Sorry, the ball landed on " + result +"("+ color+").");
                    }
                    break;
                case 3:
                    System.out.println("Enter '1' for red and '2' for black: ");
                    int redOrBlack = scanner.nextInt();
                    if ((result <= 10 || (result >= 19 && result <= 28)) && redOrBlack == 1 ||
                            ((result >= 11 && result <= 18) || (result >= 29 && result <= 36)) && redOrBlack == 2) {
                        payOut = bet;
                        System.out.println("Congratulations! The ball landed on " + result +"("+ color+").");
                    } else {
                        System.out.println("Sorry, the ball landed on " + result +"("+ color+").");
                    }
                    break;
                case 4:
                    System.out.println("Enter '1' for 0 and '2' for 00: ");
                    int green = scanner.nextInt();
                    if ((result == 0 && green == 1) || (result == 00 && green == 2)) {
                        payOut = bet * 38;
                        System.out.println("Congratulations! The ball landed on " + result +"("+ color+").");
                    } else {
                        System.out.println("Sorry, the ball landed on " + result +"("+ color+").");
                    }
                    break;
            }
            balance -= bet;
            balance += payOut;

            System.out.println("Balance: "+ balance);

            if (payOut >0){
                System.out.println("You won "+ payOut+ "!");
            }else{
                System.out.println("You lost "+bet+".");
            }

        }

    }

    @Override
    public void run() {
        boolean isPlaying = true;
        console.println("Welcome to the Game Selection Dashboard!");
        while(isPlaying) {
            Integer option = RouletteMenu();
            switch (option) {
                case 1: //slots game
                    startGame();
                    break;
                case 2: //slots game
                    BalanceManager.showBalance(currentPlayer.getPlayerAccount());
                    break;
                case 3: // exit game
                    isPlaying = false;
                    break;
                default: //slots game
                    break;
            }
        }
    }
    private Integer RouletteMenu(){
        return console.getIntegerInput(new StringBuilder()
                .append("+-----------------------+\n")
                .append("|       GAME MENU       |\n")
                .append("+-----------------------+\n")
                .append("|  1. Start Game        |\n")
                .append("|  2. Check Balance     |\n")
                .append("|  3. Exit Game         |\n")
                .append("+-----------------------+\n")
                .append("SELECT A NUMBER: ")
                .toString());
    }

    @Override
    public void startGame() {
        boolean isPlaying = true;
        console.println("Welcome to the Game Selection Dashboard!");
        Integer option = RouletteStartGameMenu();
        while(isPlaying) {
            switch (option) {
                case 1: // Number (pays 38 to 1)
                    Double bet = betHandler.takeBet(5,1000,currentPlayer.getPlayerAccount().getBalance())
                    if(bet == 0.0){
                        isPlaying = false;
                    }


                    break;
                case 2: // Even or Odd (pays 1 to 1)
                    BalanceManager.showBalance(currentPlayer.getPlayerAccount());
                    break;
                case 3: // Red or Black (pays 1 to 1)
                    isPlaying = false;
                    break;
                case 4: // Red or Black (pays 1 to 1)
                    isPlaying = false;
                    break;
                case 5: // Red or Black (pays 1 to 1)
                    isPlaying = false;
                    break;
                default: //slots game
                    break;
            }
        }
    }
    private Integer RouletteStartGameMenu(){
        return console.getIntegerInput(new StringBuilder()
                .append("+---------------------------------+\n")
                .append("|   ROULETTE BET TYPE SELECTION   |\n")
                .append("+---------------------------------+\n")
                .append("|  1. Number (pays 38 to 1)       |\n")
                .append("|  2. Even or Odd (pays 1 to 1)   |\n")
                .append("|  3. Red or Black (pays 1 to 1)  |\n")
                .append("|  4. Green 0 & 00 (pays 1 to 3)  |\n")
                .append("|  5. Exit betting mode           |\n")
                .append("+---------------------------------+\n")
                .append("SELECT A BET OPTION: ")
                .toString());
    }

    public boolean checkGuessNumber(){

       boolean isWon = false;
       boolean isRolling = true;
       while(isRolling){

       }
       return false;
    }

    /*
    int result = Integer.parseInt(wheel.spin()); //spin wheel to get random number
            String color= numberToColor.get(result); // get color of the number it lands on
            int payOut = 0;

            switch (betType) {
                case 1: //Straight up
                    System.out.print("Enter a number between 0 and 36: ");
                    int number = scanner.nextInt();
                    if (number == result) {
                        payOut = bet * 35;
                        System.out.println("Congratulations! The ball landed on " + result +"("+ color+").");
                    } else {
                        System.out.println("Sorry, the ball landed on " + result + "("+ color+").");
                    }
                    break;
     */

    public Map<Integer,String> mapNumberToColor(){
        numberToColor = new HashMap<>();
        Integer[] green = new Integer[]{0,00};
        Integer[] red = new Integer[]{1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
        Integer[] black = new Integer[]{2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};
        for(int i : green){
            numberToColor.put(i,"green");
        }
        for(int i : red){
            numberToColor.put(i,"red");
        }
        for(int i : black){
            numberToColor.put(i,"black");
        }
//        numberToColor.put(0, "green");
//        numberToColor.put(00, "green");
//
//        numberToColor.put(1, "red");
//        numberToColor.put(3, "red");
//        numberToColor.put(5, "red");
//        numberToColor.put(7, "red");
//        numberToColor.put(9, "red");
//        numberToColor.put(12, "red");
//        numberToColor.put(14, "red");
//
//        numberToColor.put(16, "red");
//        numberToColor.put(18, "red");
//        numberToColor.put(19, "red");
//        numberToColor.put(21, "red");
//        numberToColor.put(23, "red");
//        numberToColor.put(25, "red");
//
//        numberToColor.put(27, "red");
//        numberToColor.put(30, "red");
//        numberToColor.put(32, "red");
//        numberToColor.put(34, "red");
//        numberToColor.put(36, "red");
//
//
//        numberToColor.put(2, "black");
//        numberToColor.put(4, "black");
//        numberToColor.put(6, "black");
//        numberToColor.put(8, "black");
//        numberToColor.put(10, "black");
//        numberToColor.put(11, "black");
//        numberToColor.put(13, "black");
//        numberToColor.put(15, "black");
//        numberToColor.put(17, "black");
//        numberToColor.put(20, "black");
//        numberToColor.put(22, "black");
//        numberToColor.put(24, "black");
//        numberToColor.put(26, "black");
//        numberToColor.put(28, "black");
//        numberToColor.put(29, "black");
//        numberToColor.put(31, "black");
//        numberToColor.put(33, "black");
//        numberToColor.put(35, "black");
        return numberToColor;
    }
    @Override
    public void remove(Player player) {

    }
    @Override
    public Player removePlayer(Player player) {
        return currentPlayer = null;
    }

    @Override
    public Player add(Player player) {
        this.currentPlayer = (RoulettePlayer) player;
        return player;
    }
}



