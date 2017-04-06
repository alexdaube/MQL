package domain.interpreters.operators;

import domain.keywords.Keywords;
import domain.query.Query;
import domain.query.builder.OperatorType;
import domain.query.builder.QueryBuilder;
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
public class NotInterpreterTest {

    private static final String NOT_KEYWORD = "not";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query notQuery;
    @Mock
    private Query invalidQuery;
    private NotInterpreter notInterpreter;
    private Matcher notMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        notInterpreter = new NotInterpreter(keywords);
        notMatcher = NotInterpreter.NOT_PATTERN.matcher(NOT_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(NOT_KEYWORD);
        willReturn(notMatcher).given(notQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnNotQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(notInterpreter.interpret(notQuery, queryBuilder));
    }

    @Test
    public void givenAnNotQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        notInterpreter.interpret(notQuery, queryBuilder);
        verify(queryBuilder).withOperator(any(OperatorType.class));
    }

    @Test
    public void givenAnNotQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        notInterpreter.interpret(notQuery, queryBuilder);
        verify(notQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(notInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        notInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        notInterpreter.interpret(invalidQuery, queryBuilder);
        verify(notQuery, never()).removeFirstMatch(any());
    }
}