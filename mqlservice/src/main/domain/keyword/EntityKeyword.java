package domain.keyword;

public class EntityKeyword implements Keyword {

    private String keyword;

    public EntityKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public boolean isSubsetOf(Keyword keyword) {
        return false;
    }
}
