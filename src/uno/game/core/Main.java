package uno.game.core;

import uno.game.core.classes.Card;
import uno.game.core.classes.Game;

public class Main {

    public static void main(String[] args){
        //System.out.println("Hello World!");
        //Card c1 = new Card(14);

        //System.out.println(c1.getColorName());

        try {
            Game game = new Game();
            game.play();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

