package services.query;

import com.google.gson.JsonArray;
import domain.DbClient;
import domain.query.Query;
import domain.query.translators.QueryTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.locator.ServiceLocator;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class QueryServiceTest {

    private static final String QUERY = "the translated query";
    @Mock
    private DbClient dbClient;
    @Mock
    private QueryTranslator queryTranslator;
    private QueryService queryService;
    private QueryDto queryDto;
    private JsonArray suggestion;

    @Before
    public void setUp() {
        queryService = new QueryService(dbClient);
        queryDto = new QueryDto();
        suggestion = new JsonArray();
        ServiceLocator.reset();
        ServiceLocator.getInstance().register(() -> queryTranslator).asSingleInstance().of(QueryTranslator.class);
        willReturn(QUERY).given(queryTranslator).translate(any(Query.class));
    }

    @Test
    public void givenAQueryDto_whenExecuteQuery_thenTheQueryIsTranslated() {
        queryService.executeQuery(queryDto);
        verify(queryTranslator).translate(any(Query.class));
    }

    @Test
    public void givenAQueryDto_whenExecuteQuery_thenTheQueryIsExecuted() {
        queryService.executeQuery(queryDto);
        verify(dbClient).execute(QUERY);
    }

    @Test
    public void givenAQueryDto_whenGetNextSuggestion_thenTheNextSuggestionIsFetched() {
        willReturn(suggestion).given(queryTranslator).translateNextSuggestion(any(Query.class));
        queryService.getNextSuggestion(queryDto);
        verify(queryTranslator).translateNextSuggestion(any(Query.class));
    }
}