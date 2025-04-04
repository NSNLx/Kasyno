import java.util.ArrayList;
import java.util.Random;

public class Card{
    private String figure;
    private int power;
    private static ArrayList<Card>cards=new ArrayList<>();
    public String getFigure(){
        return figure;
    }
    public String fileName(){
        Random random=new Random();
        int randomCard=random.nextInt(4);
        if(getFigure().equals("2")){
            if(randomCard==0){
                return "2_of_hearts.png";
            }
            if(randomCard==1){
                return "2_of_diamonds.png";
            }
            if(randomCard==2){
                return "2_of_clubs.png";
            }
            if(randomCard==3){
                return "2_of_spades.png";
            }
        }
        if(getFigure().equals("3")){
            if(randomCard==0){
                return "3_of_hearts.png";
            }
            if(randomCard==1){
                return "3_of_diamonds.png";
            }
            if(randomCard==2){
                return "3_of_clubs.png";
            }
            if(randomCard==3){
                return "3_of_spades.png";
            }
        }
        if(getFigure().equals("5")){
            if(randomCard==0){
                return "5_of_hearts.png";
            }
            if(randomCard==1){
                return "5_of_diamonds.png";
            }
            if(randomCard==2){
                return "5_of_clubs.png";
            }
            if(randomCard==3){
                return "5_of_spades.png";
            }
        }
        if(getFigure().equals("6")){
            if(randomCard==0){
                return "6_of_hearts.png";
            }
            if(randomCard==1){
                return "6_of_diamonds.png";
            }
            if(randomCard==2){
                return "6_of_clubs.png";
            }
            if(randomCard==3){
                return "6_of_spades.png";
            }
        }
        if(getFigure().equals("7")){
            if(randomCard==0){
                return "7_of_hearts.png";
            }
            if(randomCard==1){
                return "7_of_diamonds.png";
            }
            if(randomCard==2){
                return "7_of_clubs.png";
            }
            if(randomCard==3){
                return "7_of_spades.png";
            }
        }
        if(getFigure().equals("8")){
            if(randomCard==0){
                return "8_of_hearts.png";
            }
            if(randomCard==1){
                return "8_of_diamonds.png";
            }
            if(randomCard==2){
                return "8_of_clubs.png";
            }
            if(randomCard==3){
                return "8_of_spades.png";
            }
        }
        if(getFigure().equals("9")){
            if(randomCard==0){
                return "9_of_hearts.png";
            }
            if(randomCard==1){
                return "9_of_diamonds.png";
            }
            if(randomCard==2){
                return "9_of_clubs.png";
            }
            if(randomCard==3){
                return "9_of_spades.png";
            }
        }
        if(getFigure().equals("10")){
            if(randomCard==0){
                return "10_of_hearts.png";
            }
            if(randomCard==1){
                return "10_of_diamonds.png";
            }
            if(randomCard==2){
                return "10_of_clubs.png";
            }
            if(randomCard==3){
                return "10_of_spades.png";
            }
        }
        if(getFigure().equals("Jack")){
            if(randomCard==0){
                return "jack_of_hearts.png";
            }
            if(randomCard==1){
                return "jack_of_diamonds.png";
            }
            if(randomCard==2){
                return "jack_of_clubs.png";
            }
            if(randomCard==3){
                return "jack_of_spades.png";
            }
        }
        if(getFigure().equals("Queen")){
            if(randomCard==0){
                return "queen_of_hearts.png";
            }
            if(randomCard==1){
                return "queen_of_diamonds.png";
            }
            if(randomCard==2){
                return "queen_of_clubs.png";
            }
            if(randomCard==3){
                return "queen_of_spades.png";
            }
        }
        if(getFigure().equals("King")){
            if(randomCard==0){
                return "king_of_hearts.png";
            }
            if(randomCard==1){
                return "king_of_diamonds.png";
            }
            if(randomCard==2){
                return "king_of_clubs.png";
            }
            if(randomCard==3){
                return "king_of_spades.png";
            }
        }
        if(getFigure().equals("Ace")){
            if(randomCard==0){
                return "ace_of_hearts.png";
            }
            if(randomCard==1){
                return "ace_of_diamonds.png";
            }
            if(randomCard==2){
                return "ace_of_clubs.png";
            }
            if(randomCard==3){
                return "ace_of_spades.png";
            }
        }
        if(getFigure().equals("Ace")){
            if(randomCard==0){
                return "ace_of_hearts.png";
            }
            if(randomCard==1){
                return "ace_of_diamonds.png";
            }
            if(randomCard==2){
                return "ace_of_clubs.png";
            }
            if(randomCard==3){
                return "ace_of_spades.png";
            }
        }
        return "black_joker.png";
    }
    public int getPower(){
        return power;
    }
    public Card(String f, int p){
        figure=f;
        power=p;
    }
    public static void initializeCards() {
        cards.add(new Card("2", 2));
        cards.add(new Card("3", 3));
        cards.add(new Card("5", 5));
        cards.add(new Card("6", 6));
        cards.add(new Card("7", 7));
        cards.add(new Card("8", 8));
        cards.add(new Card("9", 9));
        cards.add(new Card("10", 10));
        cards.add(new Card("Jack", 10));
        cards.add(new Card("Queen", 10));
        cards.add(new Card("King", 10));
        cards.add(new Card("Ace", 1));
    }
    public static Card getRandomCard(){
        Random randomCard=new Random();
        int random=randomCard.nextInt(cards.size());
        return cards.get(random);
    }
}