import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mao
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
        if(game(cardList,handList,deck,discard,players)) System.out.println("The game is a draw.");
    }
    public static boolean game(List<Card> cardList,List<Hand> handList,Deck deck,Discard discard,int players){
        if(deck.discardTop(discard)) return true;
        int playerOutOfCards=-1;
        while(playerOutOfCards==-1){
            for(int i=0;i<players;i++){
                handList.get(i).hasSaidMao=turn(i,handList.get(i),cardList);
                playerOutOfCards=checkHands(cardList,handList,players);
                if(playerOutOfCards!=-1) i=players;
            }
        }
        if(handList.get(playerOutOfCards).hasSaidMao==true){
            if(playerOutOfCards==0) System.out.println("I win!");
            else System.out.println("Player "+playerOutOfCards+" wins!");
        }else{
            System.out.println("Failure to say \"Mao.\"");
            handList.get(playerOutOfCards).draw(2,deck,discard);
        }
        return false;
    }
    public static int checkHands(List<Card> cardList,List<Hand> handList,int players){
        for(int i=0;i<players;i++){
            if(handList.get(i).getSize()<1) return i;
        }
        return -1;
    }
    public static boolean turn(int player,Hand hand,List<Card> cardList/*may need more arguments*/){
        boolean hasSaidMao = false;
        Scanner input = new Scanner(System.in);
        System.out.println("Player "+player+"'s turn. Press enter to continue.");
        while(input.nextLine()==null);
        System.out.println("Your cards:");
        System.out.println(hand.getCardNames(cardList));
        System.out.println("Play which card?");
        String playedCard;
        boolean validPlay=false;
        while(!validPlay){
            playedCard=input.next();
            if(hand.getCardNames(cardList).indexOf(playedCard)!=-1) validPlay=true;
            else System.out.println("Invalid input.");
        }
        return hasSaidMao;
    }
}
