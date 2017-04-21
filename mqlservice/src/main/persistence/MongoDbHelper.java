package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MongoDbHelper {

    private final Gson gson;
    private final MongoClient mongoClient;
    private final String DATABASE = "mql";
    private final String ENTITY = "entities";
    private final String JUNCTION = "junctions";
    private final String OPERATOR = "operators";
    private MongoDatabase mongoDB;

    public MongoDbHelper() {
        this.mongoClient = new MongoClient();
        this.gson = new GsonBuilder().create();
    }

    public void startConnection() {
        try {
            this.mongoDB = mongoClient.getDatabase(DATABASE);
            System.out.println("Connected to MongoDB");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<BsonDocument> findEntities() {
        MongoCollection<BsonDocument> entityCollection = this.mongoDB.getCollection(ENTITY, BsonDocument.class);
        List<BsonDocument> entities = entityCollection.find().into(new ArrayList<BsonDocument>());
        return entities;
    }

    public List<BsonDocument> findJunctions() {
        MongoCollection<BsonDocument> junctionCollection = this.mongoDB.getCollection(JUNCTION, BsonDocument.class);
        List<BsonDocument> junctions = junctionCollection.find().into(new ArrayList<BsonDocument>());
        return junctions;
    }

    public List<BsonDocument> findOperators() {
        MongoCollection<BsonDocument> operatorCollection = this.mongoDB.getCollection(ENTITY, BsonDocument.class);
        List<BsonDocument> operators = operatorCollection.find().into(new ArrayList<BsonDocument>());
        return operators;
    }

    public boolean canRetrieveDatabaseConnections() {
        Document serverStatus = this.mongoDB.runCommand(new Document("serverStatus", 1));
        Map connections = (Map) serverStatus.get("connections");
        Integer current = (Integer) connections.get("current");
        return connections.size() != 0;
    }

}
