package domain.keyword;

public class AttributeKeyword implements Keyword {

    private String keyword;
    private Keyword parentKeyword;

    public AttributeKeyword(String keyword, Keyword parentKeyword) {
        this.keyword = keyword;
        this.parentKeyword = parentKeyword;
    }

    @Override
    public String getKeyword() {
        return this.keyword;
    }

    @Override
    public boolean isSubsetOf(Keyword keyword) {
        return this.parentKeyword.equals(keyword);
    }
}
