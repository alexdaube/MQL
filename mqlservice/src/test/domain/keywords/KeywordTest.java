package domain.keywords;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class KeywordTest {
    private static final String WORD = "A Word";
    private static final Keyword.Type TYPE = Keyword.Type.EQUAL;
    private static final String SYNONYM = "A synonym";
    private Keyword keyword;

    @Before
    public void setUp() {
        keyword = new Keyword(WORD, TYPE);
        HashSet<String> synonyms = new HashSet<>();
        synonyms.add(SYNONYM);
        keyword.setSynonyms(synonyms);
    }

    @Test
    public void givenAWordAndAType_whenInitializing_thenTheNameIsSet() {
        assertThat(keyword.name(), is(equalTo(WORD)));
    }

    @Test
    public void givenAWordAndAType_whenInitializing_thenTheTypeIsSet() {
        assertThat(keyword.type, is(equalTo(TYPE)));
    }

    @Test
    public void givenAWordAndAType_whenInitializing_thenTheSynonymsAreEmpty() {
        keyword = new Keyword(WORD, TYPE);
        assertTrue(keyword.getSynonyms().isEmpty());
    }

    @Test
    public void givenASetOfSynonyms_whenSettingSynonyms_thenTheSynonymsAreSet() {
        assertTrue(keyword.isSynonymOf(SYNONYM));
    }
}