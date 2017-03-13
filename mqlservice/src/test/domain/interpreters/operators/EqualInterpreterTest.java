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
public class EqualInterpreterTest {
    private static final String EQUAL_KEYWORD = "equal";
    @Mock
    private Keywords keywords;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query equalQuery;
    @Mock
    private Query invalidQuery;
    private EqualInterpreter equalInterpreter;
    private Matcher equalMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        equalInterpreter = new EqualInterpreter(keywords);
        equalMatcher = EqualInterpreter.EQUAL_PATTERN.matcher(EQUAL_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(true).given(keywords).contains(EQUAL_KEYWORD);
        willReturn(equalMatcher).given(equalQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnEqualQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(equalInterpreter.interpret(equalQuery, queryBuilder));
    }

    @Test
    public void givenAnEqualQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        equalInterpreter.interpret(equalQuery, queryBuilder);
        verify(queryBuilder).withOperator(any(OperatorType.class));
    }

    @Test
    public void givenAnEqualQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        equalInterpreter.interpret(equalQuery, queryBuilder);
        verify(equalQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(equalInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        equalInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        equalInterpreter.interpret(invalidQuery, queryBuilder);
        verify(equalQuery, never()).removeFirstMatch(any());
    }
}