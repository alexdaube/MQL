package domain.keyword;

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
        entityKeyword.getAttributes().forEach(keyword -> keywords.add(keyword));
        return keywords;
    }

    public Set<EntityKeyword> getEntityKeywords() {
        Set<EntityKeyword> entities = new HashSet<>();
        this.entities.keySet().forEach(entityKeyword -> entities.add(entityKeyword));
        return entities;
    }

    private Set<KeywordConfig> getAttributesKeywords() {
        Set<KeywordConfig> attributes = new HashSet<>();
        this.entities.values().forEach(keywordsSet -> attributes.addAll(keywordsSet));
        return attributes;
    }

}
