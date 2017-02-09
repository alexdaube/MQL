package domain.keyword;

public interface Keyword {

    String getKeyword();

    boolean isSubsetOf(Keyword keyword);

}
