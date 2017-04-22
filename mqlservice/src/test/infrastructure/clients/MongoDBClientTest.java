package infrastructure.clients;

import configuration.keywords.EntityKeyword;
import configuration.keywords.JunctionKeyword;
import configuration.keywords.OperatorKeyword;
import org.junit.Before;
import org.junit.Test;
import persistence.MongoDbHelper;

import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class MongoDBClientTest {
    private MongoDBClient mongoDBClient;

    @Before
    public void setUp() {
        this.mongoDBClient = new MongoDBClient(new MongoDbHelper());
    }

    @Test
    public void convertBSONDocumentToEntity() {
        Collection<EntityKeyword> entities = mongoDBClient.findAllEntityKeyword();
        assertFalse(entities.isEmpty());
    }

    @Test
    public void convertBSONDocumentToJunction() {
        Collection<JunctionKeyword> junctions = mongoDBClient.findAllJunctionKeyword();
        fail();
    }

    @Test
    public void convertBSONDocumentToOperator() {
        Collection<OperatorKeyword> operators = mongoDBClient.findAllOperatorKeyword();
        fail();
    }
}