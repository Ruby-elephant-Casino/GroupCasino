package com.github.zipcodewilmington.casinoTest;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CasinoTest {
    @Test
    public void testConstructor(){
        // Given
        Casino casino = new Casino();

        // When
        // Then
        Assert.assertNotNull(casino.getAccountManager());
    }

    @Test
    public void testSetAccountManager(){
        // Given
        Casino casino = new Casino();
        CasinoAccountManager expected = new CasinoAccountManager();

        // When
        casino.setAccountManager(expected);
        // Then
        Assert.assertEquals(expected,casino.getAccountManager());
    }

    @Test
    public void testCreateNewAccount(){
        // Given
        Casino casino = new Casino();
        CasinoAccountManager accountManager = Mockito.mock(CasinoAccountManager.class);
        casino.setAccountManager(accountManager);
        String accName = "newAccount";
        String password = "newPassword";
        CasinoAccount expected = new CasinoAccount(accName,password);

        // When
        Mockito.when(accountManager.askForAccountName()).thenReturn(accName);
        Mockito.when(accountManager.askForPassword()).thenReturn(password);
        Mockito.when(accountManager.createAccount(Mockito.anyString(),Mockito.anyString())).thenReturn(expected);
        CasinoAccount acc = casino.createNewAccount();

        // Then
        Assert.assertEquals(accName,acc.getName());
        Assert.assertEquals(password,acc.getPassword());
        Assert.assertEquals(expected,acc);
    }
}
