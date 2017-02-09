package domain.translators;

import domain.QueryBuilder;
import domain.StringQuery;
import org.junit.Before;
import org.junit.Test;

public class QueryTranslatorTest {
    private QueryTranslator queryTranslator;

    @Before
    public void setUp() throws Exception {
        queryTranslator = new QueryTranslator(QueryBuilder.create());
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
        String query = " Employee  name  in     \"Jessica\"  \"Alfred\"  \"Nicolas\"  ";
        System.out.println(query);
        queryTranslator.translate(new StringQuery(query));
    }
}