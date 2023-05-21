package com.github.zipcodewilmington.casino.games.blackjack;

import com.github.zipcodewilmington.casino.Game;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.gameTools.CardDeck;
import java.util.Scanner;



public class BlackJackGame extends Game {
    Scanner scanner = new Scanner(System.in);
    BlackJackPlayer player;
    private final BlackJackDealerPlayer dealer = new BlackJackDealerPlayer();
    private final CardDeck deck = new CardDeck();
    int money;

//        public BlackJackGame(BlackJackPlayer player) {
//        this.player = player;
//}
    private Integer BlackJackMenu() {
        System.out.println("+-----------------------+\n");
        System.out.println("|    BLACKJACK MENU     |\n");
        System.out.println("+-----------------------+\n");
        System.out.println("|  1. Start Game        |\n");
        System.out.println("|  2. Check Balance     |\n");
        System.out.println("|  3. Exit Game         |\n");
        System.out.println("+-----------------------+\n");
        System.out.println("SELECT A NUMBER: ");
        System.out.println();
        return scanner.nextInt();
    }

    @Override
    public void run() throws InterruptedException {
//        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to BlackJack!");
//        String command;
//        int val;
        while (true) {
            int choice = BlackJackMenu();
            switch (choice) {
                    case 1:
                        startGame();
                        break;
                    case 2:
                        System.out.println("Your balance is: " + player.getPlayerAccount().getBalance());
                        break;
                    case 3:
                        remove(player);
                        return;
                    default:
                        System.out.println("Please enter a valid command");
                }
            }
        }



    @Override
    public void remove(Player player) {
        if (this.player == player) {
            this.player = null;
        }
    }

    @Override
    public Player add(Player player) {
        return this.player = (BlackJackPlayer) player;

    }

    @Override
    public boolean bet(Player player, int amount, int min) {
        if (amount < min) {
            System.out.println("10 is the minimum bet, please put some more or leave :)");
            return false;
        } else { //the method subtracts the amount from the player's casino account balance
            // using the "setBalance" method, sets the "money" variable
            player.getPlayerAccount().setBalance(player.getPlayerAccount().getBalance() - amount);
            this.money = amount;
            return true;
        }
    }

    @Override
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        String command;
//        int value;
        while (true) {
            if (BlackJackPlayer.getValue() == 21 && BlackJackDealerPlayer.getValue() < 21) {
                System.out.println("You got BlackJack! Congratulation you won!!");
                player.getPlayerAccount().setBalance(player.getPlayerAccount().getBalance() + (money * 2));
                break;
            } else if (BlackJackPlayer.getValue() == 21 && BlackJackDealerPlayer.getValue() == 21) {
                System.out.println("both losers");
                player.getPlayerAccount().setBalance(player.getPlayerAccount().getBalance() + money);
                break;
            } else if (BlackJackDealerPlayer.getValue() == 21) {
                System.out.println("Dealer got BlackJake! Loser");
                break;
            } else if (BlackJackDealerPlayer.getValue() > 21) {
                System.out.println("Dealer busted! Congratulation you won");
                player.getPlayerAccount().setBalance(player.getPlayerAccount().getBalance() + (money * 2));
                break;
            } else if (BlackJackPlayer.getValue() > 21) {
                System.out.println("You looooose");
                break;
            }

            System.out.println("+------------------------+\n");
            System.out.println("|    BLACKJACK MENU      |\n");
            System.out.println("+-----------------------+ \n");
            System.out.println("| 1. Hit                 |\n");
            System.out.println("| 1. Stand               |\n");
            System.out.println("| 2. Check Balance       |\n");
            System.out.println("| 3. Exit Game           |\n");
            System.out.println("+------------------------+\n");
            System.out.println("SELECT A NUMBER: ");

            command = scanner.nextLine().trim().toLowerCase();
            if (command.equals("1")) {
                player.addCard(deck.deal());
                System.out.println("Your cards: " + player + " valued at: " + BlackJackPlayer.getValue());
                if (BlackJackPlayer.getValue() > 21) {
                    System.out.println("You lose");
                    break;
                }
            } else if (command.equals("2")) {
                dealer.addCard(deck.deal());
                break;
            } else if (command.equals("3")) {
                System.out.println("Your balance: " + player.getPlayerAccount().getBalance());
            } else if (command.equals("4")) {
                System.out.println("Thanks for playing!");
                return;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        while (BlackJackDealerPlayer.getValue() < 17) {
            System.out.println("Dealer hits.");
            dealer.addCard(deck.deal());
            System.out.println("Dealer's cards: " + dealer + " valued at: " + BlackJackDealerPlayer.getValue());
        }

        if (BlackJackDealerPlayer.getValue() > BlackJackPlayer.getValue() && BlackJackDealerPlayer.getValue() <= 21) {
            System.out.println("Sorry you lose!");
        } else if (BlackJackDealerPlayer.getValue() == BlackJackPlayer.getValue()) {
            System.out.println("Dealer's cards: " + dealer + " valued at: " + BlackJackDealerPlayer.getValue());
            System.out.println("It's a tie! Nobody wins..");
            player.getPlayerAccount().setBalance(player.getPlayerAccount().getBalance() + money);
        } else {
            System.out.println("You win!");
            player.getPlayerAccount().setBalance(player.getPlayerAccount().getBalance() + (money * 2));
        }
    }
}
