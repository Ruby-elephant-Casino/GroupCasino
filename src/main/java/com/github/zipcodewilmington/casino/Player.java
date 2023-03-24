package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.utils.Card;

public abstract class Player {
    private CasinoAccount playerAccount;

    public Player(CasinoAccount playerAccount){
        this.playerAccount = playerAccount;
    }

    protected abstract void run(); // to be inherited from all subclass games

    protected abstract Double checkBalance();

    protected abstract Double exitGame();

    protected abstract Double startGame();

    public abstract boolean hasBlackjack();
}
