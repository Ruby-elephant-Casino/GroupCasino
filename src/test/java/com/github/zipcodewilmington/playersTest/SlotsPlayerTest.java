package com.github.zipcodewilmington.playersTest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Assert;
import org.junit.Test;

public class SlotsPlayerTest {
    @Test
    public void testSlotsPlayerConstructor(){
        // Given
        CasinoAccount account = new CasinoAccount();
        SlotsPlayer player = new SlotsPlayer(account);
        // When
        // Then
        Assert.assertEquals(account, player.getPlayerAccount());

    }
}
