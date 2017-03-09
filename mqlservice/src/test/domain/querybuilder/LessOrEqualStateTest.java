package domain.querybuilder;

import domain.InvalidQueryException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class LessOrEqualStateTest {
    @Mock
    private SqlQueryBuilder queryBuilder;
    private LessOrEqualState lessOrEqualState;

    @Before
    public void setUp() throws Exception {
        lessOrEqualState = new LessOrEqualState(queryBuilder);
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanOneValue_whenApplying_thenThrowAnException() {
        lessOrEqualState.apply();
    }

    @Test(expected = InvalidQueryException.class)
    public void givenMoreThanOneValue_whenApplying_thenThrowAnException() {
        lessOrEqualState.apply();
    }
}