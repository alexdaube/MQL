package domain.query.translators;

import domain.query.Query;
import domain.query.builder.QueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MqlQueryTranslatorTest {

    private MqlQueryTranslator mqlQueryTranslator;
    @Mock
    private QueryBuilder queryBuilder;
    @Mock
    private QueryTranslatorState initialState;
    @Mock
    private QueryTranslatorState finalState;
    @Mock
    private Query query;

    @Before
    public void setUp() {
        mqlQueryTranslator = new MqlQueryTranslator(initialState, queryBuilder);
        willReturn(new StateStatus(false, finalState)).given(initialState).translate(query);
        willReturn(new StateStatus(true, finalState)).given(finalState).translate(query);
    }

    @Test
    public void givenAQuery_whenTranslate_thenTheQueryIsTranslated() {
        mqlQueryTranslator.translate(query);

        verify(initialState).translate(query);
        verify(finalState).translate(query);
    }

    @Test
    public void givenAQuery_whenTranslate_thenTheQueryIsBuild() {
        mqlQueryTranslator.translate(query);

        verify(queryBuilder).build();
    }
}