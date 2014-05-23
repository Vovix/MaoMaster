
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
    public int index; // numerical identification from 0 to 51; use cardList.get(int index) to reference cards

    /**
     * Constructor for objects of class Card
     */

    public Card(){
        // initialise instance variables
        value = (numOfCards+1)%13;
        if(value==0) value=13;
        if(numOfCards/13==0) suit = "Hearts";
        if(numOfCards/13==1) suit = "Diamonds";
        if(numOfCards/13==2) suit = "Spades";
        if(numOfCards/13==3) suit = "Clubs";
        index = numOfCards;
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
        }else if(value==12){
            res="Q";
        }else if(value==13){
            res="K";
        }else if(value==1){
            res="A";
        }else{
            res=String.valueOf(value);
        }
        res=res+suit.charAt(0);
        return res;
    }
    public String fullName(){
        //returns full name (e.g. "4 of Diamonds" "Ace of Spades" "10 of Clubs")
        String res;
        if(value==11){
            res="Jack";
        }else if(value==12){
            res="Queen";
        }else if(value==13){
            res="King";
        }else if(value==1){
            res="Ace";
        }else{
            res=String.valueOf(value);
        }
        res=res+" of "+suit;
        return res;
    }
}