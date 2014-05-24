import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Rule here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rule
{
    // instance variables - replace the example below with your own
    private List<Card> triggerSuit = new ArrayList<Card>(); // list of suits that trigger the rule    
    private List<Integer> triggerValue = new ArrayList<Integer>(); // list of values that trigger the rule
    private List<Character> triggerLastSuit = new ArrayList<Character>(); // list of suits that trigger the rule
    private List<Integer> triggerLastValue = new ArrayList<Integer>(); // list of values that trigger the rule
    private int triggerSameValue; // -1=different 0=no 1=same
    private int triggerSameSuit; // -1=different 0=no 1=same
    private boolean and;
    
    private String haveToSay;

    /**
     * Constructor for objects of class Rule
     */
    public Rule(List<Card> triggerSuit,List<Integer> triggerValue,List<Character> triggerLastSuit,List<Integer> triggerLastValue,int triggerSameValue,int triggerSameSuit,boolean and)
    {
        this.triggerSuit=triggerSuit;
        this.triggerValue=triggerValue;
        this.triggerLastSuit=triggerLastSuit;
        this.triggerLastValue=triggerLastValue;
        this.triggerSameValue=triggerSameValue;
        this.triggerSameSuit=triggerSameSuit;
        this.and=and;
    }
    private boolean trigger(Card played, Card previous){
        if (and){
            if (!triggerSuit.contains(played.suit())){ //check suit
                return false;
            }
            if (!triggerValue.contains(played.value())){ //check value
                return false;
            }
            if (!triggerLastSuit.contains(previous.suit())){ //check last suit
                return false;
            }
            if (!triggerLastValue.contains(previous.value())){ //check last value
                return false;
            }
            if (triggerSameValue==1&played.value()!=previous.value()){//check if values are the same
                return false;
            }
            if (triggerSameValue==-1&played.value()==previous.value()){//check if values are different
                return false;
            }
            if (triggerSameSuit==1&played.suit()!=previous.suit()){//check if suits are the same
                return false;
            }
            if (triggerSameSuit==-1&played.suit()==previous.suit()){//check if suits are different
                return false;
            }
           return true;
        }else{
            if (triggerSuit.contains(played.suit())){ //check suit
                return true;
            }
            if (triggerValue.contains(played.value())){ //check value
                return true;
            }
            if (triggerLastSuit.contains(previous.suit())){ //check last suit
                return true;
            }
            if (triggerLastValue.contains(previous.value())){ //check last value
                return true;
            }
            if (triggerSameValue==1&played.value()==previous.value()){//check if values are the same
                return true;
            }
            if (triggerSameValue==-1&played.value()!=previous.value()){//check if values are different
                return true;
            }
            if (triggerSameSuit==1&played.suit()==previous.suit()){//check if suits are the same
                return true;
            }
            if (triggerSameSuit==-1&played.suit()!=previous.suit()){//check if suits are different
                return true;
            }
           return false;
        } 
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public boolean check(Card played, Card previous, String said)
    {
        if (!trigger(played,previous)) {
            return true;
        }
        //code code code code...
        return /*UPDATE THIS*/true;/*UPDATE THIS*/
    }
}
