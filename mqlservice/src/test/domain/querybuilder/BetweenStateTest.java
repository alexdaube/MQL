package domain.querybuilder;

import domain.InvalidQueryException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BetweenStateTest {
    @Mock
    private SqlQueryBuilder sqlQueryBuilder;
    private BetweenState betweenState;

    @Before
    public void setUp() {
        betweenState = new BetweenState(sqlQueryBuilder);
    }

    @Test
    public void givenMoreThanOneValues_whenAnd_thenTheConditionIsApplied() {
        betweenState.withDecimal(10);
        betweenState.withDecimal(20);
        betweenState.and();
        verify(sqlQueryBuilder).applyCondition(any());
    }

    @Test
    public void givenLessThanTwoValues_whenAnd_thenTheConditionIsNotApplied() {
        betweenState.and();
        verify(sqlQueryBuilder, never()).applyCondition(any());
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanTwoValues_whenWithEquals_thenThrowAnException() {
        betweenState.withEquals();
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanTwoValues_whenWithGreater_thenThrowAnException() {
        betweenState.withGreater();
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanTwoValues_whenWithLess_thenThrowAnException() {
        betweenState.withLess();
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanTwoValues_whenWithBetween_thenThrowAnException() {
        betweenState.withBetween();
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanTwoValues_whenOr_thenThrowAnException() {
        betweenState.or();
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanTwoValues_whenApplying_thenThrowAnException() {
        betweenState.apply();
    }
}