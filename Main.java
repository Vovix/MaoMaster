
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{
    // instance variables - replace the example below with your own
    int players = 2;
    // main method
    public static void main(String[] args){
        // create deck
        Deck deck = new Deck();
        // create 52 cards
        List<Card> cardList = new ArrayList<Card>();
        for(i=0;i<52;i++){
            Card newCard = new Card();
            cardList.add(newCard);
            newCard.setName(newCard.name);
        }
        // create [players] hands
        List<Hand> handList = new ArrayList<Hand>();
        for(i=0;i<players;i++){
            Hand newHand = new Hand();
            handList.add(newHand);
            newHand.setName("H"+i);
        }
    }
}
