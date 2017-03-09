package infrastructure.deserializers;

import com.google.gson.*;
import configuration.keywords.EntityKeyword;
import configuration.keywords.GeneralKeyword;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class EntityKeywordDeserializer implements JsonDeserializer<EntityKeyword> {
    @Override
    public EntityKeyword deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        GeneralKeyword[] generalKeywords = context.deserialize(jsonObject.get("attributes"), GeneralKeyword[].class);
        final Set<GeneralKeyword> keywords = extractAttributes(generalKeywords);

        final EntityKeyword entityKeyword = extractObjectFromJSON(jsonObject);
        entityKeyword.setAttributes(keywords);
        return entityKeyword;
    }


    private EntityKeyword extractObjectFromJSON(JsonObject jsonObject) {
        final JsonElement jsonKeyword = jsonObject.get("keywords");
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

    private Set<GeneralKeyword> extractAttributes(GeneralKeyword[] generalKeywords) {
        final Set<GeneralKeyword> attributes = new HashSet<>();
        for (GeneralKeyword keyword : generalKeywords) {
            attributes.add(keyword);
        }
        return attributes;
    }
}
