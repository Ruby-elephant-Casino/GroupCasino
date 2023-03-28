package com.github.zipcodewilmington.gamesTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.Rank;
import com.github.zipcodewilmington.casino.gameTools.Suit;
import com.github.zipcodewilmington.casino.games.war.WarGame;
import com.github.zipcodewilmington.casino.games.war.WarPlayer;


import com.github.zipcodewilmington.casino.games.war.WarGame;
import com.github.zipcodewilmington.casino.games.war.WarPlayer;

import com.github.zipcodewilmington.casino.games.wheelof6.WheelOfSixPlayer;
import com.github.zipcodewilmington.utils.Card;
import org.junit.Assert;
import org.junit.Test;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Queue;

import static org.junit.Assert.*;

public class WarTest {

    public void testAdd() {
        WarPlayer warPlayer = new WarPlayer(new CasinoAccount());
        WarGame game = new WarGame();
        game.add(warPlayer);
        assertNotNull(game.getCurrentPlayer());
    }

    @Test
    public void compareCardsTestDealerWins(){
        //given
        //new War instance
        WarGame war = new WarGame();
        //two equal cards
        WarPlayer currentPlayer = new WarPlayer(null);
        WarPlayer dealerPlayer = new WarPlayer(null);
        Card playerCard = new Card(Suit.DIAMONDS, Rank.FOUR);
        Card dealerCard = new Card(Suit.DIAMONDS, Rank.FIVE);
        ArrayList<Card> potentialWinnings = new ArrayList<>();
        war.dealCards(currentPlayer, dealerPlayer);
        //when
        currentPlayer.setCurrentCard(playerCard);
        dealerPlayer.setCurrentCard(dealerCard);
        boolean expected = false;

        //then
        boolean actual = war.compareCards(currentPlayer, dealerPlayer, potentialWinnings);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void compareCardsTestPlayerWins(){
        //given
        //new War instance
        WarGame war = new WarGame();
        //two equal cards
        WarPlayer currentPlayer = new WarPlayer(null);
        WarPlayer dealerPlayer = new WarPlayer(null);
        Card playerCard = new Card(Suit.DIAMONDS, Rank.FIVE);
        Card dealerCard = new Card(Suit.DIAMONDS, Rank.FOUR);
        ArrayList<Card> potentialWinnings = new ArrayList<>();
        war.dealCards(currentPlayer, dealerPlayer);
        //when
        currentPlayer.setCurrentCard(playerCard);
        dealerPlayer.setCurrentCard(dealerCard);
        boolean expected = true;

        //then
        boolean actual = war.compareCards(currentPlayer, dealerPlayer, potentialWinnings);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addPlayerWinningsTest() {
        //given
        WarGame war = new WarGame();
        boolean playerWins = true;
        WarPlayer currentPlayer = new WarPlayer(null);
        WarPlayer dealerPlayer = new WarPlayer(null);
        ArrayList<Card> potentialWinnings = new ArrayList<>();
        //when
        boolean expected = true;
        //then
        boolean actual = war.addWinnings(playerWins, currentPlayer,dealerPlayer,potentialWinnings);
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void addDealerWinningsTest() {
        //given
        WarGame war = new WarGame();
        boolean playerWins = false;
        WarPlayer currentPlayer = new WarPlayer(null);
        WarPlayer dealerPlayer = new WarPlayer(null);
        ArrayList<Card> potentialWinnings = new ArrayList<>();
        //when
        boolean expected = false;
        //then
        boolean actual = war.addWinnings(playerWins, currentPlayer,dealerPlayer,potentialWinnings);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void printPlayerCardAmount() {
        //given
        WarGame war = new WarGame();
        //two equal cards
        WarPlayer currentPlayer = new WarPlayer(null);
        WarPlayer dealerPlayer = new WarPlayer(null);
        war.dealCards(currentPlayer, dealerPlayer);
        //when
        String expected = "\n\nPlayer has 26 cards in their deck.\n";
        //then
        String actual = war.printPlayerCardAmount(currentPlayer);
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void printWinningsTest() {
        //given
        //when
        //then
    }

    @Test
    public void dealCardsTest() {
        //given
        WarGame war = new WarGame();
        WarPlayer currentPlayer = new WarPlayer(null);
        WarPlayer dealerPlayer = new WarPlayer(null);
        //when
        int expected = 26;
        //then
        int actual = war.dealCards(currentPlayer, dealerPlayer);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testConstructor() {
        CasinoAccount account = new CasinoAccount();
        WarPlayer player = new WarPlayer(account);
        assertEquals(account, player.getPlayerAccount());
    }


}
