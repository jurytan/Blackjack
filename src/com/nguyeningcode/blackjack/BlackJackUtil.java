package com.nguyeningcode.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jnguyen on 8/31/17.
 */
public class BlackJackUtil {

    public static Scanner in = new Scanner(System.in);

    public static int getNumberOfPlayers(int MAX_PLAYERS) {
        System.out.println("How many players? (1-10) : ");
        int numPlayers = 0;
        boolean error;
        do {
            try {
                error = false;
                numPlayers = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Please enter a valid number (1-10): ");
                error = true;
            }

            if ((numPlayers < 1 || numPlayers > MAX_PLAYERS) && !error) {
                System.out.println("Please enter a number within the allowed range! (1-10): ");
            }

        } while (numPlayers < 1 || numPlayers > MAX_PLAYERS || error);
        return numPlayers;
    }

    public static void getBetsForPlayers(ArrayList<Player> players, double[] bets) {
        double tempBet = -1;
        boolean error;
        for (int i = 0; i < players.size(); i++) {
            System.out.println("[" + players.get(i).getName() + "] Enter a bet for player \"" + players.get(i).getName() + "\" (>=" + Game.MIN_BUY_IN + "): ");
            do {
                try {
                    error = false;
                    tempBet = Double.parseDouble(in.nextLine());
                } catch (Exception e) {
                    System.out.println("[" + players.get(i).getName() + "] Please enter a valid number for your bet! (>=" + Game.MIN_BUY_IN + "): ");
                    error = true;
                }

                if (tempBet < Game.MIN_BUY_IN && !error) {
                    System.out.println("[" + players.get(i).getName() + "] Please enter a bet greater then the minimum buy in! (>=" + Game.MIN_BUY_IN + "): ");
                }

                if(!checkBetAgainstBalance(players.get(i).balance, tempBet) && !error) {
                    System.out.println("[" + players.get(i).getName() + "] You don't have enough to bet that! Your balance is " + players.get(i).balance + " but your bet was " + tempBet + "): ");
                }

            } while (tempBet < Game.MIN_BUY_IN || !checkBetAgainstBalance(players.get(i).balance, tempBet) || error);
            bets[i] = tempBet;
        }
    }

    public static void getPlayers(ArrayList<Player> players, int numPlayers) {
        boolean error;
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("[Player #" + (i+1) + "] Please input your name: ");
            String name = in.nextLine();
            double balance = 0;
            System.out.println("[" + name + "] Please input your balance (>=" + Game.MIN_BUY_IN + "): ");
            do {
                try {
                    error = false;
                    balance = Double.parseDouble(in.nextLine());
                } catch (Exception e) {
                    System.out.println("[" + name + "] Please enter a valid number! (>=" + Game.MIN_BUY_IN + "): ");
                    error = true;
                }

                if (balance < Game.MIN_BUY_IN && !error) {
                    System.out.println("[" + name + "] Please enter a balance greater than " + Game.MIN_BUY_IN + "! (>=" + Game.MIN_BUY_IN + "): ");
                }
            } while (balance < Game.MIN_BUY_IN || error);
            players.add(new Player(name, balance));
        }
    }


    public static boolean checkBetAgainstBalance(double balance, double bet) {
        return balance >= bet;
    }

}
