package domain.keywords;


import domain.keyword.EntityKeyword;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EntityMap {

    private Map<EntityKeyword, KeywordsSet> entities;

    public EntityMap() {
        this.entities = new HashMap<>();
    }

    public void addEntity(EntityKeyword entityKeyword) {
        KeywordsSet associatedKeywords = new KeywordsSet(getKeywordsFromEntity(entityKeyword));
        this.entities.put(entityKeyword, associatedKeywords);
    }

    private Set<String> getKeywordsFromEntity(EntityKeyword entityKeyword) {
        Set<String> keywords = new HashSet<>();
        entityKeyword.getAttributes().forEach(keyword -> keywords.add(keyword.getKeyword()));
        return keywords;
    }

    public Set<String> getEntityKeywords() {
        Set<String> entities = new HashSet<>();
        this.entities.keySet().forEach(entityKeyword -> entities.add(entityKeyword.getKeyword()));
        return entities;
    }

    public Set<String> getAttributesKeywords() {
        Set<String> attributes = new HashSet<>();
        this.entities.values().forEach(keywordsSet -> attributes.addAll(keywordsSet.getKeywords()));
        return attributes;
    }
}
