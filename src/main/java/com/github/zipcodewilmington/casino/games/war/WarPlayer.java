package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;

public class WarPlayer extends Player {

    public WarPlayer(CasinoAccount playerAccount) {
        super(playerAccount);
    }



    @Override
    protected Double checkBalance() {
        return null;
    }


}
