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
    public String getCards(){
        return cards;
    }
    public String addToDeck(){
        String cardsAdded="";
        String[] topCards="D.N.E.,D.N.E.,D.N.E.".split(",");
        for(int i=0;i<3;i++){
            if(cards.contains(",")){
                topCards[i]=cards.substring(0,cards.indexOf(","));
                cards=cards.substring(cards.indexOf(",")+1,cards.length());
                size--;
            }
        }
        while(size>0){
            cardsAdded=cardsAdded+","+cards.substring(0,cards.indexOf(","));
            cards=cards.substring(cards.indexOf(",")+1,cards.length());
            size--;
        }
        for(int i=1;i<3;i++){
            if(!topCards[i].equals("D.N.E.")){
                if(cards.length()>0) cards=cards+",";
                cards=cards+topCards[i];
                size++;
            }
        }
        return cardsAdded;
    }
    public String cardAt(int index){
        String[] cardArray=cards.split(",");
        if(index>=0&&index<cardArray.length){
            return cardArray[index];
        }else{
            return "D.N.E.";
        }
    }
}
