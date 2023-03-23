package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;

public class BlackJackPlayer extends Player implements GamblingPlayer {

    public BlackJackPlayer(CasinoAccount playerAccount) {
        super(playerAccount);
    }


    @Override
    public Double checkBalance() {
        return null;
    }

}
