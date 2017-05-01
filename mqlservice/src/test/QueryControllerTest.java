import com.despegar.http.client.HttpResponse;
import com.despegar.http.client.PostMethod;
import com.despegar.sparkjava.test.SparkServer;
import org.junit.ClassRule;
import org.junit.Test;
import services.query.QueryService;
import spark.servlet.SparkApplication;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class QueryControllerTest {
    private static final int SERVER_PORT = 4000;
    private static final String URL = "/query";
    @ClassRule
    public static SparkServer<QueryControllerTestSparkApplication> testServer = new SparkServer<>(
            QueryControllerTest.QueryControllerTestSparkApplication.class, SERVER_PORT);

    @Test
    public void canAccessApiQueryEndpointThroughGetMethod() throws Exception {
        PostMethod post = testServer.post(URL, "{\"query\": \"Site SiteId is 1\"}", false);
        HttpResponse response = testServer.execute(post);
        assertEquals(200, response.code());
    }

    public static class QueryControllerTestSparkApplication implements SparkApplication {
        @Override
        public void init() {
            QueryService queryService = mock(QueryService.class);
            QueryController queryController = new QueryController(queryService);
            queryController.initializeEndPoints();
        }
    }
}