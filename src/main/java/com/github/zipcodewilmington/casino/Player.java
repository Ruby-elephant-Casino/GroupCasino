package com.github.zipcodewilmington.casino;

public abstract class Player {
    private CasinoAccount playerAccount;

    public CasinoAccount getPlayerAccount() {
        return playerAccount;
    }

    public void setPlayerAccount(CasinoAccount playerAccount) {
        this.playerAccount = playerAccount;
    }

    public Player(CasinoAccount playerAccount){
        this.playerAccount = playerAccount;
    }

    protected abstract Double checkBalance();

}
