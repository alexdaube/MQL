package persistence;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MongoDbHelperTest {

    private MongoDbHelper mongoHelper;

    @Before
    public void setUp() {
        mongoHelper = new MongoDbHelper();
    }

    @Test
    public void canConnectToMongoDatabase() {
        mongoHelper.startConnection();
        assertTrue(mongoHelper.canRetrieveDatabaseConnections());
    }

    @Test
    public void canReadEntitiesFromDatabase() {
        mongoHelper.startConnection();
        assertFalse(mongoHelper.findEntities().isEmpty());
    }


}