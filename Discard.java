import java.util.List;
/**
 * Write a description of class Discard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Discard
{
    // instance variables - replace the example below with your own
    private int size;
    private String cards;
    private Deck deck;
    private List<Card> cardList;

    /**
     * Constructor for objects of class Discard
     */
    public Discard(List<Card> cardList){
        // initialise instance variables
        size = 0;
        cards = "";
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void add(String cardIndex){
        if(size>0) cards=","+cards;
        cards=cardIndex+cards;
        size++;
    }
    public int getSize(){
        return size;
    }
    public String addToDeck(){
        String cardsAdded="";
        while(size>0){
            cardsAdded=cardsAdded+","+cards.substring(0,cards.indexOf(","));
            cards=cards.substring(cards.indexOf(",")+1,cards.length());
            size--;
        }
        return cardsAdded;
    }
}