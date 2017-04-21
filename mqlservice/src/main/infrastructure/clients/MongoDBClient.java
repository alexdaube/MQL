package infrastructure.clients;

import persistence.MongoDbHelper;

public class MongoDBClient {
    private final MongoDbHelper mongoDbHelper;

    public MongoDBClient(MongoDbHelper mongoDbHelper) {
        this.mongoDbHelper = mongoDbHelper;
    }
}
