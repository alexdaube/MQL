package persistence;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SQLiteHelperTest {

    private SQLiteHelper sqlHelper;

    @Before
    public void setUp() {
        sqlHelper = new SQLiteHelper();
    }

    @Test
    public void canConnectToTestDatabase() throws Exception {
        sqlHelper.startConnection();
        assertTrue(sqlHelper.isConnected());
    }
}