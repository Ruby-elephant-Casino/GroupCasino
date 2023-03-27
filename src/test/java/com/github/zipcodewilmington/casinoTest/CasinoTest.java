package com.github.zipcodewilmington.casinoTest;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.BalanceManager;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

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

//    @Test
//    public void testLobbyMenu(){
//        // Given
//        Casino casino = new Casino();
//        IOConsole console = Mockito.mock(IOConsole.class);
//        casino.setConsole(console);
//        Integer expected = 1;
//
//        // When
//        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(1);
//        Integer actual = casino.lobbyMenu();
//
//        // Then
//        Assert.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void testGameMenu(){
//        // Given
//        Casino casino = new Casino();
//        IOConsole console = Mockito.mock(IOConsole.class);
//        casino.setConsole(console);
//        Integer expected = 1;
//
//        // When
//        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(1);
//        Integer actual = casino.gameMenu();
//
//        // Then
//        Assert.assertEquals(expected,actual);
//    }

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
    @Test
    public void testLogin() {
        // Given
        Casino casino = new Casino();
        CasinoAccountManager accountManager = Mockito.mock(CasinoAccountManager.class);
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);
        casino.setAccountManager(accountManager);

        //make sure accountsDB.txt contains this information
        String accountName = "whitney";
        String password = "whitney";
        CasinoAccount ca = new CasinoAccount("","",0.0);


        //
        Mockito.when(console.getStringInput(Mockito.anyString())).thenReturn(accountName,password);
        Mockito.when(accountManager.checkAccountName(Mockito.anyString())).thenReturn(true);
        Mockito.when(accountManager.checkAccount(Mockito.anyString(),Mockito.anyString())).thenReturn(true);
        Mockito.when(accountManager.getAccount(Mockito.anyString(),Mockito.anyString()))
                .thenReturn(ca);

        // When
        casino.login();

        // Then
        Assert.assertEquals(ca,casino.getCurrentAccount());
        verify(console, times(2)).getStringInput(Mockito.anyString());
        verify(accountManager, times(1)).checkAccountName(Mockito.anyString());
        verify(accountManager, times(1)).checkAccount(Mockito.anyString(),Mockito.anyString());
        verify(accountManager, times(1)).getAccount(Mockito.anyString(),Mockito.anyString());

    }

    @Test
    public void testRunWithInputCase1_1() {
        Casino casino = new Casino();
        casino.setCurrentAccount(new CasinoAccount()); // so it goes into else statement
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 1 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(1,7);

        // execute the lobbyMenu() method
        casino.run();

        // getIntegerInput was called 2 time as 1 then 7 was selected and program exited
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testRunWithInputCase1_2() {
        // Given
        Casino casino = new Casino();
        casino.setCurrentAccount(null); // so it goes into if statement and call createNewAccount();
        CasinoAccount acc = new CasinoAccount("name","pass",5.0);

        // Mock Objects
        CasinoAccountManager cam = Mockito.mock(CasinoAccountManager.class);
        IOConsole console = Mockito.mock(IOConsole.class);

        // set mock objects as casino fields
        casino.setAccountManager(cam);
        casino.setConsole(console);

        // mock user input to select menu option 1 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(1,7);

        // Mock cam
        Mockito.when(cam.askForNewAccountName()).thenReturn("");
        Mockito.when(cam.askForNewPassword()).thenReturn("");
        Mockito.when(cam.createAccount(Mockito.anyString(),Mockito.anyString())).thenReturn(acc);

        // execute the lobbyMenu() method
        casino.run();

        // verify number of time method was called
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
        verify(cam, times(1)).askForNewAccountName();
        verify(cam, times(1)).askForNewPassword();
        verify(cam, times(1)).createAccount(Mockito.anyString(),Mockito.anyString());
    }

    @Test
    public void testRunWithInputCase2_1() {
        Casino casino = new Casino();
        casino.setCurrentAccount(new CasinoAccount()); // so it goes into else statement
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 2, 2 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(2,7);

        // execute the lobbyMenu() method
        casino.run();

        // getIntegerInput was called 2 time as 1 then 7 was selected and program exited
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testRunWithInputCase2_2() {
        Casino casino = new Casino();
        casino.setCurrentAccount(null); // so it goes into if statement
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 2, 2 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(2,7);
        Mockito.when(console.getStringInput(Mockito.anyString())).thenReturn("exit","exit");

        // execute the lobbyMenu() method
        casino.run();

        // getIntegerInput was called 2 time as 1 then 7 was selected and program exited
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testRunWithInputCase3_1() {
        Casino casino = new Casino();
        casino.setCurrentAccount(null); // so it goes into else statement => errorConsole -> exit
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 3 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(3,7);

        // execute the lobbyMenu() method
        casino.run();

        // getIntegerInput was called 2 time as 1 then 7 was selected and program exited
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testRunWithInputCase3_2() {
        Casino casino = new Casino();

        // make sure  currentAccount is not null
        casino.setCurrentAccount(new CasinoAccount()); // so it goes into if statement => BalanceManager.showBalance(currentAccount);

        // fake console in casino class
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // fake console in BalanceManager class
        IOConsole console2 = Mockito.mock(IOConsole.class);
        BalanceManager.setConsole(console2);


        // mock user input to select menu option 3 then 7 in casino
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(3,7);

        // mock user input to select menu option 3 => exit the show balance menu
        Mockito.when(console2.getIntegerInput(Mockito.anyString())).thenReturn(3);

        casino.run();

        // getIntegerInput was called 2 times as 3 then 7 in casino class
        verify(console, times(2)).getIntegerInput(Mockito.anyString());

        // getIntegerInput was called 1 time as 3 to exit show balance menu
        verify(console2, times(1)).getIntegerInput(Mockito.anyString());

    }

    @Test
    public void testRunWithInputCase4_1() {
        Casino casino = new Casino();
        casino.setCurrentAccount(null); // so it goes into else statement => errorConsole -> exit
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 3 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(4,7);

        // execute the lobbyMenu() method
        casino.run();

        // getIntegerInput was called 2 time as 1 then 7 was selected and program exited
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testRunWithInputCase4_2() {
        // Given
        Casino casino = new Casino();
        casino.setCurrentAccount(new CasinoAccount()); // so it goes into else statement => errorConsole -> exit
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 3 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(4,7,7);

        // When
        // execute the lobbyMenu() method
        casino.run();

        // Then => execute 4 then 7 then 7.
        verify(console, times(3)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testRunWithInputCase5_1() {
        // Given
        Casino casino = new Casino();
        casino.setCurrentAccount(new CasinoAccount()); // so it goes into else statement => errorConsole -> exit
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 5 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(5,7);

        // When
        // execute the lobbyMenu() method
        casino.run();

        // Then => execute 5 then 7.
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testRunWithInputCase5_2() {
        // Given
        Casino casino = new Casino();
        casino.setCurrentAccount(null); // so it goes into else statement => errorConsole -> exit
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 5 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(5,7);

        // When
        // execute the lobbyMenu() method
        casino.run();

        // Then => execute 5 then 7.
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testRunWithInputCase6_1() {
        // Given
        Casino casino = new Casino();
        casino.setCurrentAccount(new CasinoAccount());
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 5 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(6,7);

        // When
        // execute the lobbyMenu() method
        casino.run();

        // Then => execute 6 then 7.
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testRunWithInputCase6_2() {
        // Given
        Casino casino = new Casino();
        casino.setCurrentAccount(null);
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 5 then 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(6,7);

        // When
        // execute the lobbyMenu() method
        casino.run();

        // Then => execute 5 then 7.
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }
//    @Test
//    public void testRunWithInputCase7() {
//        Casino casino = new Casino();
//        IOConsole console = Mockito.mock(IOConsole.class);
//        casino.setConsole(console);
//
//        // mock user input to select menu option 7
//        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(7);
//
//        // execute the lobbyMenu() method
//        casino.run();
//
//        // getIntegerInput was called only 1 time as 7 was selected and program exited
//        verify(console, times(1)).getIntegerInput(Mockito.anyString());
//    }

    @Test
    public void testRunWithInputCaseDefault() {
        Casino casino = new Casino();
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(8,7);

        // execute the lobbyMenu() method
        casino.run();

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testGetGameCaseDefault() {
        Casino casino = new Casino();
        IOConsole console = Mockito.mock(IOConsole.class);
        casino.setConsole(console);

        // mock user input to select menu option 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(8,7);

        // execute the lobbyMenu() method
        casino.getGame();

        // Then
        verify(console, times(2)).getIntegerInput(Mockito.anyString());
    }

    @Test
    public void testPlay() throws InterruptedException {
        Casino casino = new Casino();
        SlotsGame game = new SlotsGame();
        SlotsPlayer player = new SlotsPlayer(new CasinoAccount());

        IOConsole console = Mockito.mock(IOConsole.class);
        game.setConsole(console);

        // mock user input to select menu option 7
        Mockito.when(console.getIntegerInput(Mockito.anyString())).thenReturn(3);

        // execute the lobbyMenu() method
        casino.play(game,player);

        // Then
        verify(console, times(1)).getIntegerInput(Mockito.anyString());
    }

}
