package domain.interpreters.values;

import domain.query.Query;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SuggestionBuilder;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IntegerInterpreterTest {
    private static final String INTEGER_KEYWORD = "-10";
    private static final String INTEGER = "Integer";
    @Mock
    SuggestionBuilder suggestionBuilder;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query integerQuery;
    @Mock
    private Query invalidQuery;
    private IntegerInterpreter integerInterpreter;
    private Matcher integerMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        integerInterpreter = new IntegerInterpreter();
        integerMatcher = IntegerInterpreter.INTEGER_PATTERN.matcher(INTEGER_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(integerMatcher).given(integerQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnDecimalQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(integerInterpreter.interpret(integerQuery, queryBuilder));
    }

    @Test
    public void givenAnDecimalQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        integerInterpreter.interpret(integerQuery, queryBuilder);
        verify(queryBuilder).withInteger(anyInt());
    }

    @Test
    public void givenAnDecimalQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        integerInterpreter.interpret(integerQuery, queryBuilder);
        verify(integerQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(integerInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        integerInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        integerInterpreter.interpret(invalidQuery, queryBuilder);
        verify(integerQuery, never()).removeFirstMatch(any());
    }

    @Test
    public void givenASuggestionBuilder_whenSuggest_thenAddSuggestionsForValueType() {
        integerInterpreter.suggest(suggestionBuilder);
        verify(suggestionBuilder).withValue(INTEGER);
    }
}
