package com.unstable.unicorn.deck;

import com.unstable.unicorn.card.CardLibrary;
import com.unstable.unicorn.card.Type;
import com.unstable.unicorn.exceptions.DeckEmptyException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private static Random random = new Random();
    private ArrayList<Integer> cardIDs = new ArrayList<>();

    public int drawCard() throws DeckEmptyException
    {
        if (cardIDs.isEmpty()) {
            throw new DeckEmptyException();
        } else {
            return cardIDs.remove(cardIDs.size()-1);
        }
    }

    public ArrayList<Integer> drawCards(int numberOfCard) {
        ArrayList<Integer> cardsDrew = new ArrayList<>();
        for (int i = 0; i < numberOfCard; i++){
            if (cardIDs.isEmpty()){ break; }
            else { cardsDrew.add(cardIDs.remove(cardIDs.size()-1)); }
        }
        return cardsDrew;
    }

    public Integer drawCardIDWithExactName(String name) {
        CardLibrary library = CardLibrary.getInstance();
        for(int i = 0; i < cardIDs.size(); i++){
            if (library.getCardWithID(cardIDs.get(i)).getName().equals(name)) {
                return cardIDs.remove(i);
            }
        }
        return null;
    }

    public void shuffle() {
        if (cardIDs.isEmpty()){
            return;
        }
        for (int sourcePos = cardIDs.size()-1; sourcePos > 0; sourcePos--){
            //generate destination position with bound 0<=destinationPos<=sourcePos
            int destinationPos = random.nextInt(sourcePos+1);
            Collections.swap(cardIDs, sourcePos, destinationPos);
        }
    }

    public void shuffleIntoDeck(Integer card){
        if (cardIDs.isEmpty()){
            cardIDs.add(card);
            return;
        }
        cardIDs.add(random.nextInt(cardIDs.size()+1), card);
    }

    public void shuffleIntoDeck(ArrayList<Integer> cardList){
        if (cardList.isEmpty()) {
            return;
        }
        if(cardIDs.isEmpty()) {
            cardIDs.add(cardList.remove(0));
        }
        for(Integer card : cardList){
            cardIDs.add(random.nextInt(cardIDs.size()+1), card);
        }
    }

    /**
     * This method does not remove cards from the deck
     * It returns an ArrayList of Cards that matched the keyword
     * @param keyword
     * @return ArrayList of Card with keyword in Name
     */
    public ArrayList<Integer> getCardsWithKeyword(String keyword){
        CardLibrary cardLibrary = CardLibrary.getInstance();
        ArrayList<Integer> candidates = new ArrayList<>();
        for(Integer cardID: cardIDs){
            if (cardLibrary.getCardNameWithID(cardID).contains(keyword)){
                candidates.add(cardID);
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
    public ArrayList<Integer> getCardsOfType(Type type){
        CardLibrary cardLibrary = CardLibrary.getInstance();
        ArrayList<Integer> candidates = new ArrayList<>();
        for(Integer cardID : cardIDs){
            if(cardLibrary.getCardTypeWithID(cardID) == type){
                candidates.add(cardID);
            }
        }
        return candidates;
    }

    public boolean containCardWithKeyword(String keyword) {
        CardLibrary cardLibrary = CardLibrary.getInstance();
        for (Integer cardID : cardIDs) {
            if (cardLibrary.getCardNameWithID(cardID).contains(keyword)){
                return true;
            }
        }
        return false;
    }

    public int getNumOfCards(){
        return cardIDs.size();
    }

    @Override
    public String toString() {
        CardLibrary cardLibrary = CardLibrary.getInstance();
        StringBuilder deckStringBuilder = new StringBuilder();
        for (Integer card : cardIDs){
            deckStringBuilder.append(card.toString() + "\n");
        }
        return deckStringBuilder.toString();
    }
}

