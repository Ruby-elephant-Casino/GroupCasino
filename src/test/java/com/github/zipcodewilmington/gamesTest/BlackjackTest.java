package com.github.zipcodewilmington.gamesTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;
import com.github.zipcodewilmington.casino.gameTools.Dealer;
import com.github.zipcodewilmington.casino.gameTools.Rank;
import com.github.zipcodewilmington.casino.gameTools.Suit;
import com.github.zipcodewilmington.casino.games.blackjack.BlackJackGame;
import com.github.zipcodewilmington.casino.games.blackjack.BlackJackPlayer;
import com.github.zipcodewilmington.utils.Card;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.Assert.*;

public class BlackjackTest {

//
////    @Test
////    public void testGetPlayerMove() {
////        ByteArrayOutputStream out = new ByteArrayOutputStream();
////        ByteArrayInputStream in = new ByteArrayInputStream("hit\n".getBytes());
////        System.setOut(new PrintStream(out));
////        System.setIn(in);
////
////        String expected = "hit";
////        String actual = GetPlayerMove();
////
////        assertEquals(expected, actual);
////        assertEquals("Enter move (hit/stand): Please try again.\n", out.toString());
////    }
////
////    private String GetPlayerMove() {
////        return null;
////    }
//
//
//    @Test
//    public void testDealerTurn() {
//        CardDeck deck = new CardDeck();
//        Dealer dealer = new Dealer();
//        dealer.addCard(new Card(Suit.CLUBS, Rank.KING));
//        dealer.addCard(new Card(Suit.HEARTS, Rank.SEVEN));
//
//        dealerTurn(dealer, deck);
//
//        int expectedValue = dealer.getValue();
//        assertTrue(expectedValue <= 17);
//
//        if (expectedValue > 21) {
//            assertTrue(dealer.busted());
//        } else {
//            assertFalse(dealer.busted());
//        }
//    }
//
//    private void dealerTurn(Dealer dealer, CardDeck deck) {
//
//    }
//
//    @Test
//    public void testPlayerTurn() {
//        CardDeck deck = new CardDeck();
//        BlackJackPlayer player = new BlackJackPlayer(new CasinoAccount());
//        player.addCard(new Card(Suit.DIAMONDS, Rank.KING));
//        player.addCard(new Card(Suit.HEARTS, Rank.TEN));
//
//        boolean busted = playerTurn(player, deck);
//
//        int expectedValue = player.getHandValue();
//        assertTrue(expectedValue <= 21);
//
//        if (busted) {
//            assertTrue(player.busted());
//        } else {
//            assertFalse(player.busted());
//        }
//    }
//
//    private boolean playerTurn(BlackJackPlayer player, CardDeck deck) {
//        return false;
//    }
//
//
//    @Test
//    public void testPlayerWins() {
//        // create a player and add two cards to their hand
//        BlackJackPlayer player = new BlackJackPlayer(new CasinoAccount());
//        player.addCard(new Card(Suit.DIAMONDS, Rank.KING));
//        player.addCard(new Card(Suit.HEARTS, Rank.FIVE));
//
//        // create a dealer and add two cards to their hand
//        Dealer dealer = new Dealer();
//        dealer.addCard(new Card(Suit.SPADES, Rank.ACE));
//        dealer.addCard(new Card(Suit.CLUBS, Rank.SEVEN));
//
//        // calculate the expected result based on the hands
//        boolean expected = player.getHandValue() > dealer.getHandValue() && !player.busted() && !dealer.busted();
//
//        // call the method and check the result
//        boolean actual = playerWins(player, dealer);
//        assertEquals(expected, actual);
//    }
//
//    private boolean playerWins(BlackJackPlayer player, Dealer dealer) {
//        return false;
//    }
//
////    @Test
////    public void testFindWinner() {
////        DealerPlayer dealer = new DealerPlayer();
////        dealer.addCard(new Card(Card.Rank.KING, Card.Suit.HEARTS));
////        dealer.addCard(new Card(Card.Rank.THREE, Card.Suit.CLUBS));
////        dealer.addCard(new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS));
////
////        DealerPlayer player = new DealerPlayer();
////        player.addCard(new Card(Card.Rank.QUEEN, Card.Suit.SPADES));
////        player.addCard(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
////
////        int bet = 10;
////
////        double expected = bet;
////        double actual = findWinner(dealer, player, bet);
////
////        assertEquals(expected, actual, 0.01);
////    }
//
//    @Test
//    void testPlayerMove() {
//        BlackJackGame game = new BlackJackGame();
//        String hit = "hit";
//        String stand = "stand";
//
//        assertEquals(hit, game.getPlayerMove("hit"));
//        assertEquals(stand, game.getPlayerMove("stand"));
//    }
//
//    @Test
//    void testDealerTurn() {
//        BlackJackGame game = new BlackJackGame();
//        Dealer dealer = new Dealer();
//        CardDeck deck = new CardDeck();
//        deck.shuffle();
//        dealer.addCard(deck.deal());
//        dealer.addCard(deck.deal());
//
//        game.dealerTurn(dealer, deck);
//        assertFalse(dealer.handIsEmpty());
//    }

