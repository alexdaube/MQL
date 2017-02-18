package domain.query.builder;

import domain.InvalidQueryException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class GreaterOrEqualStateTest {
    @Mock
    private SqlQueryBuilder queryBuilder;
    private GreaterOrEqualState greaterOrEqualState;

    @Before
    public void setUp() throws Exception {
        greaterOrEqualState = new GreaterOrEqualState(queryBuilder);
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanOneValue_whenApplying_thenThrowAnException() {
        greaterOrEqualState.apply();
    }

    @Test(expected = InvalidQueryException.class)
    public void givenMoreThanOneValue_whenApplying_thenThrowAnException() {
        greaterOrEqualState.apply();
    }
}