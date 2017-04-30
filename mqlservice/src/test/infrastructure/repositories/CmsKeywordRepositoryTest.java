package infrastructure.repositories;

import infrastructure.clients.KeywordClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CmsKeywordRepositoryTest {

    private CmsKeywordRepository cmsKeywordRepository;
    @Mock
    private KeywordClient keywordClient;

    @Before
    public void setUp() {
        cmsKeywordRepository = new CmsKeywordRepository(keywordClient);
    }

    @Test
    public void whenFindAllKeywords_thenFetchEntitiesOperatorsAndJunctions() {
        cmsKeywordRepository.findAllKeywords();

        verify(keywordClient).fetchEntities();
        verify(keywordClient).fetchOperators();
        verify(keywordClient).fetchJunctions();
    }
}