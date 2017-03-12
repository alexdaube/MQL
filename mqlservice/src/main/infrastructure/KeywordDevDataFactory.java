package infrastructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import configuration.keywords.*;
import infrastructure.deserializers.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class KeywordDevDataFactory {


    public KeywordDevDataFactory() {

    }

    public EntityMap readEntitiesFromJSON() {
        EntityMap entities = new EntityMap();
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(GeneralKeyword.class, new GeneralKeywordDeserializer());
            gsonBuilder.registerTypeAdapter(EntityKeyword.class, new EntityKeywordDeserializer());
            gsonBuilder.registerTypeAdapter(EntityMap.class, new EntitiesDeserializer());
            Gson gson = gsonBuilder.create();

            FileReader fileReader = new FileReader("./src/main/configuration/entities_config.json");
            BufferedReader br = new BufferedReader(fileReader);

            entities = gson.fromJson(br, EntityMap.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return entities;
    }

}
