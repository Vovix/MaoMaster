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
    private List<Character> triggerSuit = new ArrayList<Character>(); // list of suits that trigger the rule    
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
    public Rule(List<Character> triggerSuit,List<Integer> triggerValue,List<Character> triggerLastSuit,List<Integer> triggerLastValue,int triggerSameValue,int triggerSameSuit,boolean and,String haveToSay)
    {
        this.triggerSuit=triggerSuit;
        this.triggerValue=triggerValue;
        this.triggerLastSuit=triggerLastSuit;
        this.triggerLastValue=triggerLastValue;
        this.triggerSameValue=triggerSameValue;
        this.triggerSameSuit=triggerSameSuit;
        this.and=and;
        this.haveToSay=haveToSay;
    }
    private boolean trigger(Card played, Card previous){
        if (and){
            if (!triggerSuit.contains(played.suit())){ //check suit
                return false;
            }
            if (!triggerValue.contains(played.value())){ //check value
                return false;
            }
            if(previous!=null){
                if (!triggerLastSuit.contains(previous.suit())){ //check last suit
                    return false;
                }
                if (!triggerLastValue.contains(previous.value())){ //check last value
                    return false;
                }
                if (triggerSameValue==1&&played.value()!=previous.value()){//check if values are the same
                    return false;
                }
                if (triggerSameValue==-1&&played.value()==previous.value()){//check if values are different
                    return false;
                }
                if (triggerSameSuit==2&&played.suit()!=previous.suit()){//check if colors are the same
                    return false;
                }
                if (triggerSameSuit==-2&&played.suit()==previous.suit()){//check if colors are different
                    return false;
                }
                if (triggerSameSuit==1&&played.suit()!=previous.suit()){//check if suits are the same
                    return false;
                }
                if (triggerSameSuit==-1&&played.suit()==previous.suit()){//check if suits are different
                    return false;
                }
            }
            return true;
        }else{
            if (triggerSuit.contains(played.suit())){ //check suit
                return true;
            }
            if (triggerValue.contains(played.value())){ //check value
                return true;
            }
            if(previous!=null){
                if (triggerLastSuit.contains(previous.suit())){ //check last suit
                    return true;
                }
                if (triggerLastValue.contains(previous.value())){ //check last value
                    return true;
                }
                if (triggerSameValue==1&&played.value()==previous.value()){//check if values are the same
                    return true;
                }
                if (triggerSameValue==-1&&played.value()!=previous.value()){//check if values are different
                    return true;
                }
                if (triggerSameSuit==2&&played.suit()==previous.suit()){//check if colors are the same
                    return true;
                }
                if (triggerSameSuit==-2&&played.suit()!=previous.suit()){//check if colors are different
                    return true;
                }
                if (triggerSameSuit==1&&played.suit()==previous.suit()){//check if suits are the same
                    return true;
                }
                if (triggerSameSuit==-1&&played.suit()!=previous.suit()){//check if suits are different
                    return true;
                }
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
    public String[] check(Card played, Card previous, String said, int prevSameVal)
    {
        boolean failureToSay=false;
        String haveToSayTemp;
        String failedToSayStr="Failure to ";
        String[] res=new String[3];
        haveToSayTemp=haveToSay;
        if (!trigger(played,previous)) {
            res[0]=said;
            res[1]="false";
            res[2]="true";
            return res;
        }
        if(haveToSay.matches("\\*[cp](Val|Suit|Name)")){
            if(haveToSay.charAt(1)=='c'){
                if(haveToSay.charAt(2)=='V'){
                    haveToSayTemp=String.valueOf(played.value());
                    failedToSayStr=failedToSayStr+"say value of ";
                }
                if(haveToSay.charAt(2)=='S'){
                    haveToSayTemp=String.valueOf(played.suit());
                    failedToSayStr=failedToSayStr+"say suit of ";
                }
                if(haveToSay.charAt(2)=='N'){
                    haveToSayTemp=played.fullName();
                    failedToSayStr=failedToSayStr+"name ";
                }
                failedToSayStr=failedToSayStr+"card (card given).";
            }
            if(haveToSay.charAt(1)=='p'){
                if(previous!=null){
                    if(haveToSay.charAt(2)=='V'){
                        haveToSayTemp=String.valueOf(previous.value());
                        failedToSayStr=failedToSayStr+"say value of ";
                    }
                    if(haveToSay.charAt(2)=='S'){
                        haveToSayTemp=String.valueOf(previous.suit());
                        failedToSayStr=failedToSayStr+"say suit of ";
                    }
                    if(haveToSay.charAt(2)=='N'){
                        haveToSayTemp=previous.fullName();
                        failedToSayStr=failedToSayStr+"name ";
                    }
                    failedToSayStr=failedToSayStr+"previous card (card given).";
                }else{
                    haveToSayTemp="";
                }
            }
        }else{
            if(haveToSay.replaceAll("[^~]","").length()==2){
                String[] haveToSaySplit=haveToSay.split("~");
                haveToSayTemp=haveToSaySplit[0];
                for(int i=0;i<prevSameVal;i++){
                    haveToSayTemp=haveToSayTemp+haveToSaySplit[1];
                }
                if(haveToSaySplit.length==3){
                    haveToSayTemp=haveToSayTemp+haveToSaySplit[2];
                }
            }
            if(haveToSayTemp.matches("\\*+")) failedToSayStr=failedToSayStr+"say \""+haveToSayTemp+"\" (card given).";
            else failedToSayStr=failedToSayStr+"knock (card given).";
        }
        if (!haveToSayTemp.equals("")&&!said.toLowerCase().contains(haveToSayTemp.toLowerCase())){
            failureToSay=true;
        }else if(said.toLowerCase().contains(haveToSayTemp.toLowerCase())){
            int indOfStr=said.indexOf(haveToSayTemp.toLowerCase());
            said=said.substring(0,indOfStr)+said.substring(indOfStr+haveToSayTemp.length(),said.length());
        }
        if(failureToSay){
            if(haveToSayTemp.matches("\\*+")){
                System.out.print("Failure to knock");
                if(prevSameVal==2){
                    System.out.print(" twice");
                    if(said.contains("*")){
                        int asterInd=said.indexOf("*");
                        said=said.substring(0,asterInd)+said.substring(asterInd+1,said.length());
                    }
                }
                if(prevSameVal==3){
                    System.out.print(" three times");
                    if(said.contains("**")){
                        int asterInd=said.indexOf("**");
                        said=said.substring(0,asterInd)+said.substring(asterInd+2,said.length());
                    }else if(said.contains("*")){
                        int asterInd=said.indexOf("*");
                        said=said.substring(0,asterInd)+said.substring(asterInd+1,said.length());
                    }
                }
                System.out.println(" (card given).");
            }else System.out.println(failedToSayStr);
        }
        while(said.contains("  ")){
            said=said.substring(0,said.indexOf("  "))+" "+said.substring(said.indexOf("  ")+2,said.length());
        }
        if(said.equals(" ")) said="";
        if(haveToSay.equals("")){
            res[0]=said;
            res[1]="false";
            res[2]="false"; // {said,draw,valid play} all triggers mean invalid plays
            return res;
        }
        res[0]=said;
        res[1]=String.valueOf(failureToSay);
        res[2]="true"; // {said,draw,valid play} triggers only mean haveToSay must be said
        return res;
    }
}
