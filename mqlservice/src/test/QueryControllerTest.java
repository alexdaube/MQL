import com.despegar.http.client.HttpResponse;
import com.despegar.http.client.PostMethod;
import com.despegar.sparkjava.test.SparkServer;
import contexts.DevContext;
import org.junit.ClassRule;
import org.junit.Test;
import persistence.SQLHelper;
import services.locator.ServiceLocator;
import services.query.QueryService;
import spark.servlet.SparkApplication;

import static org.junit.Assert.assertEquals;

public class QueryControllerTest {
    private static final int SERVER_PORT = 4000;
    private static final String URL = "/query";
    @ClassRule
    public static SparkServer<QueryControllerTestSparkApplication> testServer = new SparkServer<>(
            QueryControllerTest.QueryControllerTestSparkApplication.class, SERVER_PORT);

    // This would probably be more of a functional test than a unit test.
    // It does start a server...
    @Test
    public void canAccessApiQueryEndpointThroughGetMethod() throws Exception {
        PostMethod post = testServer.post(URL, "{query: 'Site SiteId is greater than -1'}", false);
        HttpResponse response = testServer.execute(post);
        assertEquals(200, response.code());
    }

    public static class QueryControllerTestSparkApplication implements SparkApplication {
        @Override
        public void init() {
            new DevContext().apply();
            Main.initDatabaseConnection(ServiceLocator.getInstance().resolve(SQLHelper.class));
            QueryController queryController = new QueryController(new QueryService());
            queryController.initializeEndPoints();
        }
    }
}