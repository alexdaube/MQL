package domain.query.builder;

import com.healthmarketscience.sqlbuilder.BinaryCondition;
import com.healthmarketscience.sqlbuilder.dbspec.Column;
import domain.InvalidQueryException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.willReturn;

@RunWith(MockitoJUnitRunner.class)
public class LikeStateTest {

    private static final int ONE = 1;
    private static final int TWO = 2;
    private LikeState likeState;
    @Mock
    private SqlQueryBuilder queryBuilder;
    @Mock
    private Column attribute;

    @Before
    public void setUp() {
        likeState = new LikeState(queryBuilder);
    }

    @Test
    public void givenOneValueAndAnAttribute_whenApply_thenReturnALikeCondition() {
        willReturn(attribute).given(queryBuilder).getAttribute();
        likeState.values.add(ONE);

        assertThat(likeState.apply(), is(instanceOf(BinaryCondition.class)));
    }

    @Test(expected = InvalidQueryException.class)
    public void givenLessThanOneValue_whenApply_thenThrowAnException() {
        likeState.apply();
    }

    @Test(expected = InvalidQueryException.class)
    public void givenMoreThanOneValue_whenApply_thenThrowAnException() {
        likeState.values.add(ONE);
        likeState.values.add(TWO);
        likeState.apply();
    }
}