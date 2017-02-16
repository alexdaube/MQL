package domain.keywords;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class KeywordsSetTest {
    private static final String PARENT = "A PARENT";
    private static final Keyword VALID_KEYWORD = new Keyword("A VALID KEYWORD", PARENT);
    private static final Keyword INVALID_KEYWORD = new Keyword("AN INVALID KEYWORD");
    private Set<Keyword> children;
    private Keywords keywords;

    @Before
    public void setUp() throws Exception {
        children = new HashSet<>();
        children.add(VALID_KEYWORD);
        keywords = new KeywordsSet(children);
    }

    @Test
    public void givenAnAlreadyRegisteredKeyword_whenContains_thenReturnTrue() {
        assertTrue(keywords.contains(VALID_KEYWORD.word));
    }

    @Test
    public void givenANonRegisteredKeyword_whenContains_thenReturnFalse() {
        assertFalse(keywords.contains(INVALID_KEYWORD.word));
    }

    @Test
    public void whenParentOf_thenReturnTheParentOfTheKeyword() {
        assertThat(keywords.parentOf(VALID_KEYWORD.word), is(equalTo(PARENT)));
    }
}