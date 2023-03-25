package com.github.zipcodewilmington.casinoTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.utils.IOConsole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.io.File;
import java.util.HashMap;

public class AccountManagerTest {

    @Test
    public void testConstructor(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();

        // When
        // Then
        Assert.assertNotNull(cam.getAccountMap());
    }

    @Test
    public void testSetAccountMap(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();
        HashMap<String,CasinoAccount> expected = new HashMap<>();

        // When
        cam.setAccountMap(expected);
        // Then
        Assert.assertEquals(expected,cam.getAccountMap());
    }

    @Test
    public void testSetDBFile(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();
        File expected = new File("");

        // When
        cam.setDbFile(expected);

        // Then
        Assert.assertEquals(expected,cam.getDbFile());
    }

    @Test
    public void testGetAccount(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();
        String expectedName = "Woo";
        String expectedPassword = "Hoo";
        Double expectedBalance = 0.0;

        // When
        CasinoAccount expected = cam.createAccount(expectedName,expectedPassword);
        cam.registerAccount(expected);
        CasinoAccount actual = cam.getAccount(expectedName,expectedPassword);

        // Then
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void testCreateAccount(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();
        String expectedName = "Woo";
        String expectedPassword = "Hoo";
        Double expectedBalance = 0.0;

        // When
        CasinoAccount ca = cam.createAccount(expectedName,expectedPassword);

        // Then
        Assert.assertEquals(expectedName,ca.getName());
        Assert.assertEquals(expectedPassword,ca.getPassword());
        Assert.assertEquals(expectedBalance,ca.getBalance());
    }

    @Test
    public void testRegisterAccount(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();

        // When
        CasinoAccount ca = cam.createAccount("Woo","Hoo");
        cam.registerAccount(ca);

        // Then
        Assert.assertTrue(cam.getAccountMap().containsValue(ca));
    }

    @Test
    public void testCheckAccountName(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();
        String expectedName = "Woo";

        // When
        CasinoAccount ca = cam.createAccount(expectedName,"Hoo");
        cam.registerAccount(ca);

        // Then
        Assert.assertTrue(cam.checkAccountName(expectedName));
    }

    @Test
    public void testCheckAccount() {
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();
        String expectedName = "Woo";
        String expectedPass = "Hoo";

        // When
        CasinoAccount ca = cam.createAccount(expectedName, expectedPass);
        cam.registerAccount(ca);

        // Then
        Assert.assertTrue(cam.checkAccount(expectedName,expectedPass));
        Assert.assertFalse(cam.checkAccount(expectedName,""));
    }

    @Test
    public void testAskForAccountName(){
        // Given
        IOConsole console = Mockito.mock(IOConsole.class);
        CasinoAccountManager accountManager = new CasinoAccountManager();
        accountManager.setConsole(console);
        String accName = "newAccount";

        // When
        // inject accName when scanner input scan for string input
        Mockito.when(console.getStringInput(Mockito.anyString())).thenReturn(accName);
        String actual = accountManager.askForNewAccountName();

        // Then
        Assert.assertEquals(accName,actual);
    }

    @Test
    public void testAskForPassword(){
        // Given
        IOConsole console = Mockito.mock(IOConsole.class);
        CasinoAccountManager accountManager = new CasinoAccountManager();
        accountManager.setConsole(console);
        String password = "newPassword";

        // When
        // inject password when scanner input scan for string input
        Mockito.when(console.getStringInput(Mockito.anyString())).thenReturn(password);
        String actual = accountManager.askForNewPassword();

        // Then
        Assert.assertEquals(password,actual);
    }

    @Test
    public void testGetAccounts(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();

        // When
        Boolean actual = cam.getAllAccounts();
        // Then
        Assert.assertTrue(actual);
    }
    @Test
    public void testGetAccountsThrow(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();

        // When
        cam.setDbFile(new File(""));

        // Then
        Assertions.assertThrows(RuntimeException.class,
                ()->{cam.getAllAccounts();});
    }

    @Test
    public void testSaveAccounts(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();

        // When
        Boolean actual = cam.saveAllAccounts();
        // Then
        Assert.assertTrue(actual);
    }
    @Test
    public void testSaveAccountsThrow(){
        // Given
        CasinoAccountManager cam = new CasinoAccountManager();

        // When
        cam.setDbFile(new File(""));

        // Then
        Assertions.assertThrows(RuntimeException.class,
                ()->{cam.saveAllAccounts();});
    }

}
