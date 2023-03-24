package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.BalanceManager;
import com.github.zipcodewilmington.casino.BettingPayout;
import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.Dice;
import com.github.zipcodewilmington.casino.games.war.WarPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame extends Game {
    StringBuilder sb = new StringBuilder();
    IOConsole console = new IOConsole(AnsiColor.YELLOW);
    NumberGuessPlayer currentPlayer;
    int minBet = 5;
    int maxBet = 200;
    int numberOfDice = 2;
    int playerGuess;
    boolean isPlaying, isRunning, isBetting;
    BettingPayout bettingPayout = new BettingPayout(minBet, maxBet);


    @Override
    public void remove(Player player) {

    }

    @Override
    public void run() throws InterruptedException {
        diceMainMenu();
    }

    public void diceMainMenu(){
        isRunning = true;
        while(isRunning){
            sb.setLength(0);
            Integer choice = console.getIntegerInput(sb
                    .append("+-------------------------------+\n")
                    .append("|          NUMBER GUESS         |\n")
                    .append("+-------------------------------+\n")
                    .append("|  1. Start game                |\n")
                    .append("|  2. Check balance             |\n")
                    .append("|  3. Exit Game                 |\n")
                    .append("+-------------------------------+\n")
                    .append("SELECT A NUMBER: ")
                    .toString());

            switch(choice){
                case 1:
                    printBetMenu();
                    break;
                case 2:
                    BalanceManager.showBalance(currentPlayer.getPlayerAccount());
                    break;
                case 3:
                    removePlayer(currentPlayer);
                    exitGame();
                default:
                    console.println("Pick a viable choice!");
                    break;
            }
        }
    }

    @Override
    public Player add(Player player) {
        return this.currentPlayer = (NumberGuessPlayer) player;
    }

    @Override
    public Player removePlayer(Player player) {return currentPlayer = null;}

    @Override
    public void startGame() {

    }

    public void printBetMenu() {
        isBetting = true;
        sb.setLength(0);
        while(isBetting){
            Integer choice = console.getIntegerInput(sb
                    .append("+-------------------------------+\n")
                    .append("|          NUMBER GUESS         |\n")
                    .append("+-------------------------------+\n")
                    .append("|  1. Make Bet                  |\n")
                    .append("|  2. Check balance             |\n")
                    .append("|  3. Exit Current Menu         |\n")
                    .append("|  4. Exit Game                 |\n")
                    .append("+-------------------------------+\n")
                    .append("SELECT A NUMBER: ")
                    .toString());
            switch (choice) {
                case 1:
                    bettingPayout.takeBet(minBet, maxBet, currentPlayer.getPlayerAccount().getBalance());
                    getNumOfDice();
                    break;
                case 2:
                    BalanceManager.showBalance(currentPlayer.getPlayerAccount());
                    break;
                case 3:
                    isBetting = false;
                    break;
                case 4:
                    exitGame();
                default:
                    console.println("Pick a viable choice!");
                    break;
            }
        }
    }
    public void getNumOfDice(){
        isPlaying = true;
        sb.setLength(0);
        while(isPlaying){
            Integer choice = console.getIntegerInput(sb
                    .append("+-------------------------------+\n")
                    .append("|     NUMBER GUESS DICE MENU    |\n")
                    .append("+-------------------------------+\n")
                    .append("|  1. How many dice to roll?    |\n")
                    .append("|  2. Guess the sum of the dice |\n")
                    .append("|  3. Exit Menu                 |\n")
                    .append("|  4. Exit Game                 |\n")
                    .append("+-------------------------------+\n")
                    .append("SELECT A NUMBER: ")
                    .toString());
            switch (choice) {
                case 1:
                    numberOfDice = getNumberOfDice();
                    break;
                case 2:
                    sb.setLength(0);
                    sb.append("Enter the sum of the dice: ");
                    playerGuess = console.getIntegerInput(sb.toString());
                    break;
                case 3:
                    isPlaying = false;
                    break;
                case 4:
                    exitGame();
                    break;
                default:
                    console.println("Pick a viable choice!");
                    break;
            }
        }
    }

    private int getNumberOfDice() {
        sb.setLength(0);
        sb.append("Enter the number of dice you want to use: ");
        //have to make sure they can't have negative guess
        return console.getIntegerInput(sb.toString());
    }


    public void exitGame(){
        isBetting = false;
        isPlaying = false;
        isRunning = false;
    }
}