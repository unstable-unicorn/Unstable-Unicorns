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
            else { cardsDrew.add(cardIDs.remove(cardIDs.size())); }
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

    public void shuffleIntoDeck(Integer cardID){
        if (cardIDs.isEmpty()){
            cardIDs.add(cardID);
            return;
        }
        cardIDs.add(random.nextInt(cardIDs.size()+1), cardID);
    }

    public void shuffleIntoDeck(ArrayList<Integer> cardIDList){
        if (cardIDList.isEmpty()) {
            return;
        }
        if(cardIDs.isEmpty()) {
            cardIDs.add(cardIDList.remove(0));
        }
        for(Integer cardID : cardIDList){
            cardIDs.add(random.nextInt(cardIDs.size()+1), cardID);
        }
    }

    /**
     * This method does not remove cardIDs from the deck
     * It returns an ArrayList of Integer of cards that
     * matched the keyword
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
     * This method does not remove cardIDs from the deck
     * It returns an ArrayList of Integer of the cards that
     * matches the parameter type
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
        for (Integer cardID : cardIDs){
            deckStringBuilder.append(cardLibrary.getCardNameWithID(cardID));
            deckStringBuilder.append("\n");
        }
        return deckStringBuilder.toString();
    }
}

