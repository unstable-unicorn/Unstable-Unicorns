package com.unstable.unicorn.card;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardLibrary {
    private static Logger logger = LoggerFactory.getLogger(CardLibrary.class);

    private static CardLibrary instance;
    private HashMap<Integer, UnicornCard> unicornCards;

    /**
     * I have decided to use YML property file to store all properties of cards.
     * Instead of looking them up inside a MYSQL database.
     * Retrieve all (Unicorn, Magic, Instant) cards from YAML properties files.
     * Store all of the cards into each of the HashMaps, this allows O(1) lookup.
     */
    private void instantiateCards() {
        getAllUnicornCards();
    }

    @SuppressWarnings("unchecked")
    private void getAllUnicornCards() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            URL cardYmlFileLocation = CardLibrary.class.getResource("/cards/unicorn-cards.yml");
            Map<String, List<UnicornCard>> unicornCard = mapper.readValue(new File(cardYmlFileLocation.toURI()), Map.class);

            for(Map.Entry<String, List<UnicornCard>> entry : unicornCard.entrySet()) {
                List<UnicornCard> values = entry.getValue();
                if(values != null) {
                    for (Object value : values) {
                        System.out.println(String.valueOf(value));
                    }
                }
            }
        } catch (Exception e) {
            logger.error("getAllUnicornCards() -> Failed to retrieve unicorn cards from property file");
        }
    }

    public static CardLibrary getInstance() {
        if (instance == null) {
            instance = new CardLibrary();
        }

        return instance;
    }
}
