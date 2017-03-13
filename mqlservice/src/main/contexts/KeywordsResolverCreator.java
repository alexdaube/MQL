package contexts;

import domain.keywords.KeywordRepository;
import domain.keywords.KeywordsRegistrar;
import domain.keywords.KeywordsResolver;
import services.locator.ServiceLocator;

public class KeywordsResolverCreator {
    public static KeywordsResolver create() {
        KeywordsRegistrar registrar = KeywordsRegistrar.create();
        ServiceLocator.getInstance().resolve(KeywordRepository.class).findAllKeywords().forEach(registrar::register);
        return registrar.createKeywordsResolver();
    }
}
