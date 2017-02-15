package infrastructure.deserializers;

import com.google.gson.*;
import domain.keyword.GeneralKeyword;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GeneralKeywordDeserializer implements JsonDeserializer<GeneralKeyword> {

    @Override
    public GeneralKeyword deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();
        return extractObjectFromJSON(jsonObject);
    }

    private GeneralKeyword extractObjectFromJSON(JsonObject jsonObject) {
        final JsonElement jsonKeyword = jsonObject.get("keyword");
        final String keyword = jsonKeyword.getAsString();
        final List<String> synonyms = extractKeywordSynonyms(jsonObject);

        GeneralKeyword generalKeyword = new GeneralKeyword();
        generalKeyword.setKeyword(keyword);
        generalKeyword.setSynonyms(synonyms);

        return generalKeyword;
    }

    private List<String> extractKeywordSynonyms(JsonObject jsonObject) {
        final JsonArray jsonSynonyms = jsonObject.get("synonyms").getAsJsonArray();
        final List<String> synonyms = new ArrayList<>();

        for (JsonElement jsonElement : jsonSynonyms) {
            synonyms.add(jsonElement.getAsString());
        }

        return synonyms;
    }
}
