package infrastructure.deserializers;

import com.google.gson.*;
import domain.keyword.EntityKeyword;
import domain.keyword.EntityList;
import domain.keyword.Keyword;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EntitiesDeserializer implements JsonDeserializer<EntityList> {
    @Override
    public EntityList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();

        EntityKeyword[] entityKeywords = context.deserialize(jsonObject.get("entities"), EntityKeyword[].class);

        final List<Keyword> entities = extractAttributes(entityKeywords);

        final EntityList entityList = new EntityList();
        entityList.setEntities(entities);

        return entityList;
    }

    private List<Keyword> extractAttributes(EntityKeyword[] entityKeywords) {
        final List<Keyword> attributes = new ArrayList<>();

        for (Keyword keyword : entityKeywords) {
            attributes.add(keyword);
        }

        return attributes;
    }
}
