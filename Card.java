
/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Card
{

    // class variables
    private static int numOfCards=0;
    // instance variables
    private int value;
    private String suit;

    /**
     * Constructor for objects of class Card
     */

    public Card(){
        // initialise instance variables
        value = numOfCards%13;
        if(numOfCards/13==0) suit = "Hearts";
        if(numOfCards/13==1) suit = "Diamonds";
        if(numOfCards/13==2) suit = "Spades";
        if(numOfCards/13==3) suit = "Clubs";
        numOfCards++;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */

    public int value(){
        //returns card value
        return value;
    }
    public char suit(){
        //returns suit
        return suit.charAt(0);
    }
    public String name(){
        //returns name (e.g. "4D" "AS" "10C")
        String res;
        if(value==11){
            res="J";
        }
        if(value==12){
            res="Q";
        }
        if(value==13){
            res="K";
        }        
        if(value==1){
            res="A";
        }else{
            res=String.valueOf(value);
        }
        res=res+suit;
        return res;
    }
    public String fullName(){
        //returns full name (e.g. "4 of Diamonds" "Ace of Spades" "10 of Clubs")
        String res;
        if(value==11){
            res="Jack";
        }
        if(value==12){
            res="Queen";
        }
        if(value==13){
            res="King";
        }        
        if(value==1){
            res="Ace";
        }else{
            res=String.valueOf(value);
        }
        res=res+" of "+suit;
        return res;
    }
}