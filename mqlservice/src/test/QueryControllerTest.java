import com.despegar.http.client.HttpResponse;
import com.despegar.http.client.PostMethod;
import com.despegar.sparkjava.test.SparkServer;
import org.junit.ClassRule;
import org.junit.Test;
import spark.servlet.SparkApplication;

import static org.junit.Assert.assertEquals;

public class QueryControllerTest {
    private static final int SERVER_PORT = 4000;
    private static final String URL = "/query";

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
        PostMethod post = testServer.post(URL, "{query: 'equipment'}", false);
        HttpResponse response = testServer.execute(post);
        assertEquals(200, response.code());
    }
}