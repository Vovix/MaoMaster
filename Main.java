import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
    // instance variables - replace the example below with your own
    // main method
    public static void main(String[] args){
        int players = 2;
        // create 52 cards
        List<Card> cardList = new ArrayList<Card>();
        for(int i=0;i<52;i++){
            Card newCard = new Card();
            cardList.add(newCard);
        }
        // create discard pile
        Discard discard = new Discard(cardList);
        // create deck
        Deck deck = new Deck(cardList,discard);
        for(int i=0;i<52;i++) System.out.println(cardList.get(i).name()); // temporary
        // create [players] hands
        List<Hand> handList = new ArrayList<Hand>();
        for(int i=0;i<players;i++){
            Hand newHand = new Hand(cardList,deck,discard);
            handList.add(newHand);
        }
        System.out.println("Hand 1:");
        System.out.println(handList.get(0).getCards());
        System.out.println("Hand 2:");
        System.out.println(handList.get(1).getCards());
        System.out.println("Done.");
    }
}
