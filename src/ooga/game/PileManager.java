package ooga.game;

import ooga.cards.Card;
import ooga.cards.Value;
import ooga.piles.DiscardPile;
import ooga.piles.DrawPile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds the discard and draw piles and handles updates to those piles
 * @author Mary Jiang (mvj6)
 */

public class PileManager {
    private DiscardPile discPile;
    private DrawPile drawPile;
    private ArrayList<Card> specialCards;

    public PileManager(){
        this(new ArrayList<Card>());
    }

    public PileManager(ArrayList<Card> specialCards){
        this.specialCards = specialCards;
        discPile = new DiscardPile();
        drawPile = new DrawPile();
        init();
    }

    /**
     * This constructor is used mainly in testing when a custom draw pile is used
     * @param drawPile
     */
    public PileManager(DrawPile drawPile){
        this();
        this.drawPile = drawPile;
    }

    /**
     * Constructor used when loading a game
     * @param drawPile
     * @param discPile
     */
    public PileManager(DrawPile drawPile, DiscardPile discPile){
        this.drawPile = drawPile;
        this.discPile = discPile;
    }


    public void init(){
        discPile = new DiscardPile();
        makeDrawPile(specialCards);

        //flip over the first card
        discPile.addCard(drawPile.drawCard());
    }

    public DiscardPile getDiscPile(){
        return discPile;
    }

    public DrawPile getDrawPile(){
        return drawPile;
    }

    public Card drawCard(){

        Card drawnCard =  drawPile.drawCard();
        //when draw pile is empty, put discard pile cards into it
        if (drawPile.getCardCount() == 0){
            drawPile = new DrawPile(discPile.getAllCards());
            discPile = new DiscardPile();
            discPile.addCard(drawPile.drawCard());
        }

        return drawnCard;
    }

    public Card showTopCard(){

        return discPile.showTopCard();
    }

    public void discardCard(Card card){
        discPile.addCard(card);
    }

    private void makeDrawPile(ArrayList<Card> specialCards){
        List<Value> specialCardValues = new ArrayList<Value>();
        for (Card specialCard : specialCards){
            specialCardValues.add(specialCard.getValue());
        }

        drawPile = new DrawPile(specialCardValues);
    }

    /**
     * Added for bug fix testing
     * @param drawPile
     */
    public void setDrawPile(DrawPile drawPile){
        this.drawPile = drawPile;
    }

    public void setDiscPile(DiscardPile discPile){
        this.discPile = discPile;
    }





}
