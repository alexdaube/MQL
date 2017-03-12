package domain.query.builder;

import domain.InvalidQueryException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GreaterStateTest {
    private static final OperatorType OPERATOR = OperatorType.EQUAL;
    @Mock
    private SqlQueryBuilder queryBuilder;
    private GreaterState greaterState;

    @Before
    public void setUp() throws Exception {
        greaterState = new GreaterState(queryBuilder);
    }

    @Test
    public void givenNoValue_whenWithEquals_thenChangeStateToGreaterOrEqual() {
        greaterState.withOperator(OPERATOR);
        verify(queryBuilder).changeState(any(GreaterOrEqualState.class));
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanOneValue_whenApplying_thenThrowAnException() {
        greaterState.apply();
    }

    @Test(expected = InvalidQueryException.class)
    public void givenMoreThanOneValue_whenApplying_thenThrowAnException() {
        greaterState.apply();
    }
}