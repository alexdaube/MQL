package domain.querybuilder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.InCondition;
import domain.InvalidQueryException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EqualStateTest {
    @Mock
    private SqlQueryBuilder sqlQueryBuilder;
    private EqualState equalState;

    @Before
    public void setUp() {
        equalState = new EqualState(sqlQueryBuilder);
    }

    @Test
    public void givenOneValue_whenApplying_thenReturnABinaryCondition() {
        equalState.withDecimal(10);
        Condition condition = equalState.apply();
        assertThat(condition, is(instanceOf(BinaryCondition.class)));
    }

    @Test
    public void givenMoreThanOneValue_whenApplying_thenReturnAnInCondition() {
        equalState.withDecimal(10);
        equalState.withDecimal(11);
        Condition condition = equalState.apply();
        assertThat(condition, is(instanceOf(InCondition.class)));
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanOneValue_whenApplying_thenThrowAnException() {
        equalState.apply();
    }
}