package com.github.zipcodewilmington.casino.games.wheelof6;

import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.WheelThing;

import java.util.Scanner;

public class WheelOfSixGame extends Game {
    private WheelThing wheel;
    private Scanner scanner;

    public WheelOfSixGame(WheelThing wheel, Scanner scanner) {
        this.wheel = wheel;
        this.scanner = scanner;
    }



    public void play() {
        int bet = getBet();
        int guess = getGuess();

        int result = Integer.parseInt(wheel.spin());
        System.out.println("The wheel landed on: " + result);

        if (guess == result) {
            int winnings = calculateWinnings(bet);
            System.out.println("Congratulations! You won " + winnings + " chips!");
        } else {
            System.out.println("Sorry, better luck next time!");
        }
    }

    public int getBet() {
        System.out.print("Enter your bet amount (or 0 to quit): ");
        int bet = scanner.nextInt();
        return bet;
    }

    public int getGuess() {
        System.out.print("Enter your guess (1-6): ");
        int guess = scanner.nextInt();
        return guess;
    }

    public static int calculateWinnings(int bet) {
        return bet;
    }

    public static void main(String[] args) {
        String[] wheelSlots = {"1", "2", "3", "4", "5", "6"};
        WheelThing wheel = new WheelThing();
        wheel.Wheel(wheelSlots);
        Scanner scanner = new Scanner(System.in);
        WheelOfSixGame game = new WheelOfSixGame(wheel, scanner);

        System.out.println("Welcome to the Wheel of 6!");
        System.out.println("The game is simple - just guess which of the 6 slots the wheel will land on.");

        System.out.println();

        while (true) {
            game.play();

            int bet = game.getBet();
            if (bet == 0) {
                break;
            }
        }
    }



    public Object[] spinWheel(String[] wheel) {
        return wheel;
    }

    public int getPayout(String s, int i) {
        return i;
    }

    @Override
    public void remove(Player player) {

    }

    @Override
    public void run() throws InterruptedException {

    }

    @Override
    public Player add(Player player) {
        return null;
    }



    @Override
    public void startGame() {

    }
}