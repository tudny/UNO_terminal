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

    public void addCards(ArrayList<Card> cardsFromStack){
        deck.addAll(cardsFromStack);
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void printPlayersDeck(){
        System.out.println("\n-----------------");
        System.out.println("Player: " + getPlayerName());
        System.out.println("id; name; color");
        for(Card next : deck){
            System.out.print(deck.indexOf(next) + ": " + next.getCardName());
            if(next.hasColor()) System.out.print("\t\t " + next.getColorName());
            System.out.println();
        }
        System.out.println("-----------------\n");
    }

    public void removeCard(Integer cardId){
        deck.remove(deck.get(cardId));
    }
}
