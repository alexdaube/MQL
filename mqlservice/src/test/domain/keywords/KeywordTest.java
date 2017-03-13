package domain.keywords;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class KeywordTest {
    private static final String WORD = "A Word";
    private static final Keyword.Type TYPE = Keyword.Type.EQUAL;
    private static final String SYNONYM = "A synonym";
    private static final String NON_SYNONYM = "A non synonym";
    @Mock
    private Keywords children;
    private HashSet<String> synonyms;
    private Keyword keyword;

    @Before
    public void setUp() {
        synonyms = new HashSet<>();
        synonyms.add(SYNONYM);
        keyword = new Keyword(WORD, TYPE, synonyms, children);
    }

    @Test
    public void givenAWordATypeSynonymsAndChildren_whenInitializing_thenTheNameIsSet() {
        assertThat(keyword.name(), is(equalTo(WORD)));
    }

    @Test
    public void givenAWordATypeSynonymsAndChildren_whenInitializing_thenTheTypeIsSet() {
        assertThat(keyword.type, is(equalTo(TYPE)));
    }

    @Test
    public void givenAWordATypeSynonymsAndChildren_whenInitializing_thenTheSynonymsAreSet() {
        assertThat(keyword.getSynonyms(), is(equalTo(synonyms)));
    }

    @Test
    public void givenAWordATypeSynonymsAndChildren_whenInitializing_thenTheChildrenAreSet() {
        assertThat(keyword.getChildren(), is(equalTo(children)));
    }

    @Test
    public void givenASynonym_whenIsSynonymOf_thenReturnTrue() {
        assertTrue(keyword.isSynonymOf(SYNONYM));
    }

    @Test
    public void givenANonSynonym_whenIsSynonymOf_thenReturnFalse() {
        assertFalse(keyword.isSynonymOf(NON_SYNONYM));
    }
}