package domain.translators;

import builders.KeywordsBuilder;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import domain.QueryBuilder;
import domain.StringQuery;
import domain.keyword.KeywordsResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mockito.BDDMockito.willReturn;

@RunWith(MockitoJUnitRunner.class)
public class QueryTranslatorTest {
    @Mock
    private KeywordsResolver keywordsResolver;
    private QueryTranslator queryTranslator;

    @Before
    public void setUp() throws Exception {
        willReturn(KeywordsBuilder.create().with("is").with("equal").with("to").with("in").build()).given(keywordsResolver).resolveOperators();
        willReturn(KeywordsBuilder.create().with("Employee").build()).given(keywordsResolver).resolveEntities();
        willReturn(KeywordsBuilder.create().with("name").build()).given(keywordsResolver).resolveAttributes();
        willReturn(KeywordsBuilder.create().with("and").with("or").build()).given(keywordsResolver).resolveJunctions();
        queryTranslator = new QueryTranslator(QueryBuilder.create(), keywordsResolver);
    }

    @Test
    public void translate0() throws Exception {
        String query = "   Employee name is \"Nicolas\"";
        System.out.println(query);
        queryTranslator.translate(new StringQuery(query));
    }

    @Test
    public void translate1() throws Exception {
        String query = "Employee.name is equal to \"Nicolas\"";
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
        String query = " Employee  name  in     \"Jessica\"  \"Alfred\"  \"Nicolas\" or name in 9.99 ";
        System.out.println(query);
        queryTranslator.translate(new StringQuery(query));
    }

    @Test
    public void test() throws Exception {
        Pattern pattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2})( (\\d{2}:\\d{2}:\\d{2}))?(.(\\d{3}))?");
        Matcher matcher = pattern.matcher("1111-12-13 14:15:16.178");
        matcher.find();
        System.out.println(matcher.group(1));
    }
}