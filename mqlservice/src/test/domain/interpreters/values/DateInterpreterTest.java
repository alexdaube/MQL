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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DateInterpreterTest {
    private static final String DATE_KEYWORD = "1990-10-10";
    private static final String DATE = "Date";
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query dateQuery;
    @Mock
    private Query invalidQuery;
    @Mock
    SuggestionBuilder suggestionBuilder;
    private DateInterpreter dateInterpreter;
    private Matcher dateMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        dateInterpreter = new DateInterpreter();
        dateMatcher = DateInterpreter.DATE_PATTERN.matcher(DATE_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(dateMatcher).given(dateQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnDateQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(dateInterpreter.interpret(dateQuery, queryBuilder));
    }

    @Test
    public void givenAnDateQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        dateInterpreter.interpret(dateQuery, queryBuilder);
        verify(queryBuilder).withDate(any());
    }

    @Test
    public void givenAnDateQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        dateInterpreter.interpret(dateQuery, queryBuilder);
        verify(dateQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(dateInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        dateInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        dateInterpreter.interpret(invalidQuery, queryBuilder);
        verify(dateQuery, never()).removeFirstMatch(any());
    }

    @Test
    public void givenASuggestionBuilder_whenSuggest_thenAddSuggestionsForValueType() {
        dateInterpreter.suggest(suggestionBuilder);
        verify(suggestionBuilder).withValue(DATE);
    }
}
