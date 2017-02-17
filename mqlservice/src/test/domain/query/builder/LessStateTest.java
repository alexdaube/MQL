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
public class LessStateTest {
    @Mock
    private SqlQueryBuilder queryBuilder;
    private LessState lessState;

    @Before
    public void setUp() throws Exception {
        lessState = new LessState(queryBuilder);
    }

    @Test
    public void givenNoValue_whenWithEquals_thenChangeStateToLessOrEqual() {
        lessState.withEquals();
        verify(queryBuilder).changeState(any(LessOrEqualState.class));
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanOneValue_whenApplying_thenThrowAnException() {
        lessState.apply();
    }

    @Test(expected = InvalidQueryException.class)
    public void givenMoreThanOneValue_whenApplying_thenThrowAnException() {
        lessState.apply();
    }
}