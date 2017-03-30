package domain.interpreters.operators;

import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.OperatorType;
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
public class GreaterInterpreterTest {
    private static final String GREATER_KEYWORD = "greater";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query greaterQuery;
    @Mock
    private Query invalidQuery;
    @Mock
    SuggestionBuilder suggestionBuilder;
    private GreaterInterpreter greaterInterpreter;
    private Matcher greaterMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        greaterInterpreter = new GreaterInterpreter(keywords);
        greaterMatcher = GreaterInterpreter.GREATER_PATTERN.matcher(GREATER_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(GREATER_KEYWORD);
        willReturn(greaterMatcher).given(greaterQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
        willReturn(suggestionBuilder).given(suggestionBuilder).withQueryMatching(keywords);
        willReturn(suggestionBuilder).given(suggestionBuilder).withAllowed(keywords);
    }

    @Test
    public void givenAnGreaterQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(greaterInterpreter.interpret(greaterQuery, queryBuilder));
    }

    @Test
    public void givenAnGreaterQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        greaterInterpreter.interpret(greaterQuery, queryBuilder);
        verify(queryBuilder).withOperator(OperatorType.GREATER);
    }

    @Test
    public void givenAnGreaterQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        greaterInterpreter.interpret(greaterQuery, queryBuilder);
        verify(greaterQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(greaterInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        greaterInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        greaterInterpreter.interpret(invalidQuery, queryBuilder);
        verify(greaterQuery, never()).removeFirstMatch(any());
    }
    @Test
    public void givenASuggestionBuilder_whenSuggest_thenAddSuggestionsMatchingTheQuery() {
        greaterInterpreter.suggest(suggestionBuilder);
        verify(suggestionBuilder).withQueryMatching(keywords);
    }

    @Test
    public void givenASuggestionBuilder_whenSuggest_thenAddAllPossibleSuggestions() {
        greaterInterpreter.suggest(suggestionBuilder);
        verify(suggestionBuilder).withAllowed(keywords);
    }
}