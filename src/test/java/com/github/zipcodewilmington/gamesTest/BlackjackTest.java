package com.github.zipcodewilmington.gamesTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;
import com.github.zipcodewilmington.casino.gameTools.Dealer;
import com.github.zipcodewilmington.casino.gameTools.Rank;
import com.github.zipcodewilmington.casino.gameTools.Suit;
import com.github.zipcodewilmington.casino.games.blackjack.BlackJackGame;
import com.github.zipcodewilmington.casino.games.blackjack.BlackJackPlayer;
import com.github.zipcodewilmington.utils.Card;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;


public class BlackjackTest {
    private BlackJackPlayer player;
    private Dealer dealer;
    private CardDeck deck;
    private int money;
    @BeforeEach
    public void setUp() {
        player = new BlackJackPlayer("Player", new CasinoAccount());
        dealer = new Dealer();
        deck = new CardDeck();
        money = 100;
    }

    @Test
    public void testStartGame_playerWins() {
        // Arrange
        deck.shuffle();
        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.HEARTS, Rank.KING));
        dealer.addCard(new Card(Suit.CLUBS, Rank.SIX));
        dealer.addCard(new Card(Suit.CLUBS, Rank.FIVE));
        InputStream input = new ByteArrayInputStream("1".getBytes());
        System.setIn(input);

        // Act
        player.startGame(deck, dealer, money);

        // Assert
        assertTrue(player.getPlayerAccount().getBalance() > 1000);
    }

    @Test
    public void testStartGame_playerLoses() {
        // Arrange
        deck.shuffle();
        player.addCard(new Card(Suit.HEARTS, Rank.KING));
        player.addCard(new Card(Suit.HEARTS, Rank.TWO));
        dealer.addCard(new Card(Suit.CLUBS, Rank.SIX));
        dealer.addCard(new Card(Suit.CLUBS, Rank.FIVE));
        InputStream input = new ByteArrayInputStream("2".getBytes());
        System.setIn(input);

        // Act
        player.startGame(deck, dealer, money);

        // Assert
        assertTrue(player.getPlayerAccount().getBalance() < 1000);
    }
}
