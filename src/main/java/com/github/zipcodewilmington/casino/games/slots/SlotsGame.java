package com.github.zipcodewilmington.casino.games.slots;
import com.github.zipcodewilmington.casino.BettingPayout;
import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.Random;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame extends Game {
    SlotsPlayer currentPlayer;
    private final String[] outcome = {"ZCW", "Bar", "cherry", "7", "coal", "bunny", "$$$"};
    int a, b, c, d, e, f, g, h, j;
    String x,x1, x2, y, y1, y2, z, z1, z2;
    Random rand;
    int minBet = 5;
    int maxBet = 100;
    BettingPayout bettingPayout = new BettingPayout(minBet, maxBet);
    private final IOConsole console = new IOConsole(AnsiColor.PURPLE);
    public int getSlotRoll() {
        return rand.nextInt(outcome.length);
    }
    public String pullSlots() throws InterruptedException {
        rand = new Random();
        int rollCount = 200;
        for (int i = 0; i < rollCount; i++) {
            a = getSlotRoll();
//            b = getSlotRoll();
//            c = getSlotRoll();
            d = getSlotRoll();
//            e = getSlotRoll();
//            f = getSlotRoll();
            g = getSlotRoll();
//            h = getSlotRoll();
//            j = getSlotRoll();
            x = outcome[a];
//            x1 = outcome[b];
//            x2 = outcome[c];
            y = outcome[d];
//            y1 = outcome[e];
//            y2 = outcome[f];
            z = outcome[g];
//            z1 = outcome[h];
//            z2 = outcome[j];
            System.out.printf("[  " + "  %s  " + "  :  " + "  %s  " + "  :  " + "  %s  " +   "]\r", x,y,z);
            Thread.sleep(120);
        }
        return x + y + z;
    }
    @Override
    public void remove(Player player) {
    }
    public void run() {
        boolean inSlots = true;
        console.println(welcomeSlots());
        Double money = console.getDoubleInput("Please enter money into slot machine!");
        currentPlayer.getPlayerAccount().withdraw(money);
        if (bettingPayout.checkBet(money)) {
            String pullLever = console.getStringInput("adsf");
            try {
                String pullSlot = pullSlots();
                console.println(welcomeSlots());
                while (inSlots) {

                }

            } catch (InterruptedException ex) {
                //throw new RuntimeException(ex);
            }
    }
    }

    private String welcomeSlots(){
        return new StringBuilder()
                .append("+-------------------------------+\n")
                .append("Ruby Slots! Are you a Winner?\n")
                .append("+-------------------------------+\n")
                .toString();
    }
    private String slotsMenu(){
        return null;//console.getStringInput(new StringBuilder()

    }
    @Override
    public Player add(Player player) {
        this.currentPlayer = (SlotsPlayer)player;
        return currentPlayer;
    }

    @Override
    public Player removePlayer(Player player) {
        return null;
    }

    @Override
    public void startGame() {

    }
}
