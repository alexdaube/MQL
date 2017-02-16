package infrastructure.deserializers;

import com.google.gson.*;
import domain.keyword.EntityKeyword;
import domain.keyword.GeneralKeyword;
import domain.keyword.Keyword;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class EntityKeywordDeserializer implements JsonDeserializer<EntityKeyword> {
    @Override
    public EntityKeyword deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        GeneralKeyword[] generalKeywords = context.deserialize(jsonObject.get("attributes"), GeneralKeyword[].class);
        final Set<Keyword> keywords = extractAttributes(generalKeywords);

        final EntityKeyword entityKeyword = extractObjectFromJSON(jsonObject);
        entityKeyword.setAttributes(keywords);
        return entityKeyword;
    }


    private EntityKeyword extractObjectFromJSON(JsonObject jsonObject) {
        final JsonElement jsonKeyword = jsonObject.get("keyword");
        final String keyword = jsonKeyword.getAsString();
        final Set<String> synonyms = extractKeywordSynonyms(jsonObject);

        EntityKeyword entityKeyword = new EntityKeyword();
        entityKeyword.setKeyword(keyword);
        entityKeyword.setSynonyms(synonyms);

        return entityKeyword;
    }

    private Set<String> extractKeywordSynonyms(JsonObject jsonObject) {
        final JsonArray jsonSynonyms = jsonObject.get("synonyms").getAsJsonArray();
        final Set<String> synonyms = new HashSet<>();

        for (JsonElement jsonElement : jsonSynonyms) {
            synonyms.add(jsonElement.getAsString());
        }

        return synonyms;
    }

    private Set<Keyword> extractAttributes(GeneralKeyword[] generalKeywords) {
        final Set<Keyword> attributes = new HashSet<>();
        for (Keyword keyword : generalKeywords) {
            attributes.add(keyword);
        }
        return attributes;
    }
}
