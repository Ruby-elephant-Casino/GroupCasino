package com.github.zipcodewilmington.gamesTest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
public class SlotsTest {
    @Test
    public void testWinOrLose(){
        // Given
        double bet = 20;
        int a = 1;
        int b = 2;
        int c = 2;
        SlotsGame slotsGame = new SlotsGame();
        SlotsPlayer player = new SlotsPlayer(new CasinoAccount());
        slotsGame.add(player);
        Double expectedWin = (120.0);
        // When
        double actual = slotsGame.winOrLose(bet, a,b,c);
        // Then
        Assert.assertEquals(expectedWin, actual, 0.2);
    }
    @Test
    public void testAddPlayer(){
        // Given
        SlotsPlayer player = new SlotsPlayer(new CasinoAccount());
        SlotsGame game = new SlotsGame();
        // When
        game.add(player);
        // Then
        Assert.assertNotNull(game.getCurrentPlayer());
    }

    @Test
    public void testRemovePlayer(){
        // Given
        SlotsPlayer player = new SlotsPlayer(new CasinoAccount());
        SlotsGame game = new SlotsGame();
        // When
        game.add(player);
        game.remove(player);
        // Then
        Assert.assertNull(game.getCurrentPlayer());

    }


}
