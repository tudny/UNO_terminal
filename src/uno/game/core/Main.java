package uno.game.core;

import uno.game.core.classes.Card;

public class Main {
    public static void main(String[] args){
        System.out.println("Hello World!");

        Card testowa = new Card(1, Card.ColorRed);

        System.out.println(testowa.getColorName());

    }
}

