package infrastructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.keyword.EntityKeyword;
import domain.keyword.GeneralKeyword;
import domain.keyword.EntityList;
import infrastructure.deserializers.EntitiesDeserializer;
import infrastructure.deserializers.EntityKeywordDeserializer;
import infrastructure.deserializers.GeneralKeywordDeserializer;

import java.io.*;

public class KeywordDevDataFactory {

    private EntityList entities;

    public KeywordDevDataFactory() {
        this.entities = new EntityList();
    }

    public EntityList readEntitiesFromJSON() {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(GeneralKeyword.class, new GeneralKeywordDeserializer());
            gsonBuilder.registerTypeAdapter(EntityKeyword.class, new EntityKeywordDeserializer());
            gsonBuilder.registerTypeAdapter(EntityList.class, new EntitiesDeserializer());
            Gson gson = gsonBuilder.create();

            FileReader fileReader = new FileReader("./mqlservice/src/main/configuration/entities_config.json");
            BufferedReader br = new BufferedReader(fileReader);

            this.entities = gson.fromJson(br, EntityList.class);
            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return this.entities;
    }
}
