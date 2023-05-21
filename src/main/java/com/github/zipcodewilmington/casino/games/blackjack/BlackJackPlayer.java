package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;
import com.github.zipcodewilmington.casino.gameTools.Dealer;
import com.github.zipcodewilmington.utils.Card;

import java.util.List;

public class BlackJackPlayer extends Player implements GamblingPlayer {
    private List<Card> hand;

    public BlackJackPlayer(String player, CasinoAccount playerAccount) {
        super(playerAccount);
    }


    public void hit(Card card) {
        hand.add(card);
    }

    @Override
    public Double checkBalance() {
        return null;
    }



    public boolean hasBlackjack() {
        return getHandValue() > 21;
    }


    public boolean busted() {

        return false;
    }

    public void addCard(Card c) {

    }

    public int getHandValue() {

        return 0;
    }

    public static int getValue() {

        return 1;
    }

    public void startGame(CardDeck deck, Dealer dealer, int money) {


    }
}
