package services.query;

import builders.KeywordsBuilder;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import domain.DbClient;
import domain.keywords.Keywords;
import domain.query.builder.SqlQueryBuilder;
import domain.query.translators.MqlQueryTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.keyword.KeywordsRegistrar;
import services.keyword.KeywordsValueRegistrar;

@RunWith(MockitoJUnitRunner.class)
public class QueryServiceIT {
    @Mock
    private DbClient dbClient;
    private QueryService queryService;
    private QueryDto query;
    private DbSchema schema;

    @Before
    public void setUp() {
        //Doit être monté selon les infos de la bd et du user
        KeywordsValueRegistrar kwRegistrar = KeywordsRegistrar.create();
        kwRegistrar.register(KeywordsBuilder.create().with("Site").build()).as(Keywords.Type.ENTITY);
        kwRegistrar.register(KeywordsBuilder.create().with("Name").with("SiteName", "Name").with("Id").build()).as(Keywords.Type.ATTRIBUTE);
        kwRegistrar.register(KeywordsBuilder.create().with("=").with("equals").with("equal").with("is").build()).as(Keywords.Type.EQUALS);
        kwRegistrar.register(KeywordsBuilder.create().with("<").with("less").build()).as(Keywords.Type.LESS);
        kwRegistrar.register(KeywordsBuilder.create().with(">").with("greater").build()).as(Keywords.Type.GREATER);
        kwRegistrar.register(KeywordsBuilder.create().with("between").build()).as(Keywords.Type.BETWEEN);
        kwRegistrar.register(KeywordsBuilder.create().with("or").with("than").build()).as(Keywords.Type.OTHER);
        kwRegistrar.register(KeywordsBuilder.create().with("or").build()).as(Keywords.Type.OR);
        kwRegistrar.register(KeywordsBuilder.create().with("and").build()).as(Keywords.Type.AND);

        //Doit être monté selon les infos de la bd
        schema = new DbSpec().addDefaultSchema();
        DbTable table = schema.addTable("Site");
        table.addColumn("Name");
        table.addColumn("Id");

        queryService = new QueryService(dbClient, new MqlQueryTranslator(
                new SqlQueryBuilder(schema).withAllTablesColumns(), kwRegistrar.createKeywordsResolver()));
        query = new QueryDto();
    }

    @Test
    public void given_whenExecuteQuery_then() {
        query.query = "Site SiteName is \"Look the SiteName is equivalent to Name\"";
        System.out.println(query.query);
        queryService.executeQuery(query);
    }

}