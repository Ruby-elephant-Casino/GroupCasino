package com.github.zipcodewilmington.casino;

/**
 * ONLY JAMES, DON'T TOUCH
 */
public class BettingPayout {
    private  double maxBet = 0;
    private  double minBet = 0;

    public BettingPayout(int min, int max){
        minBet = min;
        maxBet = max;
    }
    public  boolean checkMax(double bet) {
        return bet <= maxBet;
    }
    public  boolean checkMin(double bet){
        return bet >= minBet;
    }
    public boolean checkBetAgainstBalance(double bet, double currentBalance){
        return bet <= currentBalance;
    }
    public static double betPayout(double bet, double multiplier){
        return (bet * multiplier);
    }
    public boolean checkBet(double bet){
        return (checkMin(bet) && checkMax(bet));
    }
}
