package infrastructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.keyword.EntityKeyword;
import domain.keywords.EntityMap;
import domain.keyword.GeneralKeyword;
import infrastructure.deserializers.EntitiesDeserializer;
import infrastructure.deserializers.EntityKeywordDeserializer;
import infrastructure.deserializers.GeneralKeywordDeserializer;

import java.io.*;

public class KeywordDevDataFactory {

    private EntityMap entities;

    public KeywordDevDataFactory() {
        this.entities = new EntityMap();
    }

    public EntityMap readEntitiesFromJSON() {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(GeneralKeyword.class, new GeneralKeywordDeserializer());
            gsonBuilder.registerTypeAdapter(EntityKeyword.class, new EntityKeywordDeserializer());
            gsonBuilder.registerTypeAdapter(EntityMap.class, new EntitiesDeserializer());
            Gson gson = gsonBuilder.create();

            FileReader fileReader = new FileReader("./mqlservice/src/main/configuration/entities_config.json");
            BufferedReader br = new BufferedReader(fileReader);

            this.entities = gson.fromJson(br, EntityMap.class);
            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this.entities;
    }
}
