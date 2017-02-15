package domain.keyword;


import java.util.ArrayList;
import java.util.List;

public class EntityList {

    private List<Keyword> entities;

    public EntityList() {
        this.entities = new ArrayList<>();
    }

    public void setEntities(List<Keyword> entities) {
        this.entities = entities;
    }

    public void addEntity(EntityKeyword entityKeyword) {
        this.entities.add(entityKeyword);
    }

    public List<Keyword> getEntities(){
        return this.entities;
    }
}
