package uno.game.core.classes;

/*
* CARD_ID   CARD_NAME   DESCRIPTION ACTION  ACTION_DESCRIPTION
* 0 - 9     Number      ""          1       "Normalna karta. Może być położona na takim samym kolorze lub numerze."
* 10        Skip        ""          2       "Może być położona na takim samym kolorze lub numerze. Pomija następnego gracza. Może zablokować blokadę."
* 11        Reverse     ""          3       "Odwraca kierunek ruchu gry."
* 12        DrawTwo     ""          4       "Obliguje następnego gracza do dobrania dwóch kart. Może zablokować przerzucić dobranie na następnego gracza."
* 13        Wild        ""          5       "Może zostać położona na dowolną kartę. Zmienia kolor na stole."
* 14        WildDraw    ""          6       "Może zostać położona na dowolną kartę. Zmienia kolor na stole. Dodatkowo obliguje następnego gracza do dobrania czterech kart."
* */

public class Card {

    final static private Integer ColorBase = 2010;
    final static public Integer ColorRed       = ColorBase + 1;
    final static public Integer ColorGreen     = ColorBase + 2;
    final static public Integer ColorBlue      = ColorBase + 3;
    final static public Integer ColorYellow    = ColorBase + 4;

    private final static String[] ColorNames = {"Red", "Green", "Blue", "Yellow"};
    private final static String[] CardNames  = {"Skip", "Reverse", "DrawTwo", "Wild", "WildDraw"};

    private Integer number; //treat as number and id from table above
    private Integer color;  //filled only if is NOT a Wild Card. Other way is NULL
    private Integer action; //id of action from table above

    public Card(Integer cardId, Integer cardColor) {
        try {
            number = cardId;
            color = cardColor;
            if(cardId == 13 || cardId == 14)
                color = null;
            action = readActionForNumber(cardId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Card(Integer cardId){
        this(cardId, ColorBase);
    }


    private Integer readActionForNumber(Integer cardId) throws Exception {
        if(cardId < 0 || cardId > 14){
            throw new Exception("Bad Card Number!");
        }
        if(cardId <= 9) {
            return 1;
        } else {
            return cardId - 8;
        }
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getColor() {
        return color;
    }

    public String getColorName() {
        if(color.equals(ColorBase)) return null;
        return ColorNames[color - ColorBase - 1];
    }

    public String getCardName() {
        if(number < 10){
            return String.valueOf(number);
        } else {
            return CardNames[number - 10];
        }
    }

    public Integer getAction() {
        return action;
    }

    public boolean hasColor(){
        return (color != null);
    }
}
