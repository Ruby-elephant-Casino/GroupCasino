package com.github.zipcodewilmington.casinoTest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.zipcodewilmington.casino.BettingPayout;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class BettingPayoutTest {

    @Test
    public void testConstructor(){

    }

    @Test
    public void testCheckMax(){

    }

    @Test
    public void testCheckMin(){

    }

    @Test
    public void testCheckBetAgainstBalance(){

    }

    @Test
    public void testBetPayout(){

    }

    @Test
    public void testBetPayout2(){

    }

    @Test
    public void testCheckBet(){

    }

    @Test
    public void testTakeBet(){
        // Given
        int min = 5;
        int max = 1000;
        double bal = 10000.0;
        BettingPayout bp = new BettingPayout(5,1000);
        IOConsole console = Mockito.mock(IOConsole.class);
        bp.setConsole(console);
        double expected = 500.0;


        // When
        Mockito.when(console.getDoubleInput(Mockito.anyString())).thenReturn(20000.0,2000.0,expected);
        double actual = bp.takeBet(min,max,bal);

        // Then
        verify(console, times(3)).getDoubleInput(Mockito.anyString());
        Assert.assertEquals(expected,actual,0.001);
    }
}
