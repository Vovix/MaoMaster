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
        Scanner input = new Scanner(System.in);
        cls();
        int players = getPlayers(input); // asks for human players, returns all (including computer)
        cls();
        char difficulty = getDifficulty(input); // e = easy, m = medium, h = hard
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
        // create [players] hands
        List<Hand> handList = new ArrayList<Hand>();
        for(int i=0;i<players;i++){
            Hand newHand = new Hand(cardList,deck,discard);
            handList.add(newHand);
        }
        if(game(cardList,handList,deck,discard,players)) System.out.println("The game is a draw.");
    }
    public static int getPlayers(Scanner input){
        System.out.println("How many (human) players?");
        int players=input.nextInt();
        while(players<1){
            System.out.println("There must be at least one human player.");
            players=input.nextInt();
        }
        return players+1; // +1 accounts for computer player
    }
    public static char getDifficulty(Scanner input){
        System.out.println("What AI difficulty? easy=newbie; medium=knows basic rules; hard=knows all rules");
        char dif=input.next().charAt(0);
        while(dif!='e'&&dif!='m'&&dif!='h'){
            System.out.println("Only values beginning with \"e\", \"m\", or \"h\" will be accepted. Difficulty?");
            dif=input.next().charAt(0);
        }
        return dif;
    }
    public static boolean game(List<Card> cardList,List<Hand> handList,Deck deck,Discard discard,int players){
        if(deck.discardTop(discard)) return true;
        int playerOutOfCards=-1;
        while(playerOutOfCards==-1){
            for(int i=0;i<players;i++){
                /*String wordsSaid=*/turn(i,players,handList.get(i),cardList,handList,deck,discard);
                //handList.get(i).hasSaidMao=false;
                playerOutOfCards=checkHands(cardList,handList,players);
                if(playerOutOfCards!=-1){
                    if(handList.get(playerOutOfCards).hasSaidMao){
                        if(playerOutOfCards==0){
                            System.out.println("I win!");
                            return false;
                        }else{
                            System.out.println("Player "+playerOutOfCards+" wins!");
                            return false;
                        }
                    }else{
                        System.out.println("Failure to say \"Mao.\"");
                        handList.get(playerOutOfCards).draw(2,deck,discard);
                        playerOutOfCards=-1;
                    }
                }
            }
        }
        return false;
    }
    public static int checkHands(List<Card> cardList,List<Hand> handList,int players){
        for(int i=0;i<players;i++){
            if(handList.get(i).getSize()<1) return i;
        }
        return -1;
    }
    public static void turn(int player,int players,Hand hand,List<Card> cardList,List<Hand> handList,Deck deck,Discard discard/*may need more arguments*/){
        Scanner input = new Scanner(System.in);
        cls();
        System.out.println("Player "+player+"'s turn. Press enter to continue.");
        while(input.nextLine()==null);
        cls();
        String[] topCards="D.N.E.,D.N.E.,D.N.E.".split(",");
        while(discard.cardAt(0).equals("D.N.E.")){
            deck.discardTop(discard);
        }
        for(int i=0;i<3;i++){
            topCards[i]=discard.cardAt(i);
        }
        System.out.println("Opponent hand sizes:");
        for(int i=0;i<players;i++){
            String playerName;
            if(i==0) playerName="AI";
            else playerName="P"+i;
            if(i!=player) System.out.print(playerName+": "+handList.get(i).getSize()+"   ");
        }
        System.out.println();
        System.out.println("Last cards on pile:");
        if(!topCards[2].equals("D.N.E.")) System.out.println("3rd: "+cardList.get(Integer.parseInt(topCards[2])).name());
        if(!topCards[1].equals("D.N.E.")) System.out.println("2nd: "+cardList.get(Integer.parseInt(topCards[1])).name());
        if(!topCards[0].equals("D.N.E.")) System.out.println("Top: "+cardList.get(Integer.parseInt(topCards[0])).name());
        System.out.println();
        System.out.println("Your cards:");
        System.out.println(hand.getCardNames(cardList));
        System.out.println("Play which card? Type \"draw\" to draw a card, which you may then play.");
        String playedCard="";
        boolean validPlay=false;
        while(!validPlay){
            playedCard=input.next().toUpperCase();
            if(hand.getCardNames(cardList).indexOf(playedCard)!=-1&&playedCard.matches("([AJQK]|\\d{1,2})[HDSC]")||playedCard.equals("DRAW")){
                validPlay=true;
            }
            else{
                if(playedCard.equals("EXIT")||playedCard.equals("QUIT")) System.exit(0);
                System.out.println("Invalid input (no penalty). Please re-enter.");
            }
        }
        if(!playedCard.equals("DRAW")){
            if(/*INSERT RULE CHECKER HERE*/true){
                String cardIndex="none";
                for(int i=0;i<52;i++){
                    if(cardList.get(i).name().equals(playedCard)){
                        cardIndex=String.valueOf(i);
                        i=52;
                    }
                }
                hand.play(cardIndex,discard);
            }else{
                System.out.println("Improper play.");
                hand.draw(1,deck,discard);
            }
            //System.out.println("Say anything? Asterisks the beginning signify knocking (e.g. \"** Hello.\")");
            return/* input.next()*/;
        }else{
            hand.draw(1,deck,discard);
            String cardIndex=hand.getCards().split(",")[hand.getCards().split(",").length-1];
            System.out.println("You drew the "+cardList.get(Integer.parseInt(cardIndex)).fullName()+".");
            System.out.println("Play drawn card? (y/n)");
            String answer;
            boolean validAnswer=false;
            while(!validAnswer){
                answer=input.next();
                if(answer.charAt(0)=='y'){
                    hand.play(cardIndex,discard);
                    validAnswer=true;
                }else if(answer.charAt(0)=='n'){
                    validAnswer=true;
                }else System.out.println("Invalid response (no penalty). Please re-enter.");
            }
        }
        //return input.next();
    }
    public static void cls(){
        try{
            if(System.getProperty("os.name").contains("Windows")) Runtime.getRuntime().exec("cls");
            else Runtime.getRuntime().exec("clear");
        }catch(Exception error){
        }
        System.out.println("\033[2J\n");
        System.out.println("\f");
    }
}
