package uno.game.core.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private static ArrayList<Card> createStack(){
        ArrayList<Card> deck = new ArrayList<>();

        for(int k = 0; k < 2; k++) {
            for (int i = k; i <= 12; i++) {
                deck.add(new Card(i, Card.ColorRed));
                deck.add(new Card(i, Card.ColorGreen));
                deck.add(new Card(i, Card.ColorBlue));
                deck.add(new Card(i, Card.ColorYellow));
            }
        }

        for(int i = 0; i < 4; i++){
            deck.add(new Card(13));
            deck.add(new Card(14));
        }

        return deck;
    }

    private ArrayList<Card> stack;
    private ArrayList<Player> players;
    private Integer playerCount;
    private Integer actualPlayer;
    private Integer directory;
    private final static Integer StartingCards = 5;

    private Card takeFromStack(){
        Card toReturn = stack.get(0);
        stack.remove(0);
        return toReturn;
    }

    private void addToStack(Card cardToAdd) {
        stack.add(cardToAdd);
    }

    public Game() throws Exception {
        stack = createStack();
        players = new ArrayList<>();
        Collections.shuffle(stack);

        Scanner in = new Scanner(System.in);
        Random rand  = new Random();

        printInitMessage();

        playerCount = in.nextInt();
        actualPlayer = rand.nextInt(playerCount);
        directory = 1;

        if( playerCount < 2 || 4 < playerCount){
            throw new Exception("Bad player count!");
        }

        for(int i = 0; i < playerCount; i++){
            System.out.println("Player number " + (i + 1) + " name: ");
            String playerName = in.next();
            Player justMade = new Player(playerName);
            players.add(justMade);
        }

        for(int i = 0; i < StartingCards; i++){
            for(Player player : players){
                player.addCard(takeFromStack());
            }
        }

        System.out.println("The game ready!");
    }

    private void printInitMessage() {
        System.out.println("Hello in UNO!");
        System.out.println("Players: ");
    }

    private void nextPlayer(){
        actualPlayer = (actualPlayer + directory) % playerCount;
    }

    private void reverse(){
        if(directory == 1) directory = playerCount - 1;
        else directory = 1;
    }
}
