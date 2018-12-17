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
    private final static Integer MinPlayers = 2;
    private final static Integer MaxPlayers = 4;

    private Integer actualNumber;
    private Integer actualColor;
    private Integer cardsToDraw = 0;

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
        Random rand = new Random();

        printInitMessage();

        playerCount = in.nextInt();
        actualPlayer = rand.nextInt(playerCount);
        directory = 1;

        if( playerCount < MinPlayers || MaxPlayers < playerCount){
            throw new Exception("Bad player count!");
        }

        for(int i = 0; i < playerCount; i++){
            System.out.print("Player number " + (i + 1) + " name: ");
            String playerName = in.next();
            players.add(new Player(playerName));
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
        System.out.print("Players: ");
    }

    private void nextPlayer(){
        actualPlayer = (actualPlayer + directory) % playerCount;
    }

    private void reverse(){
        if(directory == 1) directory = playerCount - 1;
        else directory = 1;
    }

    public void play(){
        Card startingCard = takeFromStack();

        while(!startingCard.isNormalCard()){
            addToStack(startingCard);
            startingCard = takeFromStack();
        }

        actualColor = startingCard.getColor();
        actualNumber = startingCard.getNumber();

        while(true) {

            while(cardsToDraw --> 0){           //operator dążący
                players.get(actualPlayer).addCard(takeFromStack());
            }

            System.out.println("Player " + players.get(actualPlayer).getPlayerName() + " (" + (actualPlayer + 1) + ")" + " turn!");
            if(actualNumber == null) System.out.println("Actual card: - " + Card.getColorName(actualColor));
            else System.out.println("Actual card: " + Card.getCardName(actualNumber) + " " + Card.getColorName(actualColor));
            System.out.println("What to do?\ntype in \"play card_number\" or \"draw\"");
            players.get(actualPlayer).printPlayersDeck();

            Scanner in = new Scanner(System.in);
            String playersChoice = in.next();

            if(playersChoice.equals("draw")){
                players.get(actualPlayer).addCard(takeFromStack());
            } else if(playersChoice.equals("play")){
                int cardNumber = in.nextInt();
                Card chosenCard = players.get(actualPlayer).getDeck().get(cardNumber);
                if(!canPlayCard(chosenCard)) {
                    System.out.println("You can't play this card!");
                    continue;
                }

                players.get(actualPlayer).removeCard(cardNumber);

                if(players.get(actualPlayer).getDeck().size() == 1){
                    System.out.println("\n\nUNO!\n\n");
                }

                if(players.get(actualPlayer).getDeck().size() == 0){
                    System.out.println(players.get(actualPlayer).getPlayerName() + " WON!");
                    break;
                }

                addToStack(chosenCard);

                if(chosenCard.getAction() == 5 || chosenCard.getAction() == 6){
                    System.out.println("Change color to: ");
                    int id = 1;
                    for(String color : Card.ColorNames){
                        System.out.println((id++) + " " + color);
                    }
                    Integer newColor = in.nextInt();
                    actualColor = Card.ColorBase + newColor;
                    actualNumber = null;

                    if(chosenCard.getAction() == 6){
                        cardsToDraw = 4;
                    }
                } else {
                    if (chosenCard.getAction() == 2) {
                        nextPlayer();
                    } else if (chosenCard.getAction() == 3) {
                        reverse();
                    } else if (chosenCard.getAction() == 4) {
                        cardsToDraw = 2;
                    }
                    actualNumber = chosenCard.getNumber();
                    actualColor = chosenCard.getColor();
                }
            } else {
                System.out.println("Bad command!");
                continue;
            }

            nextPlayer();
        }
    }

    private boolean canPlayCard(Card card) {
        if(Card.NotColored.contains(card.getNumber())){
            return true;
        }
        if(actualNumber == null && card.getColor().equals(actualColor)){
            return true;
        }
        return card.getNumber().equals(actualNumber) || card.getColor().equals(actualColor);
    }
}
