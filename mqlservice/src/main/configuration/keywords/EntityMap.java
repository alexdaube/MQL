package configuration.keywords;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EntityMap {

    private Map<EntityKeyword, Set<GeneralKeyword>> entities;

    public EntityMap() {
        this.entities = new HashMap<>();
    }

    public void addEntity(EntityKeyword entityKeyword) {
        this.entities.put(entityKeyword, getKeywordsFromEntity(entityKeyword));
    }

    public Set<GeneralKeyword> getKeywordsFromEntity(EntityKeyword entityKeyword) {
        Set<GeneralKeyword> keywords = new HashSet<>();
        entityKeyword.getAttributes().forEach(keywords::add);
        return keywords;
    }

    public Set<EntityKeyword> getEntityKeywords() {
        Set<EntityKeyword> entities = new HashSet<>();
        this.entities.keySet().forEach(entities::add);
        return entities;
    }

}
