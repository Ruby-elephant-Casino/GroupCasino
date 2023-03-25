package com.github.zipcodewilmington.casinoTest;

import com.github.zipcodewilmington.casino.CasinoAccount;
import org.junit.Assert;
import org.junit.Test;
// Given
// When
// Then
public class CasinoAccountTest {
    @Test
    public void testEmptyConstructor(){
        // Given
        CasinoAccount acc = new CasinoAccount();
        String expectedName = "bot";
        String expectedPass = "bot";
        Double expectedBalance = 0.0;

        // When
        // Then
        Assert.assertEquals(expectedName,acc.getName());
        Assert.assertEquals(expectedPass,acc.getPassword());
        Assert.assertEquals(expectedBalance,acc.getBalance());
    }

    @Test
    public void testConstructor(){
        // Given
        String expectedName = "bot1";
        String expectedPass = "bot1";
        Double expectedBalance = 0.0;
        CasinoAccount acc = new CasinoAccount(expectedName,expectedPass);

        // When
        // Then
        Assert.assertEquals(expectedName,acc.getName());
        Assert.assertEquals(expectedPass,acc.getPassword());
        Assert.assertEquals(expectedBalance,acc.getBalance());
    }

    @Test
    public void testConstructor2(){
        // Given
        String expectedName = "bot2";
        String expectedPass = "bot2";
        Double expectedBalance = 50.0;
        CasinoAccount acc = new CasinoAccount(expectedName,expectedPass,expectedBalance);

        // When
        // Then
        Assert.assertEquals(expectedName,acc.getName());
        Assert.assertEquals(expectedPass,acc.getPassword());
        Assert.assertEquals(expectedBalance,acc.getBalance());
    }

    @Test
    public void testGetName(){
        // Given
        String expectedName = "bot2";
        CasinoAccount acc = new CasinoAccount(expectedName,"",0.0);

        // When
        // Then
        Assert.assertEquals(expectedName,acc.getName());
    }

    @Test
    public void testSetName(){
        // Given
        CasinoAccount acc = new CasinoAccount();
        String expectedName = "bot2";

        // When
        acc.setName(expectedName);

        // Then
        Assert.assertEquals(expectedName,acc.getName());
    }

    @Test
    public void testGetPassword(){
        // Given
        String expectedPass = "bot2";
        CasinoAccount acc = new CasinoAccount("",expectedPass,5.0);

        // When
        // Then
        Assert.assertEquals(expectedPass,acc.getPassword());
    }

    @Test
    public void testSetPassword(){
        // Given
        CasinoAccount acc = new CasinoAccount();
        String expected = "pass";

        // When
        acc.setPassword(expected);

        // Then
        Assert.assertEquals(expected,acc.getPassword());
    }

    @Test
    public void testGetBalance(){
        // Given
        Double expected = 33.5;
        CasinoAccount acc = new CasinoAccount("","",expected);

        // When
        // Then
        Assert.assertEquals(expected,acc.getBalance());
    }

    @Test
    public void testSetBalance(){
        // Given
        CasinoAccount acc = new CasinoAccount();
        Double expected = 100.50;

        // When
        acc.setBalance(expected);

        // Then
        Assert.assertEquals(expected,acc.getBalance());
    }

    @Test
    public void testDeposit(){
        // Given
        CasinoAccount acc = new CasinoAccount();
        Double initialBalance = 100.0;
        Double expected = 200.0;

        // When
        acc.setBalance(initialBalance);
        acc.deposit(100.0);

        // Then
        Assert.assertEquals(expected,acc.getBalance());
    }

    @Test
    public void testWithdraw(){
        // Given
        CasinoAccount acc = new CasinoAccount();
        Double initialBalance = 100.0;
        Double expected = 50.0;

        // When
        acc.setBalance(initialBalance);
        acc.withdraw(50.0);

        // Then
        Assert.assertEquals(expected,acc.getBalance());
    }

}
