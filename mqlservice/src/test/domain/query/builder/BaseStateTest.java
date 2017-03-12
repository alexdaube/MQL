package domain.query.builder;

import com.healthmarketscience.sqlbuilder.ComboCondition;
import com.healthmarketscience.sqlbuilder.Condition;
import com.healthmarketscience.sqlbuilder.JdbcEscape;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BaseStateTest {
    @Mock
    private Condition condition;
    @Mock
    private SqlQueryBuilder sqlQueryBuilder;
    @Mock
    private List<Object> values;
    private BaseState baseState;

    @Before
    public void setUp() throws Exception {
        baseState = new BaseState(sqlQueryBuilder, values) {
            @Override
            public Condition apply() {
                return condition;
            }
        };
    }

    @Test
    public void whenWithEquals_thenChangeStateToEqual() throws Exception {
        baseState.withOperator(OperatorType.EQUAL);
        verify(sqlQueryBuilder).changeState(any(EqualState.class));
    }

    @Test
    public void whenWithGreater_thenChangeStateToGreater() {
        baseState.withOperator(OperatorType.GREATER);
        verify(sqlQueryBuilder).changeState(any(GreaterState.class));
    }

    @Test
    public void whenWithBetween_thenChangeStateToBetween() throws Exception {
        baseState.withOperator(OperatorType.BETWEEN);
        verify(sqlQueryBuilder).changeState(any(BetweenState.class));
    }

    @Test
    public void whenWithLess_thenChangeStateToLess() throws Exception {
        baseState.withOperator(OperatorType.LESS);
        verify(sqlQueryBuilder).changeState(any(LessState.class));
    }

    @Test
    public void givenAString_whenWithVarchar_thenTheValueIsAdded() throws Exception {
        String value = "A value";
        baseState.withVarchar(value);
        verify(values).add(value);
    }

    @Test
    public void givenAnInteger_whenWithInteger_thenTheValueIsAdded() throws Exception {
        int value = 10;
        baseState.withInteger(value);
        verify(values).add(value);
    }

    @Test
    public void givenADouble_whenWithDecimal_thenTheValueIsAdded() throws Exception {
        double value = 10.1;
        baseState.withDecimal(value);
        verify(values).add(value);
    }

    @Test
    public void givenADate_whenWithDate_thenTheDateIsAdded() throws Exception {
        Date date = new Date(0);
        baseState.withDate(date);
        verify(values).add(any(JdbcEscape.date(date).getClass()));
    }

    @Test
    public void whenAnd_thenTheJunctionIsSetToAnd() throws Exception {
        baseState.and();
        verify(sqlQueryBuilder).setJunction(ComboCondition.Op.AND);
    }

    @Test
    public void whenAnd_thenTheValuesAreCleared() throws Exception {
        baseState.and();
        verify(values).clear();
    }

    @Test
    public void whenAnd_thenTheConditionIsApplied() throws Exception {
        baseState.and();
        verify(sqlQueryBuilder).applyCondition(condition);
    }

    @Test
    public void whenOr_thenTheJunctionIsSetToOR() throws Exception {
        baseState.or();
        verify(sqlQueryBuilder).setJunction(ComboCondition.Op.OR);
    }

    @Test
    public void whenOr_thenTheValuesAreCleared() throws Exception {
        baseState.or();
        verify(values).clear();
    }

    @Test
    public void whenOr_thenTheConditionIsApplied() throws Exception {
        baseState.or();
        verify(sqlQueryBuilder).applyCondition(condition);
    }
}