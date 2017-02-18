package domain.keywords;

import domain.keyword.EntityKeyword;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EntityMap {

    private Map<EntityKeyword, Set<String>> entities;

    public EntityMap() {
        this.entities = new HashMap<>();
    }

    public void addEntity(EntityKeyword entityKeyword) {
        this.entities.put(entityKeyword, getKeywordsFromEntity(entityKeyword));
    }

    //TODO Only attributes and entity compatible for now
    public Set<String> getKeywordsFromType(Keywords.Type type) {
        if (type == Keywords.Type.ATTRIBUTE) {
            return getAttributesKeywords();
        }
        return getEntityKeywords();
    }

    private Set<String> getKeywordsFromEntity(EntityKeyword entityKeyword) {
        Set<String> keywords = new HashSet<>();
        entityKeyword.getAttributes().forEach(keyword -> keywords.add(keyword.getKeyword()));
        return keywords;
    }

    private Set<String> getEntityKeywords() {
        Set<String> entities = new HashSet<>();
        this.entities.keySet().forEach(entityKeyword -> entities.add(entityKeyword.getKeyword()));
        return entities;
    }

    private Set<String> getAttributesKeywords() {
        Set<String> attributes = new HashSet<>();
        this.entities.values().forEach(keywordsSet -> attributes.addAll(keywordsSet));
        return attributes;
    }
}
