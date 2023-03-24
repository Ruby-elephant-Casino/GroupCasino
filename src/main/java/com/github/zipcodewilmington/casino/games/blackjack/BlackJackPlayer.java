package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.utils.Card;

public class BlackJackPlayer extends Player implements GamblingPlayer {

    public BlackJackPlayer(CasinoAccount playerAccount) {
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
    public boolean hasBlackjack() {
        return false;
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

    public boolean busted() {
        return false;
    }

    public void addCard(Card c) {

    }

    public int getHandValue() {
        return 0;
    }

    public int getValue() {
        return 1;
    }
}
