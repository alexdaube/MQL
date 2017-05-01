package domain.keywords;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;

@RunWith(MockitoJUnitRunner.class)
public class KeywordsRegistrarTest {

    private static final Keyword.Type TYPE = Keyword.Type.ENTITY;
    private static final String NAME = "a keyword name";
    private KeywordsRegistrar keywordsRegistrar;
    @Mock
    private Keyword keyword;

    @Before
    public void setUp() {
        keywordsRegistrar = KeywordsRegistrar.create();
        willReturn(TYPE).given(keyword).getType();
        willReturn(NAME).given(keyword).name();
        willReturn(true).given(keyword).isSynonymOf(NAME);
    }

    @Test
    public void givenAKeyword_whenRegister_thenTheKeywordIsRegistered() {
        keywordsRegistrar.register(keyword);

        assertTrue(keywordsRegistrar.createKeywordsResolver().resolveType(TYPE).contains(NAME));
    }
}