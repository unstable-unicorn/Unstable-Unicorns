package com.unstable.unicorn.Deck;

import com.unstable.unicorn.card.Card;
import com.unstable.unicorn.card.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private static Random random = new Random();
    private ArrayList<Card> Cards = new ArrayList<Card>();

    public Card drawCard(){
        if (Cards.isEmpty()) {
            return null;
        } else {
            return Cards.remove(Cards.size()-1);
        }
    }

    public ArrayList<Card> drawCards(int numberOfCard){
        ArrayList<Card> cardsDrew = new ArrayList<Card>();
        for (int i = 0; i < numberOfCard; i++){
            if (Cards.isEmpty()){ break; }
            else { cardsDrew.add(Cards.remove(Cards.size()-1)); }
        }
        return cardsDrew;
    }

    public Card drawCardWithExactName(String name) {
        for(int i=0; i < Cards.size(); i++){
            if (Cards.get(i).getName().equals(name)) {
                return Cards.remove(i);
            }
        }
        return null;
    }

    public void shuffle() {
        if (Cards.isEmpty()){
            return;
        }
        for (int sourcePos = Cards.size()-1; sourcePos > 0; sourcePos--){
            //generate destination position with bound 0<=destinationPos<=sourcePos
            int destinationPos = random.nextInt(sourcePos+1);
            Collections.swap(Cards, sourcePos, destinationPos);
        }
    }

    public void shuffleIntoDeck(Card card){
        if (Cards.isEmpty()){
            Cards.add(card);
            return;
        }
        Cards.add(random.nextInt(Cards.size()+1), card);
    }

    public void shuffleIntoDeck(ArrayList<Card> cardList){
        if (cardList.isEmpty()) {
            return;
        }
        if(Cards.isEmpty()) {
            Cards.add(cardList.remove(0));
        }
        for(Card card : cardList){
            Cards.add(random.nextInt(Cards.size()+1), card);
        }
    }

    /**
     * This method does not remove cards from the deck
     * It returns an ArrayList of Cards that matched the keyword
     * @param keyword
     * @return ArrayList of Card with keyword in Name
     */
    public ArrayList<Card> getCardsWithKeyword(String keyword){
        ArrayList<Card> candidates = new ArrayList<>();
        for(Card card: Cards){
            if (card.getName().contains(keyword)){
                candidates.add(card);
            }
        }
        return candidates;
    }

    /**
     * This method does not remove cards from the deck
     * It returns an ArrayList of Cards of the parameter type
     * @param type
     * @return ArrayList of Card of Type type
     */
    public ArrayList<Card> getCardsOfType(Type type){
        ArrayList<Card> candidates = new ArrayList<>();
        for(Card card : Cards){
            if(card.getType() == type){
                candidates.add(card);
            }
        }
        return candidates;
    }

    public boolean containCardWithKeyword(String keyword) {
        for (Card card : Cards) {
            if (card.getName().contains(keyword)){
                return true;
            }
        }
        return false;
    }

    public int getNumOfCards(){
        return Cards.size();
    }

    @Override
    public String toString() {
        StringBuilder deckStringBuilder = new StringBuilder();
        for (Card card : Cards){
            deckStringBuilder.append(card.toString() + "\n");
        }
        return deckStringBuilder.toString();
    }
}

