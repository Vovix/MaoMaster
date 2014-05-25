import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
        List<Rule> ruleList = new ArrayList<Rule>();
//         List<Integer> tVals=new ArrayList<Integer>();
//         List<Integer> tLVals=new ArrayList<Integer>();
//         List<Character> tSuits=new ArrayList<Character>();
//         List<Character> tLSuits=new ArrayList<Character>();
//         tVals=intList("1,2,3,4,5,6,7,8,9,10,11,12,13");
//         tLVals=intList("1,2,3,4,5,6,7,8,9,10,11,12,13");
//         tSuits=charList("HDSC");
//         tLSuits=charList("HDSC");
//         Rule sameSuitValue = new Rule(tSuits,tVals,tLSuits,tLVals,-1,-1,true,"");
//         ruleList.add(sameSuitValue);
        String fileName=getRulesFile();
        int lines = countLines(fileName);
        if(lines==-1){
            try{
                PrintWriter writer = new PrintWriter(fileName);
                writer.println("HDSC");// triggerSuit      HDSC
                writer.println("1,2,3,4,5,6,7,8,9,10,11,12,13");// triggerValue     1,2,3,...,11,12,13; 0=none
                writer.println("HDSC");// triggerLastSuit  HDSC
                writer.println("1,2,3,4,5,6,7,8,9,10,11,12,13");// triggerLastValue 1,2,3,...,11,12,13; 0=none
                writer.println("-1");// triggerSameValue -1,0,1 different,ignore,same
                writer.println("-1");// triggerSameSuit  -2,-1,0,1,2 !=color,!=suit,ignore,==suit,==color
                writer.println("true");// and
                writer.println("");// haveToSay
                writer.println("");// triggerSuit      HDSC
                writer.println("7");// triggerValue     1,2,3,...,11,12,13; 0=none
                writer.println("");// triggerLastSuit  HDSC
                writer.println("0");// triggerLastValue 1,2,3,...,11,12,13; 0=none
                writer.println("0");// triggerSameValue -1,0,1 different,ignore,same
                writer.println("0");// triggerSameSuit  -2,-1,0,1,2 !=color,!=suit,ignore,==suit,==color
                writer.println("false");// and
                writer.println("Have a nice day");// haveToSay
                writer.println("S");// triggerSuit      HDSC
                writer.println("0");// triggerValue     1,2,3,...,11,12,13; 0=none
                writer.println("");// triggerLastSuit  HDSC
                writer.println("0");// triggerLastValue 1,2,3,...,11,12,13; 0=none
                writer.println("0");// triggerSameValue -1,0,1 different,ignore,same
                writer.println("0");// triggerSameSuit  -2,-1,0,1,2 !=color,!=suit,ignore,==suit,==color
                writer.println("false");// and
                writer.println("*cName");// haveToSay
                //writer.println("");// triggerSuit      HDSC
                //writer.println("");// triggerValue     1,2,3,...,11,12,13; 0=none
                //writer.println("");// triggerLastSuit  HDSC
                //writer.println("");// triggerLastValue 1,2,3,...,11,12,13; 0=none
                //writer.println("");// triggerSameValue -1,0,1 different,ignore,same
                //writer.println("");// triggerSameSuit  -2,-1,0,1,2 !=color,!=suit,ignore,==suit,==color
                //writer.println("");// and
                //writer.println("");// haveToSay
                writer.close();
                lines=countLines(fileName);
            }catch(FileNotFoundException error){}
        }
        for(int i=0;i<lines/8;i++){
            int line=i*8;
            List<Integer> tVals=new ArrayList<Integer>();
            List<Integer> tLVals=new ArrayList<Integer>();
            List<Character> tSuits=new ArrayList<Character>();
            List<Character> tLSuits=new ArrayList<Character>();
            tSuits=charList(readLine(fileName,line+1));
            tVals=intList(readLine(fileName,line+2));
            tLSuits=charList(readLine(fileName,line+3));
            tLVals=intList(readLine(fileName,line+4));
            int tSameVal = Integer.parseInt(readLine(fileName,line+5));
            int tSameSuit = Integer.parseInt(readLine(fileName,line+6));
            boolean and = Boolean.parseBoolean(readLine(fileName,line+7));
            String haveToSay = readLine(fileName,line+8);
            Rule newRule = new Rule(tSuits,tVals,tLSuits,tLVals,tSameVal,tSameSuit,and,haveToSay);
            ruleList.add(newRule);
        }
        if(game(cardList,handList,ruleList,deck,discard,players)) System.out.println("The game is a draw.");
    }
    public static List<Integer> intList(String ints){
        List<Integer> list = new ArrayList<Integer>();
        String[] strArray = ints.split(",");
        for(int i=0;i<strArray.length;i++){
            list.add(Integer.parseInt(strArray[i]));
        }
        return list;
    }
    public static List<Character> charList(String chars){
        List<Character> list = new ArrayList<Character>();
        for(int i=0;i<chars.length();i++){
            list.add(chars.charAt(i));
        }
        return list;
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
    public static boolean game(List<Card> cardList,List<Hand> handList,List<Rule> ruleList,Deck deck,Discard discard,int players){
        Scanner input = new Scanner(System.in);
        if(deck.discardTop(discard)) return true;
        int playerOutOfCards=-1;
        while(playerOutOfCards==-1){
            for(int i=0;i<players;i++){
                /*String wordsSaid=*/playerOutOfCards=turn(i,players,handList.get(i),cardList,handList,ruleList,deck,discard);
                //handList.get(i).hasSaidMao=false;
                if(playerOutOfCards!=-1) i=players;
                while(input.nextLine()==null);
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
    public static int turn(int player,int players,Hand hand,List<Card> cardList,List<Hand> handList,List<Rule> ruleList,Deck deck,Discard discard/*may need more arguments*/){
        int playerOutOfCards=-1;
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
            playerOutOfCards=checkPlay(playedCard,cardList,hand,handList,ruleList,deck,discard,players,player);
            return playerOutOfCards;
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
                    playerOutOfCards=checkPlay(cardList.get(Integer.parseInt(cardIndex)).name(),cardList,hand,handList,ruleList,deck,discard,players,player);
                    validAnswer=true;
                }else if(answer.charAt(0)=='n'){
                    validAnswer=true;
                }else System.out.println("Invalid response (no penalty). Please re-enter.");
            }
        }
        return playerOutOfCards;
    }
    public static int checkPlay(String playedCard,List<Card> cardList,Hand hand,List<Hand> handList,List<Rule> ruleList,Deck deck,Discard discard,int players,int player){
        Scanner input = new Scanner(System.in);
        System.out.println("Say anything? Press enter to say nothing.");
        //System.out.println("Asterisks at the beginning signify knocking (e.g. \"** Hello.\").");
        String said=input.nextLine().toLowerCase();
        //if(said.equals(".")) said="";
        String cardIndex="none";
        for(int i=0;i<52;i++){
            if(cardList.get(i).name().equals(playedCard)){
                cardIndex=String.valueOf(i);
                i=52;
            }
        }
        int cIndex=Integer.parseInt(cardIndex);
        boolean canPlay=true;
        for(int ruleNum=0;ruleNum<ruleList.size();ruleNum++){
            if(!discard.cardAt(0).equals("D.N.E.")){
                String[] ruleOut=ruleList.get(ruleNum).check(cardList.get(cIndex),cardList.get(Integer.parseInt(discard.cardAt(0))),said);
                if(ruleOut[1].equals("true")){
                    hand.draw(1,deck,discard);
                }
                if(ruleOut[2].equals("false")){
                    canPlay=false;
                    System.out.println("Improper play (card given).");
                    hand.draw(1,deck,discard);
                }
                said=ruleOut[0];
            }else{
                String[] ruleOut=ruleList.get(ruleNum).check(cardList.get(cIndex),null,said);
                if(ruleOut[1].equals("true")){
                    hand.draw(1,deck,discard);
                }
                if(ruleOut[2].equals("false")){
                    canPlay=false;
                    System.out.println("Improper play (card given).");
                    hand.draw(1,deck,discard);
                }
                said=ruleOut[0];
            }
        }
        if(canPlay) hand.play(cardIndex,discard);
        if(said.toLowerCase().contains(" mao")){
            hand.hasSaidMao=true;
            int indMao=said.toLowerCase().indexOf(" mao");
            said=said.substring(0,indMao)+said.substring(indMao+4,said.length());
        }else if(said.toLowerCase().contains("mao ")){
            hand.hasSaidMao=true;
            int indMao=said.toLowerCase().indexOf("mao ");
            said=said.substring(0,indMao)+said.substring(indMao+4,said.length());
        }else if(said.toLowerCase().contains("mao")){
            hand.hasSaidMao=true;
            int indMao=said.toLowerCase().indexOf("mao");
            said=said.substring(0,indMao)+said.substring(indMao+3,said.length());
        }
        int playerOutOfCards=-1;//checkHands(cardList,handList,players);
        if(hand.getSize()<1) playerOutOfCards=player;
        if(playerOutOfCards!=-1){
            if(handList.get(playerOutOfCards).hasSaidMao){
                if(playerOutOfCards==0){
                    System.out.println("I win!");
                    return playerOutOfCards;
                }else{
                    System.out.println("Player "+playerOutOfCards+" wins!");
                    return playerOutOfCards;
                }
            }else{
                System.out.println("Failure to say \"Mao.\"");
                handList.get(playerOutOfCards).draw(2,deck,discard);
                playerOutOfCards=-1;
            }
        }else if(hand.hasSaidMao){
            System.out.println("Unnecessarily saying \"Mao.\"");
            hand.draw(1,deck,discard);
        }
        for(int index=0;index<players;index++){
            handList.get(index).hasSaidMao=false;
        }
        if(!said.matches("[\\s\\.]*")){
            System.out.println("Speaking out of turn (card given).");
            hand.draw(1,deck,discard);
        }
        System.out.println("Press enter to continue.");
        return -1;
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
    public static String readLine(String fileName,int lineNum){
        File file = new File(fileName);
        String line="";
        try{
            Scanner read = new Scanner(file);
            int i=0;
            while(read.hasNextLine()&&i<lineNum){
                line=read.nextLine();
                i++;
            }
            if(i<lineNum){
                return "!line";
            }
        }catch(FileNotFoundException error){
            return "!file";
        }
        return line;
    }
    public static int countLines(String fileName){
        File file = new File(fileName);
        String line;
        int lines=0;
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                line=read.nextLine();
                lines++;
            }
        }catch(FileNotFoundException error){
            return -1;
        }
        return lines;
    }
    public static String getRulesFile(){
        Scanner input = new Scanner(System.in);
        System.out.println("Use default rules? y/n");
        String defRules="";
        while(defRules.indexOf("y")!=0&&defRules.indexOf("n")!=0){
            defRules=input.next();
        }
        String fileName="";
        if(defRules.charAt(0)=='y'){
            fileName="rules.txt";
        }else{
            while(!fileName.matches("\\w+\\.txt")){
                fileName=input.next();
            }
        }
        return fileName;
    }
}