package domain.interpreters.junctions;

import domain.keywords.Keywords;
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
public class AndInterpreterTest {
    private static final String AND_KEYWORD = "and";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query andQuery;
    @Mock
    private Query invalidQuery;
    @Mock
    SuggestionBuilder suggestionBuilder;
    private AndInterpreter andInterpreter;
    private Matcher andMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        andInterpreter = new AndInterpreter(keywords);
        andMatcher = AndInterpreter.AND_PATTERN.matcher(AND_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(AND_KEYWORD);
        willReturn(andMatcher).given(andQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
        willReturn(suggestionBuilder).given(suggestionBuilder).withQueryMatching(keywords);
        willReturn(suggestionBuilder).given(suggestionBuilder).withAllowed(keywords);
    }

    @Test
    public void givenAnAndQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(andInterpreter.interpret(andQuery, queryBuilder));
    }

    @Test
    public void givenAnAndQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        andInterpreter.interpret(andQuery, queryBuilder);
        verify(queryBuilder).and();
    }

    @Test
    public void givenAnAndQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        andInterpreter.interpret(andQuery, queryBuilder);
        verify(andQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(andInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        andInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        andInterpreter.interpret(invalidQuery, queryBuilder);
        verify(andQuery, never()).removeFirstMatch(any());
    }

    @Test
    public void givenASuggestionBuilder_whenSuggest_thenAddSuggestionsMatchingTheQuery() {
        andInterpreter.suggest(suggestionBuilder);
        verify(suggestionBuilder).withQueryMatching(keywords);
    }

    @Test
    public void givenASuggestionBuilder_whenSuggest_thenAddAllPossibleSuggestions() {
        andInterpreter.suggest(suggestionBuilder);
        verify(suggestionBuilder).withAllowed(keywords);
    }
}