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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VarcharInterpreterTest {
    private static final String VARCHAR_KEYWORD = "\"Something!!\"";
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private Query varcharQuery;
    @Mock
    private Query invalidQuery;
    private VarcharInterpreter varcharInterpreter;
    private Matcher varcharMatcher;
    private Matcher invalidMatcher;

    @Before
    public void setUp() throws Exception {
        varcharInterpreter = new VarcharInterpreter();
        varcharMatcher = VarcharInterpreter.VARCHAR_PATTERN.matcher(VARCHAR_KEYWORD);
        invalidMatcher = Pattern.compile("An invalid one").matcher("");
        willReturn(varcharMatcher).given(varcharQuery).findMatches(any());
        willReturn(invalidMatcher).given(invalidQuery).findMatches(any());
    }

    @Test
    public void givenAnVarcharQueryAndAQueryBuilder_whenInterpreting_thenReturnTrue() {
        assertTrue(varcharInterpreter.interpret(varcharQuery, queryBuilder));
    }

    @Test
    public void givenAnVarcharQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsCalled() {
        varcharInterpreter.interpret(varcharQuery, queryBuilder);
        verify(queryBuilder).withVarchar(anyString());
    }

    @Test
    public void givenAnVarcharQueryAndAQueryBuilder_whenInterpreting_thenTheKeywordIsRemovedFromQuery() {
        varcharInterpreter.interpret(varcharQuery, queryBuilder);
        verify(varcharQuery).removeFirstMatch(any());
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenReturnFalse() {
        assertFalse(varcharInterpreter.interpret(invalidQuery, queryBuilder));
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenTheBuilderIsNotCalled() {
        varcharInterpreter.interpret(invalidQuery, queryBuilder);
        verify(queryBuilder, never()).and();
    }

    @Test
    public void givenAnInvalidQueryAndAQueryBuilder_whenInterpreting_thenNoKeywordsIsRemovedFromQuery() {
        varcharInterpreter.interpret(invalidQuery, queryBuilder);
        verify(varcharQuery, never()).removeFirstMatch(any());
    }
}

