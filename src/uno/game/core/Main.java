package uno.game.core;

import uno.game.core.classes.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        //System.out.println("Hello World!");

        Player p1 = new Player("Alek");
        Card c1 = new Card(9, Card.ColorRed);
        Card c2 = new Card(14);
        Card c3 = new Card(13);
        Card c4 = new Card(4, Card.ColorYellow);
        Card c5 = new Card(10, Card.ColorBlue);

        p1.addCard(c1);
        p1.addCard(c2);
        p1.addCard(c3);
        p1.addCard(c4);
        p1.addCard(c5);

        ArrayList<Card> d1 = p1.getDeck();

        p1.printPlayersDeck();

    }
}

