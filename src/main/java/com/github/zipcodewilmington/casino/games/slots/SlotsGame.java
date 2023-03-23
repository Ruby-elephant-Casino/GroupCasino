package com.github.zipcodewilmington.casino.games.slots;
import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;

import java.util.Random;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame extends Game {
    Player currentPlayer;
    private final String[] outcome = {"ZCW", "Bar", "cherry", "7", "coal", "bunny", "$"};
    int a, b, c, d, e, f, g, h, j;
    String x,x1, x2, y, y1, y2, z, z1, z2;
    Random rand;
    public int getSlotRoll() {
        return rand.nextInt(outcome.length);
    }
    public String pullSlots() throws InterruptedException {
        rand = new Random();
        int rollCount = 200;
        for (int i = 0; i < rollCount; i++) {
            a = getSlotRoll();
            b = getSlotRoll();
            c = getSlotRoll();
            d = getSlotRoll();
            e = getSlotRoll();
            f = getSlotRoll();
            g = getSlotRoll();
            h = getSlotRoll();
            j = getSlotRoll();
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
        return null;
    }

    @Override
    public void remove(Player player) {

    }

    public void run() {
        try{
            String pullSlot = pullSlots();

        } catch (InterruptedException ex) {
            //throw new RuntimeException(ex);
        }
    }

    @Override
    public Player add(Player player) {
        this.currentPlayer = player;
        return currentPlayer;
    }

    @Override
    public Player removePlayer(Player player) {
        return null;
    }
}
