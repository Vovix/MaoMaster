import java.util.List;
/**
 * Write a description of class Hand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hand
{
    // instance variables - replace the example below with your own
    private int size; // number of cards in hand
    private String cards; // names of cards in hand, separated by commas
    private Deck deck;
    private Discard discard;
    private List<Card> cardList;
    /**
     * Constructor for objects of class Hand
     */
    public Hand(List<Card> cardList,Deck deck,Discard discard){
        // initialise instance variables
        size=0;
        cards="";
        draw(5);
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void draw(int n){
        for(int i=0;i<n;i++){
            String newCard=deck.draw();
            if(!newCard.equals("D.N.E.")){
                if(cards.length()>0) cards=cards+",";
                cards=cards+newCard;
                size++;
            }
        }
    }
    public String getCards(){
        return cards;
    }
}
