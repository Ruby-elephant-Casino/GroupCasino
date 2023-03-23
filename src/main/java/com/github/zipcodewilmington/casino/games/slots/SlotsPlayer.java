package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsPlayer extends Player implements GamblingPlayer {
    public SlotsPlayer(CasinoAccount playerAccount) {
        super(playerAccount);
    }



    @Override
    protected Double checkBalance() {
        return null;
    }



    @Override
    public void makeBet() {

    }

    @Override
    public void checkBet() {

    }

    @Override
    public boolean bettable() {
        return false;
    }
}