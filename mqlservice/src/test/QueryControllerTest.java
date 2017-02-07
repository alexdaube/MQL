import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpResponse;
import com.despegar.sparkjava.test.SparkServer;
import org.junit.ClassRule;
import org.junit.Test;
import spark.servlet.SparkApplication;
import com.multitel.mql.mqlservice.QueryController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueryControllerTest {
    private static final int SERVER_PORT = 8000;
    private static final String URL = "/query/Sites%20that%20have%20id%20=%201";
    private static final String QUERY = "Sites that have id";

    public static class QueryControllerTestSparkApplication implements SparkApplication {
        @Override
        public void init() {
            QueryController queryController = new QueryController();
            queryController.initializeEndPoints();
        }
    }

    @ClassRule
    public static SparkServer<QueryControllerTestSparkApplication> testServer = new SparkServer<>(
            QueryControllerTest.QueryControllerTestSparkApplication.class, SERVER_PORT);

    // This would probably be more of a functional test than a unit test.
    // It does start a server...
    @Test
    public void canAccessApiQueryEndpointThroughGetMethod() throws Exception {
        GetMethod get = testServer.get(URL, false);
        HttpResponse response = testServer.execute(get);
        assertEquals(200, response.code());
        assertTrue(new String(response.body()).contains(QUERY));
    }
}
