package infrastructure.deserializers;

import com.google.gson.*;
import domain.keyword.EntityKeyword;
import domain.keyword.GeneralKeyword;
import domain.keyword.Keyword;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EntityKeywordDeserializer implements JsonDeserializer<EntityKeyword> {
    @Override
    public EntityKeyword deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        GeneralKeyword[] generalKeywords = context.deserialize(jsonObject.get("attributes"), GeneralKeyword[].class);
        final List<Keyword> keywords = extractAttributes(generalKeywords);

        final EntityKeyword entityKeyword = extractObjectFromJSON(jsonObject);
        entityKeyword.setAttributes(keywords);
        return entityKeyword;
    }


    private EntityKeyword extractObjectFromJSON(JsonObject jsonObject) {
        final JsonElement jsonKeyword = jsonObject.get("keyword");
        final String keyword = jsonKeyword.getAsString();
        final List<String> synonyms = extractKeywordSynonyms(jsonObject);

        EntityKeyword entityKeyword = new EntityKeyword();
        entityKeyword.setKeyword(keyword);
        entityKeyword.setSynonyms(synonyms);

        return entityKeyword;
    }

    private List<String> extractKeywordSynonyms(JsonObject jsonObject) {
        final JsonArray jsonSynonyms = jsonObject.get("synonyms").getAsJsonArray();
        final List<String> synonyms = new ArrayList<>();

        for (JsonElement jsonElement : jsonSynonyms) {
            synonyms.add(jsonElement.getAsString());
        }

        return synonyms;
    }

    private List<Keyword> extractAttributes(GeneralKeyword[] generalKeywords) {
        final List<Keyword> attributes = new ArrayList<>();
        for (Keyword keyword : generalKeywords) {
            attributes.add(keyword);
        }
        return attributes;
    }
}
