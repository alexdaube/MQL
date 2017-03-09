package infrastructure.deserializers;

import com.google.gson.*;
import configuration.keywords.GeneralKeyword;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class GeneralKeywordDeserializer implements JsonDeserializer<GeneralKeyword> {

    @Override
    public GeneralKeyword deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        return extractObjectFromJSON(jsonObject);
    }

    private GeneralKeyword extractObjectFromJSON(JsonObject jsonObject) {
        final JsonElement jsonKeyword = jsonObject.get("keyword");
        final String keyword = jsonKeyword.getAsString();
        final Set<String> synonyms = extractKeywordSynonyms(jsonObject);

        GeneralKeyword generalKeyword = new GeneralKeyword();
        generalKeyword.setKeyword(keyword);
        generalKeyword.setSynonyms(synonyms);

        return generalKeyword;
    }

    private Set<String> extractKeywordSynonyms(JsonObject jsonObject) {
        final JsonArray jsonSynonyms = jsonObject.get("synonyms").getAsJsonArray();
        final Set<String> synonyms = new HashSet<>();

        for (JsonElement jsonElement : jsonSynonyms) {
            synonyms.add(jsonElement.getAsString());
            System.out.println(jsonElement.getAsString());
        }

        return synonyms;
    }
}
