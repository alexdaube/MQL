package services.keyword;

import domain.keywords.Keyword;
import domain.keywords.Keywords;

public interface KeywordsTypeRegistrar {
     KeywordsRegistrar as(Keyword.Type type);
}
