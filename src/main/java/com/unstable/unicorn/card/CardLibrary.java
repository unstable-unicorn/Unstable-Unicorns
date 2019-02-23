package com.unstable.unicorn.card;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class CardLibrary {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            URL cardYmlFileLocation = CardLibrary.class.getResource("/cards/cards.yml");
            UnicornCard unicornCard = mapper.readValue(new File(cardYmlFileLocation.toURI()), UnicornCard.class);
            System.out.println(ReflectionToStringBuilder.toString(unicornCard ,ToStringStyle.MULTI_LINE_STYLE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CardLibrary instance;
    private HashMap<Integer, Card> library;

    public CardLibrary() {
        instantiateCards();
    }

    private void instantiateCards() {
        if (library == null) {
            library = new HashMap<>();
        }
    }

    public static CardLibrary getInstance() {
        if (instance == null) {
            instance = new CardLibrary();
        }

        return instance;
    }
}
