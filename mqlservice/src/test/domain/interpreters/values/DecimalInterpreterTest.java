package domain.interpreters.values;

import domain.query.Query;
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
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DecimalInterpreterTest {
    private static final String DECIMAL_KEYWORD = "-10.0";
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query decimalQuery;
    @Mock
    private Query invalidQuery;
    private DecimalInterpreter decimalInterpreter;
    private Matcher decimalMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        decimalInterpreter = new DecimalInterpreter();
        decimalMatcher = DecimalInterpreter.DECIMAL_PATTERN.matcher(DECIMAL_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(decimalMatcher).given(decimalQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnDecimalQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(decimalInterpreter.interpret(decimalQuery, queryBuilder));
    }

    @Test
    public void givenAnDecimalQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        decimalInterpreter.interpret(decimalQuery, queryBuilder);
        verify(queryBuilder).withDecimal(anyDouble());
    }

    @Test
    public void givenAnDecimalQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        decimalInterpreter.interpret(decimalQuery, queryBuilder);
        verify(decimalQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(decimalInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        decimalInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        decimalInterpreter.interpret(invalidQuery, queryBuilder);
        verify(decimalQuery, never()).removeFirstMatch(any());
    }
}