    private BlackJackGame game;
    private Player player;
    private Dealer dealer;
    private CardDeck deck;

    @Before
    public void setup() {
        game = new BlackJackGame();
        player = new BlackJackPlayer(new CasinoAccount());
        dealer = new Dealer();
        deck = new CardDeck();
    }
    private void provideInput(String data) {
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            System.setIn(stdin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getConsoleOutput(Runnable runnable) {
        // Create a new ByteArrayOutputStream to capture the console output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Save the current System.out
        PrintStream oldOut = System.out;
        // Set the System.out to use the ByteArrayOutputStream
        System.setOut(new PrintStream(baos));

        try {
            // Run the code that writes to the console
            runnable.run();
        } finally {
            // Reset System.out to the original value
            System.setOut(oldOut);
        }

        // Convert the captured console output to a string and return it
        return baos.toString();
    }

    @Test
    public void initializeGameTest() {
        // Arrange
        String expectedOutput = "Welcome to Blackjack!\n\n  BLACKJACK RULES: \n\t-Each player is dealt 2 cards. The dealer is dealt 2 cards with one face-up and one face-down.\n\t-Cards are equal to their value with face cards being 10 and an Ace being 1 or 11.\n\t-The players cards are added up for their total.\n\t-Players “Hit” to gain another card from the deck. Players “Stay” to keep their current card total.\n\t-Dealer “Hits” until they equal or exceed 17.\n\t-The goal is to have a higher card total than the dealer without going over 21.\n\t-If the player total equals the dealer total, it is a “Push” and the hand ends.\n\t-Players win their bet if they beat the dealer. Players win 1.5x their bet if they get “Blackjack” which is 21.\n\n\n";

        // Act
        String consoleOutput = getConsoleOutput(game::initializeGame);

        // Assert
        Assert.assertEquals(expectedOutput, consoleOutput);
    }

    @Test
    public void getPlayerMoveTest() {
        // Arrange
        provideInput("hit\n");

        // Act
        String move = game.getPlayerMove();

        // Assert
        Assert.assertEquals("hit", move);
    }

//    @Test
//    public void playerTurnTest() {
//        // Arrange
//        player.addCard(deck.deal());
//        player.addCard(deck.deal());
//
//        provideInput("hit\n");
//        provideInput("stand\n");
//
//        // Act
//        boolean result = game.playerTurn((BlackJackPlayer) player, deck);
//
//        // Assert
//        Assert.assertFalse(result);
//    }
//
//    @Test
//    public void dealerTurnTest() {
//        // Arrange
//        dealer.addCard(deck.deal());
//        dealer.addCard(deck.deal());
//
//        // Act
//        game.dealerTurn(dealer, deck);
//
//        // Assert
//        Assert.assertTrue(dealer.getValue() >= 17);
//    }


}