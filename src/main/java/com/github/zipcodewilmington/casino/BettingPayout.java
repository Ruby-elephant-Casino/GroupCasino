package com.github.zipcodewilmington.casino;

/**
 * ONLY JAMES, DON'T TOUCH
 */
public class BettingPayout {
    private static final double maxBet = 100;
    private static final double minBet = 5;
    public static boolean checkMax(double bet) {
        return bet <= maxBet;
    }
    public static boolean checkMin(double bet){
        return bet >= minBet;
    }
    public boolean checkBetAgainstBalance(double bet, double currentBalance){
        return bet <= currentBalance;
    }
    public double betPayout(double bet, double multiplier){
        return (bet * multiplier);
    }
}
