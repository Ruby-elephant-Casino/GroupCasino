package com.github.zipcodewilmington.gameToolsTest;

import com.github.zipcodewilmington.casino.gameTools.Dice;
import org.junit.Assert;
import org.junit.Test;

// METHODS FROM JAMES
public class DiceTest {
    @Test
    public void testDiceConstructor(){
        // Given
        int expected = 2;
        Dice dice = new Dice(expected);

        // When
        int actual = dice.getNumberDice();
        // Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testSetNumberDice(){
        // Given
        int numDice = 4;
        Dice dice = new Dice(numDice);
        // When
        int expected = 22;
        dice.setNumberDice(expected);
        int actual = dice.getNumberDice();
        //
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testRolldice(){
        // Given

        // When
        // Then
    }
}
