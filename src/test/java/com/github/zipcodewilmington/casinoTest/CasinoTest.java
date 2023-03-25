package com.github.zipcodewilmington.casinoTest;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

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
        Mockito.when(accountManager.askForNewAccountName()).thenReturn(accName);
        Mockito.when(accountManager.askForNewPassword()).thenReturn(password);
        Mockito.when(accountManager.createAccount(Mockito.anyString(),Mockito.anyString())).thenReturn(expected);
        CasinoAccount acc = casino.createNewAccount();

        // Then
        Assert.assertEquals(accName,acc.getName());
        Assert.assertEquals(password,acc.getPassword());
        Assert.assertEquals(expected,acc);
    }

    @Test
    public void testWelcomeMessage(){
        // Given
        Casino casino = new Casino();
        String expected = "Welcome to the Arcade Dashboard!\nFrom here, you can select any of the following options:";

        // When
        String actual = casino.welcomeMessage();

        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testLobbyMenu(){
        // Given
        Casino casino = new Casino();
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);
        Integer expected = 1;

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(1);
        Integer actual = casino.lobbyMenu();

        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGameMenu(){
        // Given
        Casino casino = new Casino();
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);
        Integer expected = 1;

        // When
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(1);
        Integer actual = casino.gameMenu();

        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGetAccountNameForLogIn(){
        // Given
        Casino casino = new Casino();
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);
        String expected = "exit";

        // When
        Mockito.when(console.getStringInput(Mockito.anyString())).thenReturn(expected);
        String actual = casino.getAccountNameForLogIn();

        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGetAccountNameForLogIn2(){
        // Given
        Casino casino = new Casino();
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);
        String expected = "whitney"; // make sure accountDB.txt has this info

        // When
        Mockito.when(console.getStringInput(Mockito.anyString())).thenReturn(expected);
        String actual = casino.getAccountNameForLogIn();

        // Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testGetAccountNameForLogIn3(){
        // Given
        Casino casino = new Casino();
        CasinoAccountManager accountManager = Mockito.mock(CasinoAccountManager.class);
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock account not found then exit
        Mockito.when(console.getStringInput(Mockito.anyString())).thenReturn("invalid_account_name","exit");
        Mockito.when(accountManager.checkAccountName(Mockito.anyString())).thenReturn(false);

        // When
        String actual = casino.getAccountNameForLogIn();

        // Then
        Assert.assertEquals("exit", actual);
    }

    @Test
    public void testGetPasswordForLogIn(){
        // Given
        Casino casino = new Casino();
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // make sure accountsDB.txt contains this information
        // test will fail otherwise
        String accountName = "whitney";
        String password = "whitney";

        // mock valid password
        Mockito.when(console.getStringInput(Mockito.anyString())).thenReturn(password);

        // When
        String actual = casino.getPasswordForLogIn(accountName);

        // Then
        Assert.assertEquals(password, actual);
    }

    @Test
    public void testGetPasswordForLogIn2(){
        // Given
        Casino casino = new Casino();
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        //make sure accountsDB.txt contains this information
        String accountName = "whitney";
        String expected = "exit";

        // mock password to be "exit"
        Mockito.when(console.getStringInput(Mockito.anyString())).thenReturn(expected);

        // When
        String actual = casino.getPasswordForLogIn(accountName);

        // Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testGetPasswordForLogIn3(){
        // Given
        Casino casino = new Casino();
        CasinoAccountManager accountManager = Mockito.mock(CasinoAccountManager.class);
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        //make sure accountsDB.txt contains this information
        String accountName = "whitney";
        String expected = "exit";

        // mock invalid password then "exit
        Mockito.when(console.getStringInput(Mockito.anyString())).thenReturn("",expected);

        // When
        String actual = casino.getPasswordForLogIn(accountName);

        // Then
        Assert.assertEquals(expected, actual);
    }


}
