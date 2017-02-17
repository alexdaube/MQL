package services.keyword;

import domain.keywords.Keywords;
import domain.keywords.KeywordsResolver;

public interface KeywordsValueRegistrar {
    KeywordsTypeRegistrar register(Keywords keywords);
    KeywordsResolver createKeywordsResolver();
}
