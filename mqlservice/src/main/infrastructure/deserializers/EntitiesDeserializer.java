package infrastructure.deserializers;

import com.google.gson.*;
import domain.keyword.EntityKeyword;
import domain.keywords.EntityMap;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class EntitiesDeserializer implements JsonDeserializer<EntityMap> {
    @Override
    public EntityMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();

        EntityKeyword[] entityKeywords = context.deserialize(jsonObject.get("entities"), EntityKeyword[].class);

        final Set<EntityKeyword> entities = extractEntities(entityKeywords);

        final EntityMap entityMap = new EntityMap();

        for (EntityKeyword entity : entities){
            entityMap.addEntity(entity);
        }

        return entityMap;
    }

    private Set<EntityKeyword> extractEntities(EntityKeyword[] entityKeywords) {
        final Set<EntityKeyword> entities = new HashSet<>();

        for (EntityKeyword keyword : entityKeywords) {
            entities.add(keyword);
        }

        return entities;
    }
}
