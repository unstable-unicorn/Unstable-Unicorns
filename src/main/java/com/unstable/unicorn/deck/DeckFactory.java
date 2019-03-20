package com.unstable.unicorn.deck;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeckFactory {
    private static Logger logger = LoggerFactory.getLogger(DeckFactory.class);
    private static DeckFactory ourInstance = new DeckFactory();

    public static DeckFactory getInstance() {
        return ourInstance;
    }

    private DeckFactory() {
    }

    @SuppressWarnings("unchecked")
    public Deck getDeck(String deckConfigUrl){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Deck deck = new Deck();
        try{
            URL deckConfigFilePath = new URL(deckConfigUrl);
            Map<String,List<LinkedHashMap<Integer, Integer>>> listMap = mapper.readValue(
                    new File(deckConfigFilePath.toURI()), Map.class);

            for(Map.Entry<String, List<LinkedHashMap<Integer, Integer>>> entry : listMap.entrySet()){
                List<LinkedHashMap<Integer, Integer>> lists = entry.getValue();
                for(LinkedHashMap<Integer, Integer> attribute : lists){
                    int cardID = attribute.get("id");
                    int cardNumber = attribute.get("number");

                    ArrayList<Integer> cardList = new ArrayList<>();
                    if(cardNumber > 1)
                        deck.shuffleIntoDeck(buildCardList(cardID, cardNumber));
                    else
                        deck.shuffleIntoDeck(cardID);
                }
            }

        } catch (URISyntaxException e) {
            logger.error("getDeck() -> Failed to Convert URL to URI");
            e.printStackTrace();
        } catch(MalformedURLException e){
            logger.error("getDeck() -> Failed to construct URL from given String");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("getDeck() -> Failed to read the Property file");
            e.printStackTrace();
        }
        return deck;
    }

    private ArrayList<Integer> buildCardList(int cardID, int cardNumber){
        ArrayList<Integer> cardList = new ArrayList<>();
        for (int i = 0; i < cardNumber; i++){
            cardList.add(cardID);
        }
        return cardList;
    }
}
