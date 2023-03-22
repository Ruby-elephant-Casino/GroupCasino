package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.WheelThing;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.awt.*;
import java.util.Scanner;

public class RouletteGame implements GameInterface {
    public static void Roulette() {
        WheelThing wheel = new WheelThing(); //created wheel object to stimulate roulette wheel
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
            System.out.println("1. Number (pays 35 to 1)");
            System.out.println("2. Even or Odd (pays 1 to 1)");
            System.out.println("3. Red or Black (pays 1 to 1)");
            System.out.println("4. Green 0 & 00 (pays 1 to 1)");
            int betType = scanner.nextInt();

            int result = wheel.spin(0, 37); //spin wheel to get random number

            int payOut = 0;

            switch (betType) {
                case 1: //Straight up
                    System.out.print("Enter a number between 0 and 36: ");
                    int number = scanner.nextInt();
                    if (number == result) {
                        payOut = bet * 35;
                        System.out.println("Congratulations! The ball landed on " + result + ".");
                    } else {
                        System.out.println("Sorry, the ball landed on " + result + ".");
                    }
                    break;
                case 2:
                    System.out.println("Enter '1' for even and '2' for odd: ");
                    int evenOdd = scanner.nextInt();
                    if (result == 0 || (result % 2 == 0 && evenOdd == 1) || result % 2 == 1 && evenOdd == 2) {
                        payOut = bet;
                        System.out.println("Congratulations! The ball landed on " + result + ".");
                    } else {
                        System.out.println("Sorry, the ball landed on " + result + ".");
                    }
                    break;
                case 3:
                    System.out.println("Enter '1' for red and '2' for black: ");
                    int redOrBlack = scanner.nextInt();
                    if ((result <= 10 || (result >= 19 && result <= 28)) && redOrBlack == 1 ||
                            ((result >= 11 && result <= 18) || (result >= 29 && result <= 36)) && redOrBlack == 2) {
                        payOut = bet;
                        System.out.println("Congratulations! The ball landed on " + result + ".");
                    } else {
                        System.out.println("Sorry, the ball landed on " + result + ".");
                    }
                    break;
                case 4:
                    System.out.println("Enter '1' for 0 and '2' for 00: ");
                    int green = scanner.nextInt();
                    if ((result == 0 && green == 1) || (result == 37 && green == 2)) {
                        payOut = bet * 35;
                        System.out.println("Congratulations! The ball landed on " + result + ".");
                    } else {
                        System.out.println("Sorry, the ball landed on " + result + ".");
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
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {

    }
}



