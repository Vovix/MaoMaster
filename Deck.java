import java.util.Random;
import java.util.List;
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
    private List<Card> cardList;
    private Discard discard;
    Random rNGesus;

    /**
     * Constructor for objects of class Deck
     */
    public Deck(List<Card> cardList,Discard discard){
        // initialise instance variables
        size = 0;
        cards = "";
        this.discard=discard;
        this.cardList=cardList;
        for(int i=0;i<52;i++){
            if(size>0) cards = cards+",";
            cards = cards+cardList.get(i).index;
            size++;
        }
        rNGesus = new Random();
        shuffle();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public String draw(Discard discard){
        this.discard=discard;
        String card;
        if(cards.indexOf(",")==-1){
            card = "-1";
        } else {
            card = cards.substring(0,cards.indexOf(","));
        }
        if(card.equals("-1")){
            if(discard.getSize()>0){
                this.shuffleIn(discard);
                return this.draw(discard);
            } else return "D.N.E.";
        } else {
            cards=cards.substring(cards.indexOf(",")+1,cards.length());
            size--;
        }
        return card;
    }
    public void shuffleIn(Discard discard){
        this.discard=discard;
        size=size+discard.getSize();
        cards=cards+discard.addToDeck();
        String[] cardArray=cards.split(",");
        String[] newCardArray=new String[cardArray.length];
        int random;
        for(int i=0;i<cardArray.length;i++){
            random=rNGesus.nextInt(cardArray.length);
            while(cardArray[random].equals("D.N.E.")) random=rNGesus.nextInt(cardArray.length);
            newCardArray[i]=cardArray[random];
            cardArray[random]="D.N.E.";
        }
        cards=newCardArray[0];
        for(int i=1;i<newCardArray.length;i++) cards=cards+","+newCardArray[i];
    }
    public void shuffle(){
        String[] cardArray=cards.split(",");
        String[] newCardArray=new String[cardArray.length];
        int random;
        for(int i=0;i<cardArray.length;i++){
            random=rNGesus.nextInt(cardArray.length);
            while(cardArray[random].equals("D.N.E.")) random=rNGesus.nextInt(cardArray.length);
            newCardArray[i]=cardArray[random];
            cardArray[random]="D.N.E.";
        }
        cards=newCardArray[0];
        for(int i=1;i<newCardArray.length;i++) cards=cards+","+newCardArray[i];
    }
    public boolean discardTop(Discard discard){
        this.discard=discard;
        String card;
        if(cards.indexOf(",")==-1){
            card = "-1";
        } else {
            card = cards.substring(0,cards.indexOf(","));
        }
        if(card.equals("-1")){
            if(discard.getSize()>0){
                this.shuffleIn(discard);
                discard.add(this.draw(discard));
                return false;
            } else return true;
        } else {
            cards=cards.substring(cards.indexOf(",")+1,cards.length());
            size--;
        }
        discard.add(card);
        return false;
    }
}