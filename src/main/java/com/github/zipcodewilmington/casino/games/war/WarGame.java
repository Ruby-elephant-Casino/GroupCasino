package com.github.zipcodewilmington.casino.games.war;

import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;
import com.github.zipcodewilmington.utils.Card;
import com.github.zipcodewilmington.utils.IOConsole;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class WarGame extends Game {
    WarPlayer currentPlayer;
    WarPlayer dealerPlayer = new WarPlayer(null);
    StringBuilder sb = new StringBuilder();
    ArrayList<Card> potentialWinnings = new ArrayList<>();

    IOConsole console = new IOConsole();
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
            sb.setLength(0);
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
                console.println(currentPlayer.getPlayerAccount().getBalance().toString());
                break;
            case 3:
                removePlayer(currentPlayer);
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
    public Player removePlayer(Player player) {
        return null;
    }

    @Override
    public void startGame() {
        boolean isPlaying = true;
        dealCards();
        while(isPlaying) {
            sb.setLength(0);
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
                    compareCards(currentPlayer.getCurrentCard(), dealerPlayer.getCurrentCard());
                    break;
                case 2:
                    removePlayer(currentPlayer);
                    isPlaying = false;
                    break;
                default:
                    console.println("Pick a viable choice!");
                    break;
            }

        }
    }

    public void dealCards(){
        CardDeck cardDeck = new CardDeck();
        Card[] dealingDeck = cardDeck.createCardDeck().toArray(new Card[0]);
        for(int i = 0; i < dealingDeck.length; i++){
            if(i % 2 ==0){
                currentPlayer.addCardsToDeck(dealingDeck[i]);
            }else{
                dealerPlayer.addCardsToDeck(dealingDeck[i]);
            }
        }
    }
    public void compareCards(Card playerCard, Card dealerCard){
        if(playerCard.getRank().ordinal() > dealerCard.getRank().ordinal()){
            //player is winner, add the cards to their deck
            currentPlayer.addWinnings(potentialWinnings);
            console.println("Player won with a " + playerCard.getRank() + " of " + playerCard.getSuit());
            console.println(printWinnings(potentialWinnings));
            console.println("Opponent had a " + dealerCard.getRank() + " of " + dealerCard.getSuit());
        }else if(playerCard.getRank().ordinal() < dealerCard.getRank().ordinal()){
            //dealer wins, add the cards to their deck
            dealerPlayer.addWinnings(potentialWinnings);
            console.println("Opponent won with a " + dealerCard.getRank() + " of " + dealerCard.getSuit());
            console.println("Player had a " + playerCard.getRank() + " of " + playerCard.getSuit());
        }else{
            //WarTime
            warTime();
        }
    }

    public void warTime(){
        //deal as many cards as necessary
        for(int i = 1; i<3; i++) {
            potentialWinnings.add(currentPlayer.warTime());
            potentialWinnings.add(dealerPlayer.warTime());
        }
        //compare the last played card
        compareCards(currentPlayer.currentCard, dealerPlayer.currentCard);
    }

    public String printWinnings(ArrayList<Card> winnings){
        sb.setLength(0);
        for(Card c : winnings){
            sb.append(c.getRank())
              .append(" of ")
              .append(c.getSuit());
        }
        return sb.toString();
    }
}
