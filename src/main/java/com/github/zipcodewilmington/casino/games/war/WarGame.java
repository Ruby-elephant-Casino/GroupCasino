package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.BalanceManager;
import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.Card;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;


public class WarGame extends Game {
    WarPlayer currentPlayer;
    WarPlayer dealerPlayer = new WarPlayer(null);
    ArrayList<Card> potentialWinnings = new ArrayList<>();
    ArrayList<Card> newDeck = new ArrayList<>();

    IOConsole console = new IOConsole(AnsiColor.RED);


    @Override
    public void remove(Player player) {
        currentPlayer = null;
    }

    @Override
    public void run() throws InterruptedException {

        warMainMenu();
    }

    private void warMainMenu() {
        boolean isRunning = true;
        while(isRunning){
            StringBuilder sb = new StringBuilder();
            Integer choice = console.getIntegerInput(sb
                    .append("+-------------------------------+\n")
                    .append("|              WAR              |\n")
                    .append("+-------------------------------+\n")
                    .append("|  1. Start game                |\n")
                    .append("|  2. Check balance             |\n")
                    .append("|  3. Exit Game                 |\n")
                    .append("+-------------------------------+\n")
                    .append("SELECT A NUMBER: ")
                    .toString());

            switch(choice){
            case 1:
                startGame();
                break;
            case 2:
                BalanceManager.showBalance(currentPlayer.getPlayerAccount());
                break;
            case 3:
                remove(currentPlayer);
                isRunning = false;
                break;
            default:
                console.println("Pick a viable choice!");
                break;
            }
        }
    }

    @Override
    public Player add(Player player) {
        return this.currentPlayer = (WarPlayer) player;
    }


    @Override
    public void startGame() {
        boolean isPlaying = true;
        dealCards(currentPlayer, dealerPlayer);
        while(isPlaying) {
            StringBuilder sb = new StringBuilder();
            Integer choice = console.getIntegerInput(sb
                    .append("+-------------------------------+\n")
                    .append("|              WAR              |\n")
                    .append("+-------------------------------+\n")
                    .append("|  1. Flip card                 |\n")
                    .append("|  2. Exit Game                 |\n")
                    .append("+-------------------------------+\n")
                    .append("SELECT A NUMBER: ")
                    .toString());

            switch(choice) {
                case 1:
                    potentialWinnings.add(currentPlayer.flipCard());
                    potentialWinnings.add(dealerPlayer.flipCard());
                    compareCards(currentPlayer, dealerPlayer, potentialWinnings);
                    break;
                case 2:
                    remove(currentPlayer);
                    isPlaying = false;
                    break;
                default:
                    console.println("Pick a viable choice!");
                    break;
            }
        }
    }

    public int dealCards(WarPlayer currentPlayer, WarPlayer dealerPlayer){
        CardDeck cardDeck = new CardDeck();
        newDeck = cardDeck.createCardDeck();
        Collections.shuffle(newDeck);
        Card[] dealingDeck = newDeck.toArray(new Card[0]);
        for(int i = 0; i < dealingDeck.length; i++){
            if(i % 2 ==0){
                currentPlayer.addCardsToDeck(dealingDeck[i]);
            }else{
                dealerPlayer.addCardsToDeck(dealingDeck[i]);
            }
        }
        return currentPlayer.getCardDeck().size();
    }
    public boolean compareCards(WarPlayer currentPlayer, WarPlayer dealerPlayer, ArrayList<Card> potentialWinnings){
        StringBuilder sb = new StringBuilder();
        boolean playerWins = true;
        if(currentPlayer.getCurrentCard().getRank().ordinal() > dealerPlayer.getCurrentCard().getRank().ordinal()){
            //player is winner, add the cards to their deck
            console.println(printWinnings(potentialWinnings, true, currentPlayer, dealerPlayer));
        }else if(currentPlayer.getCurrentCard().getRank().ordinal() < dealerPlayer.getCurrentCard().getRank().ordinal()){
            //dealer wins, add the cards to their deck
            playerWins = false;
            console.println(printWinnings(potentialWinnings, false, currentPlayer, dealerPlayer));
        }else{
            //WarTime
            sb.append("\nIT'S WAR TIME!!!!!\n\nYou had a ")
                    .append(currentPlayer.getCurrentCard().getRank())
                    .append(" OF ")
                    .append(currentPlayer.getCurrentCard().getSuit())
                    .append("\nOpponent had a ")
                    .append(dealerPlayer.getCurrentCard().getRank())
                    .append(" OF ")
                    .append(dealerPlayer.getCurrentCard().getSuit())
                    .append("\n");
            console.println(sb.toString());
            warTime();
        }
        return playerWins;
    }

    public void warTime(){
        //deal as many cards as necessary
        for(int i = 0; i<3; i++) {
            potentialWinnings.add(currentPlayer.warTime());
            potentialWinnings.add(dealerPlayer.warTime());
        }
        //compare the last played card
        compareCards(currentPlayer, dealerPlayer, potentialWinnings);
    }

    public String printWinnings(ArrayList<Card> winnings, boolean playerWins, WarPlayer currentPlayer, WarPlayer dealerPlayer){
        StringBuilder sb = new StringBuilder();
        if(playerWins){
            sb.append("You won with a ")
                    .append(currentPlayer.currentCard.getRank())
                    .append(" OF ")
                    .append(currentPlayer.currentCard.getSuit());
        }else{
            sb.append("Opponent won with a ")
                    .append(dealerPlayer.currentCard.getRank())
                    .append(" OF ")
                    .append(dealerPlayer.currentCard.getSuit());
        }
        if(winnings.size()>2){
            sb.append(" and they won the ");
            for(Card c : winnings){
                  sb.append(c.getRank())
                  .append(" OF ")
                  .append(c.getSuit())
                  .append(", ");
            }
            sb.append("in the war.");
        }
        if(playerWins){
            sb.append("\nOpponent lost with a ")
                    .append(dealerPlayer.currentCard.getRank())
                    .append(" OF ")
                    .append(dealerPlayer.currentCard.getSuit());
            addWinnings(true, currentPlayer, dealerPlayer, winnings);
        }else {
            sb.append("\nYou lost with a ")
                    .append(currentPlayer.currentCard.getRank())
                    .append(" OF ")
                    .append(currentPlayer.currentCard.getSuit());
            addWinnings(false, currentPlayer, dealerPlayer, winnings);
        }

        return sb.toString();
    }

    public String printPlayerCardAmount(WarPlayer currentPlayer){
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nPlayer has ")
                .append(currentPlayer.getCardDeck().size())
                .append(" cards in their deck.\n");
        return sb.toString();
    }

    public boolean addWinnings(boolean playerWins, WarPlayer currentPlayer, WarPlayer dealerPlayer, ArrayList<Card> potentialWinnings){
        if(playerWins){
            currentPlayer.addWinnings(potentialWinnings);
        }else{
            dealerPlayer.addWinnings(potentialWinnings);
        }
        console.println(printPlayerCardAmount(currentPlayer));
        potentialWinnings.clear();

        return playerWins;
    }

    public WarPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(WarPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
