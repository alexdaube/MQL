package domain.interpreters.operators;

import domain.Query;
import domain.keywords.Keywords;
import domain.querybuilder.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LessInterpreterTest {
    private static final String LESS_KEYWORD = "less";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query lessQuery;
    @Mock
    private Query invalidQuery;
    private LessInterpreter lessInterpreter;
    private Matcher lessMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        lessInterpreter = new LessInterpreter(keywords);
        lessMatcher = LessInterpreter.LESS_PATTERN.matcher(LESS_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(LESS_KEYWORD);
        willReturn(lessMatcher).given(lessQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnLessQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(lessInterpreter.interpret(lessQuery, queryBuilder));
    }

    @Test
    public void givenAnLessQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        lessInterpreter.interpret(lessQuery, queryBuilder);
        verify(queryBuilder).withLess();
    }

    @Test
    public void givenAnLessQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        lessInterpreter.interpret(lessQuery, queryBuilder);
        verify(lessQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(lessInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        lessInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        lessInterpreter.interpret(invalidQuery, queryBuilder);
        verify(lessQuery, never()).removeFirstMatch(any());
    }
}