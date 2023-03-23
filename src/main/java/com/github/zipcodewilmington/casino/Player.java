package com.github.zipcodewilmington.casino;

public abstract class Player {
    private CasinoAccount playerAccount;

    public Player(CasinoAccount playerAccount){
        this.playerAccount = playerAccount;
    }

    protected abstract void run(); // to be inherited from all subclass games

    protected abstract Double checkBalance();


}
