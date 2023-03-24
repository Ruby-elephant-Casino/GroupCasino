package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.BalanceManager;
import com.github.zipcodewilmington.casino.BettingPayout;
import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
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
    double minBet = 5;
    double maxBet = 200;
    @Override
    public void remove(Player player) {

    }

    @Override
    public void run() throws InterruptedException {
        diceMainMenu();
    }

    public void diceMainMenu(){
        boolean isRunning = true;
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
                    startGame();
                    break;
                case 2:
                    BalanceManager.showBalance(currentPlayer.getPlayerAccount());
                    break;
                case 3:
                    removePlayer(currentPlayer);
                    isRunning = false;
                    break;
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
        boolean isPlaying = true;
        while (isPlaying) {
            console.println(printBetMenu());

        }
    }

    public String printBetMenu() {
        boolean isBetting = true;
        sb.setLength(0);
        while (isBetting) {
            Integer choice = console.getIntegerInput(sb
                    .append("+-------------------------------+\n")
                    .append("|          NUMBER GUESS         |\n")
                    .append("+-------------------------------+\n")
                    .append("|  1. Make Bet                  |\n")
                    .append("|  2. Check balance             |\n")
                    .append("|  3. Exit Game                 |\n")
                    .append("+-------------------------------+\n")
                    .append("SELECT A NUMBER: ")
                    .toString());
            switch (choice) {
                case 1:
                    //BettingPayout.takeBet(minBet, maxBet, currentPlayer.getPlayerAccount().getBalance());
                    break;
                case 2:
                    BalanceManager.showBalance(currentPlayer.getPlayerAccount());
                    break;
                case 3:
                    isBetting = false;
                    break;
                default:
                    console.println("Pick a viable choice!");
                    break;
            }

            return sb.toString();
        }
        return sb.toString();
    }
}