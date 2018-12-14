package uno.game.core.classes;

import java.util.ArrayList;
import java.util.Collections;
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
    private Integer playerCount;

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
        Collections.shuffle(stack);

        Scanner in = new Scanner(System.in);

        printInitMessage();

        playerCount = in.nextInt();

        if( playerCount < 2 || 4 < playerCount){
            throw new Exception("Bad player count!");
        }

    }

    private void printInitMessage() {
        System.out.println("Hello in UNO!");
        System.out.println("Players: ");
    }
}
