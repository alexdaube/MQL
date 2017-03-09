package domain.keywords;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

@RunWith(MockitoJUnitRunner.class)
public class KeywordTest {
    private static final String WORD = "A Word";
    private static final Keyword.Type TYPE = Keyword.Type.EQUALS;
    private static final Object SYNONYM = "A synonym";
    private Keyword keyword;

    @Before
    public void setUp() {
        keyword = new Keyword(WORD, TYPE);
        HashSet synonyms = new HashSet();
        synonyms.add(SYNONYM);
        keyword.setSynonyms(synonyms);
    }

    @Test
    public void given_whenName_then() {


    }
}