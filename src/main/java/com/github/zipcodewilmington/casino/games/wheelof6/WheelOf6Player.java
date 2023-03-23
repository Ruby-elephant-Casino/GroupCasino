package com.github.zipcodewilmington.casino.games.wheelof6;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;

public class WheelOf6Player extends Player implements GamblingPlayer {
    public WheelOf6Player(CasinoAccount playerAccount) {
        super(playerAccount);
    }

    @Override
    protected void run() {

    }

    @Override
    protected Double checkBalance() {
        return null;
    }

    @Override
    protected Double exitGame() {
        return null;
    }

    @Override
    protected Double startGame() {
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
