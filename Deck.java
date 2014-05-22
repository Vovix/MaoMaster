import java.util.random;
/**
 * Write a description of class Deck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deck
{
    // instance variables - replace the example below with your own
    private int size;
    private String cards;
    Random rNGesus;

    /**
     * Constructor for objects of class Deck
     */
    public Deck(){
        // initialise instance variables
        size = 52;
        for(i=0;i<52;i++){
            // create a Card object and add its card.name to String cards
        }
        rNGesus = new Random();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String draw(){
        String card;
        if(cards.indexOf(",")==-1){
            card = "-1";
        } else {
            card = cards.substring(0,cards.indexOf(","));
        }
        if(card.equals("-1")){
            if(Discard.getSize>0){
                deck.shuffle();
                return deck.draw;
            } else return "D.N.E.";
        } else {
            cards=cards.substring(cards.indexOf(","+1),cards.length());
            size--;
        }
        return card;
    }
    public void shuffle(){
        size=cards+Discard.getSize();
        cards=cards+Discard.addToDeck();
        cardArray=cards.split(",");
        newCardArray=new String[cardArray.length];
        int random;
        for(i=0;i<cardArray.length;i++){
            random=rNGesus.nextInt(cardArray.length);
            while(cardArray[random].equals("D.N.E.")) random=rNGesus.nextInt(cardArray.length);
            newCardArray[i]=cardArray[random];
            cardArray[random].equals("D.N.E.");
        }
        cards="";
        for(i=0;i<newCardArray.length;i++) cards=cards+","+newCardArray[i];
    }
}
