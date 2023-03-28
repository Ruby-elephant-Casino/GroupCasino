package com.github.zipcodewilmington.gamesTest;

import com.github.zipcodewilmington.casino.BalanceManager;
import com.github.zipcodewilmington.casino.BettingPayout;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;

import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NumberGuessTest {

    // COMPLETE TESTS BELOW WILL INCREASE CODE COVERAGE

    //@Test
    public void testCompareDice() {

    }
    //@Test
    public void testRollDice() {

    }
    //@Test
    public void testPrintComparison() {

    }

    // ---------------------------------------------------------
    @Test
    public void testAdd() { // METHOD FROM FATIMA
        // Arrange
        NumberGuessPlayer player = new NumberGuessPlayer(new CasinoAccount());
        NumberGuessGame game = new NumberGuessGame();
        game.add(player);
        assertNotNull(game.getCurrentPlayer());
    }
    @Test
    public void testRemove() { // METHOD FROM FATIMA
        NumberGuessPlayer player = new NumberGuessPlayer(new CasinoAccount());
        NumberGuessGame game = new NumberGuessGame();
        game.add(player);
        game.remove(player);
        assertNull(game.getCurrentPlayer());
    }

    @Test
    public void testRunThenCheckBalance() throws InterruptedException {
        // Given
        NumberGuessPlayer player = new NumberGuessPlayer(new CasinoAccount());
        NumberGuessGame game = new NumberGuessGame();
        game.add(player);

        // mock behavior of console in NumberGuessGame class
        IOConsole console = Mockito.mock(IOConsole.class);
        game.setConsole(console);

        // mock behavior of console in BalanceManager class
        Mockito.spy(BalanceManager.class);
        IOConsole console2 = Mockito.mock(IOConsole.class);
        BalanceManager.setConsole(console2);

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(2,3);
        Mockito.when(console2.getIntegerInput(Mockito.anyString())).thenReturn(3);
        game.run();

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
        verify(console2, times(1)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testRunThenStartGame() throws InterruptedException {
        // Given
        NumberGuessPlayer player = new NumberGuessPlayer(new CasinoAccount());
        NumberGuessGame game = new NumberGuessGame();
        game.add(player);

        // mock behavior of console in NumberGuessGame class
        IOConsole console = Mockito.mock(IOConsole.class);
        game.setConsole(console);

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(1,4);
        game.run();

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testStartGameAndMakeBet() {
        // Given
        NumberGuessPlayer player = new NumberGuessPlayer(new CasinoAccount());
        NumberGuessGame game = new NumberGuessGame();
        game.add(player);

        // mock behavior of console in NumberGuessGame class
        IOConsole console = Mockito.mock(IOConsole.class);
        game.setConsole(console);

        // mock bettingPayout
        BettingPayout bp = Mockito.mock(BettingPayout.class);
        game.setBettingPayout(bp);

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(1,5);
        Mockito.when(bp.takeBet(Mockito.anyInt(),
                Mockito.anyInt(),
                Mockito.anyDouble()))
                .thenReturn(0.0);
        game.startGame();

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
        verify(bp, times(1)).takeBet(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyDouble());
    }

    @Test
    public void testStartGameAndCheckBalance() {
        // Given
        NumberGuessPlayer player = new NumberGuessPlayer(new CasinoAccount());
        NumberGuessGame game = new NumberGuessGame();
        game.add(player);

        // mock behavior of console in NumberGuessGame class
        IOConsole console = Mockito.mock(IOConsole.class);
        game.setConsole(console);

        // mock behavior of console in BalanceManager class
        Mockito.spy(BalanceManager.class);
        IOConsole console2 = Mockito.mock(IOConsole.class);
        BalanceManager.setConsole(console2);

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(2,4);
        Mockito.when(console2.getIntegerInput(Mockito.anyString())).thenReturn(3);
        game.startGame();

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
        verify(console2, times(1)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testStartGameThenExit() {
        // Given
        NumberGuessPlayer player = new NumberGuessPlayer(new CasinoAccount());
        NumberGuessGame game = new NumberGuessGame();
        game.add(player);

        // mock behavior of console in NumberGuessGame class
        IOConsole console = Mockito.mock(IOConsole.class);
        game.setConsole(console);

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(3);
        game.startGame();

        // Then
        verify(console, times(1)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testGetNumberOfDice() {
        // Given
        NumberGuessPlayer player = new NumberGuessPlayer(new CasinoAccount());
        NumberGuessGame game = new NumberGuessGame();
        game.add(player);
        int expected = 2;

        // mock behavior of console in NumberGuessGame class
        IOConsole console = Mockito.mock(IOConsole.class);
        game.setConsole(console);

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(expected);
        int actual = game.getNumberOfDice();

        // Then
        verify(console, times(1)).getIntegerInput(Mockito.anyString());
        Assert.assertEquals(expected,actual);
    }
}
