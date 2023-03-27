package com.github.zipcodewilmington.casinoTest;

import com.github.zipcodewilmington.casino.BalanceManager;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;


public class BalanceManagerTest {
    @Test
    public void testSuccessDeposit(){
        // Given
        CasinoAccount ca = new CasinoAccount();
        Mockito.spy(BalanceManager.class);
        IOConsole console = Mockito.mock(IOConsole.class);
        BalanceManager.setConsole(console);
        Double expected  = 5.0;

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(1,3);
        Mockito.when(console.getDoubleInput(Mockito.anyString())).thenReturn(expected);
        BalanceManager.showBalance(ca);

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
        verify(console, times(1)).getDoubleInput(Mockito.anyString());
        Assert.assertEquals(expected,ca.getBalance());
    }

    @Test
    public void testFailDeposit(){
        // Given
        CasinoAccount ca = new CasinoAccount();
        Mockito.spy(BalanceManager.class);
        IOConsole console = Mockito.mock(IOConsole.class);
        BalanceManager.setConsole(console);
        Double expected  = 0.0;

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(1,3);
        Mockito.when(console.getDoubleInput(Mockito.anyString())).thenReturn(30000.0);
        BalanceManager.showBalance(ca);

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
        verify(console, times(1)).getDoubleInput(Mockito.anyString());
        Assert.assertEquals(expected,ca.getBalance());
    }

    @Test
    public void testSuccessWithdraw(){
        // Given
        CasinoAccount ca = new CasinoAccount("","",100.0);
        Mockito.spy(BalanceManager.class);
        IOConsole console = Mockito.mock(IOConsole.class);
        BalanceManager.setConsole(console);
        Double expected  = 50.0;

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(2,3);
        Mockito.when(console.getDoubleInput(Mockito.anyString())).thenReturn(expected);
        BalanceManager.showBalance(ca);

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
        verify(console, times(1)).getDoubleInput(Mockito.anyString());
        Assert.assertEquals(expected,ca.getBalance());
    }

    @Test
    public void testFailWithdraw(){
        // Given
        CasinoAccount ca = new CasinoAccount("","",100.0);
        Mockito.spy(BalanceManager.class);
        IOConsole console = Mockito.mock(IOConsole.class);
        BalanceManager.setConsole(console);
        Double expected  = 100.0;

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(2,3);
        Mockito.when(console.getDoubleInput(Mockito.anyString())).thenReturn(200.0);
        BalanceManager.showBalance(ca);

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
        verify(console, times(1)).getDoubleInput(Mockito.anyString());
        Assert.assertEquals(expected,ca.getBalance());
    }
    @Test
    public void testFailWithdraw2(){
        // Given
        CasinoAccount ca = new CasinoAccount("","",100.0);
        Mockito.spy(BalanceManager.class);
        IOConsole console = Mockito.mock(IOConsole.class);
        BalanceManager.setConsole(console);
        Double expected  = 100.0;

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(2,3);
        Mockito.when(console.getDoubleInput(Mockito.anyString())).thenReturn(-100.0);
        BalanceManager.showBalance(ca);

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
        verify(console, times(1)).getDoubleInput(Mockito.anyString());
        Assert.assertEquals(expected,ca.getBalance());
    }

    @Test
    public void testDefaultCase(){
        // Given
        CasinoAccount ca = new CasinoAccount();
        Mockito.spy(BalanceManager.class);
        IOConsole console = Mockito.mock(IOConsole.class);
        BalanceManager.setConsole(console);
        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(5,3);
        BalanceManager.showBalance(ca);

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }
}
