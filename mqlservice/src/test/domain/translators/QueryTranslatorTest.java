package domain.translators;

import builders.KeywordsBuilder;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import domain.querybuilder.QueryBuilder;
import domain.StringQuery;
import domain.keyword.KeywordsResolver;
import domain.querybuilder.SqlQueryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.willReturn;

@RunWith(MockitoJUnitRunner.class)
public class QueryTranslatorTest {
    @Mock
    private KeywordsResolver keywordsResolver;
    private QueryTranslator queryTranslator;
    private DbSchema schema;

    @Before
    public void setUp() throws Exception {
        willReturn(KeywordsBuilder.create().with("is").with("equal").with("in").with("equals").with("=").build())
                .given(keywordsResolver).resolveEqualOperators();
        willReturn(KeywordsBuilder.create().with("to").with("than").with("or").build()).given(keywordsResolver).resolveOtherOperators();
        willReturn(KeywordsBuilder.create().with("less").with("<").build()).given(keywordsResolver).resolveLessOperators();
        willReturn(KeywordsBuilder.create().with("greater").with(">").build()).given(keywordsResolver).resolveGreaterOperators();
        willReturn(KeywordsBuilder.create().with("Employee").with("Site").build()).given(keywordsResolver).resolveEntities();
        willReturn(KeywordsBuilder.create().with("name").build()).given(keywordsResolver).resolveAttributes();
        willReturn(KeywordsBuilder.create().with("and").build()).given(keywordsResolver).resolveAndJunctions();
        willReturn(KeywordsBuilder.create().with("or").build()).given(keywordsResolver).resolveOrJunctions();
        willReturn(KeywordsBuilder.create().with("between").build()).given(keywordsResolver).resolveBetweenOperators();

        schema = new DbSpec().addDefaultSchema();
        DbTable employee = schema.addTable("Employee");
        employee.addColumn("name", "varchar", 255);
        employee.addColumn("age", "number", null);
        DbTable site = schema.addTable("Site");
        site.addColumn("name", "varchar", 255);
        queryTranslator = new QueryTranslator(new SqlQueryBuilder(schema).withAllTablesColumns(), keywordsResolver);
    }

    @Test
    public void translate0() throws Exception {
        String query = "   Employee name is <= \"Nicolas\" and name >= 9";
        System.out.println(query);
        queryTranslator.translate(new StringQuery(query));
    }

    @Test
    public void translate1() throws Exception {
        String query = "Employee.name is less or equal to \"Nicolas\"";
        System.out.println(query);
        queryTranslator.translate(new StringQuery(query));
    }

    @Test
    public void translate2() throws Exception {
        String query = " Employee  name    is   in     9.99,  10, -10.1, \"Nicolas\"  ";
        System.out.println(query);
        queryTranslator.translate(new StringQuery(query));
    }

    @Test
    public void translate3() throws Exception {
        String query = " Employee  name  in     \"Jessica\"  \"Alfred\"  \"Nicolas\" or 9.99 or name is less than 1111-12-13 and Site name is 9.99";
        System.out.println(query);
        queryTranslator.translate(new StringQuery(query));
    }

    @Test
    public void translate4() throws Exception {
        String query = " Employee  name is between 10 and 20 and [0:11] or Site name is \" I don't know\"";
        System.out.println(query);
        queryTranslator.translate(new StringQuery(query));
    }
}