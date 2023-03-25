package com.github.zipcodewilmington.casinoTest;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

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
        Casino casino = new Casino();
        CasinoAccountManager accountManager = Mockito.mock(CasinoAccountManager.class);
        String accName = "newAccount";

        // When
        // inject accName when scanner input scan for string input
        Mockito.when(accountManager.askForAccountName()).thenReturn(accName);
        String actual = accountManager.askForAccountName();

        // Then
        Assert.assertEquals(accName,actual);
    }

    @Test
    public void testAskForPassword(){
        // Given
        CasinoAccountManager accountManager = Mockito.mock(CasinoAccountManager.class);

        String password = "newPassword";

        // When
        // inject password when scanner input scan for string input
        Mockito.when(accountManager.askForPassword()).thenReturn(password);
        String actual = accountManager.askForPassword();

        // Then
        Assert.assertEquals(password,actual);
    }
}
