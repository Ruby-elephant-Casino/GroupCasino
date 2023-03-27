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

    // NEED TO FINISH 2 TESTS BELOW FOR 100% COVERAGE


    @Test
    public void testBetPayout(){ // methods with 4 params

    }

    @Test
    public void testBetPayout2(){ // method with 2 params

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

    @Test
    public void testTakeBet0(){
        // Given
        BettingPayout bp = new BettingPayout(1,1);
        IOConsole console = Mockito.mock(IOConsole.class);
        bp.setConsole(console);
        double expected = 0.0;

        // When
        Mockito.when(console.getDoubleInput(Mockito.anyString())).thenReturn(0.0);
        double actual = bp.takeBet(1,1,1);

        // Then
        verify(console, times(1)).getDoubleInput(Mockito.anyString());
        Assert.assertEquals(expected,actual,0.001);
    }
}
