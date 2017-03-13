package services.query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import configuration.keywords.*;
import contexts.DevContext;
import domain.DbClient;
import domain.keywords.Keyword;
import domain.keywords.KeywordRepository;
import domain.keywords.KeywordsResolver;
import domain.keywords.KeywordsSet;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SqlQueryBuilder;
import infrastructure.repositories.InMemoryKeywordRepository;
import infrastructure.repositories.InterpreterKeywordFactory;
import infrastructure.repositories.KeywordDevDataFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import infrastructure.KeywordResolver.KeywordsRegistrar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

// TODO: 12/03/17 test
@RunWith(MockitoJUnitRunner.class)
public class QueryServiceTest {

    @Mock
    private DbClient dbClient;
    private QueryService queryService;
    private QueryDto queryDto;

    @BeforeClass
    public static void beforeSetUp() {
        new DevContext().apply();
    }

    @Before
    public void setUp() {
        queryService = new QueryService(dbClient);
        queryDto = new QueryDto();
    }

    @Test
    public void given_whenExecuteQuery_then() {
        String query = "Site SiteId is equal to \"9999\" or less than 9999 and between 9:10 and Equipment serial is greater or equal to 0";
        System.err.println(query);
        queryDto.query = query;
        queryService.executeQuery(queryDto);
    }
}