package domain.keywords;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class KeywordsSetTest {
    private static final String VALID_KEYWORD = "A VALID KEYWORD";
    private static final String INVALID_KEYWORD = "AN INVALID KEYWORD";
    private Set<String> keywordSet;
    private Keywords keywords;

    @Before
    public void setUp() throws Exception {
        keywordSet = new HashSet<>();
        keywordSet.add(VALID_KEYWORD);
        keywords = new KeywordsSet(keywordSet);
    }

    @Test
    public void givenAnAlreadyRegisteredKeyword_whenContains_thenReturnTrue() {
        assertTrue(keywords.contains(VALID_KEYWORD));
    }

    @Test
    public void givenANonRegisteredKeyword_whenContains_thenReturnFalse() {
        assertFalse(keywords.contains(INVALID_KEYWORD));
    }
}