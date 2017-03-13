package domain.keywords;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.willReturn;

@RunWith(MockitoJUnitRunner.class)
public class KeywordsSetTest {
    private static final String SYNONIM = "A SYNONIM";
    private static final String VALID_WORD = "A valid word";
    private static final String INVALID_WORD = "Invalid word";
    @Mock
    private Keyword validKeyword;
    @Mock
    private Keyword invalidKeyword;
    private Keywords keywords;

    @Before
    public void setUp() throws Exception {
        keywords = new KeywordsSet();
        keywords.add(validKeyword);
        willReturn(VALID_WORD).given(validKeyword).name();
        willReturn(INVALID_WORD).given(invalidKeyword).name();
        willReturn(true).given(validKeyword).isSynonymOf(SYNONIM);
    }

    @Test
    public void givenAnAlreadyRegisteredKeyword_whenContains_thenReturnTrue() {
        assertTrue(keywords.contains(SYNONIM));
    }

    @Test
    public void givenANonRegisteredKeyword_whenContains_thenReturnFalse() {
        assertFalse(keywords.contains(INVALID_WORD));
    }

    @Test
    public void whenParentOf_thenReturnTheParentOfTheKeyword() {
        assertThat(keywords.parentOf(SYNONIM), is(equalTo(VALID_WORD)));
    }
}