package com.unstable.unicorn.card;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CardLibrary {
    private static Logger logger = LoggerFactory.getLogger(CardLibrary.class);

    private static CardLibrary instance;
    private HashMap<Integer, UnicornCard> unicornCards;

    private CardLibrary(){}

    /**
     * I have decided to use YML property file to store all properties of cards.
     * Instead of looking them up inside a MYSQL database.
     * Retrieve all (Unicorn, Magic, Instant) cards from YAML properties files.
     * Store all of the cards into each of the HashMaps, this allows O(1) lookup.
     */
    private void instantiateCards() {
        getAllUnicornCards();
    }

    /**
     * Retrieve the list of attributes for unicorns from "unicorn-cards.yml" file.
     * Parse the List of LinkedHashMap. The list is basically the list of unicorns and the LinkedHashMap
     * contains all of the properties for a single unicorn.
     * Using the properties retrieved from the HashMap, create a new Unicorn class and store it in our Unicorn Cards.
     */
    @SuppressWarnings("unchecked")
    private void getAllUnicornCards() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            URL cardYmlFileLocation = CardLibrary.class.getResource("/cards/unicorn-cards.yml");
            Map<String, List<LinkedHashMap<String, String>>> listMap = mapper.readValue(new File(cardYmlFileLocation.toURI()), Map.class);

            for (Map.Entry<String, List<LinkedHashMap<String, String>>> entry : listMap.entrySet()) {
                List<LinkedHashMap<String, String>> values = entry.getValue();
                // parse all of the attributes in the HashMap
                // and store them inside an UnicornCard class, then add the unicorn class to the HashMap
                for (LinkedHashMap<String, String> attributes : values) {
                    UnicornCard unicornCard = new UnicornCard();
                    unicornCard.setId(Integer.parseInt(attributes.get("id")));
                    unicornCard.setName(attributes.get("name"));
                    unicornCard.setType(Type.valueOf(attributes.get("type")));
                    unicornCard.setEffect(attributes.get("effect"));
                    unicornCard.setDescription(attributes.get("description"));
                    unicornCard.setBasic(Boolean.valueOf(attributes.get("basic")));
                    unicornCard.setMagical(Boolean.valueOf(attributes.get("magical")));
                    unicornCard.setBaby(Boolean.valueOf(attributes.get("baby")));

                    this.unicornCards.put(unicornCard.getId(), unicornCard);
                }
            }
        } catch (Exception e) {
            logger.error("getAllUnicornCards() -> Failed to retrieve unicorn cards from property file");
            e.printStackTrace();
        }
    }

    public static CardLibrary getInstance() {
        if (instance == null) {
            instance = new CardLibrary();
        }

        return instance;
    }

    public Card getCardWithID(int id){
        return unicornCards.get(id);
    }

    public String getCardNameWithID(int id){
        return getCardWithID(id).getName();
    }

    public Type getCardTypeWithID(int id){
        return getCardWithID(id).getType();
    }
}
