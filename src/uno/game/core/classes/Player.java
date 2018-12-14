package uno.game.core.classes;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private ArrayList<Card> deck = new ArrayList<>();

    public Player(){
        this("unknown");
    }

    public Player(String name){
        playerName = name;
    }

    public void addCard(Card cardFromStack){
        deck.add(cardFromStack);
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void printPlayersDeck(){
        System.out.println("Player: " + getPlayerName());
        System.out.println("id; name; color");
        int id = 0;
        for(Card next : deck){
            System.out.print(id + ": " + next.getCardName());
            if(next.hasColor()) System.out.print("; " + next.getColorName());
            System.out.println();
            id++;
        }
    }
}
