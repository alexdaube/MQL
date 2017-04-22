package infrastructure.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import configuration.keywords.EntityKeyword;
import configuration.keywords.JunctionKeyword;
import configuration.keywords.OperatorKeyword;
import org.bson.BsonDocument;
import persistence.MongoDbHelper;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class MongoDBClient {
    private final MongoDbHelper mongoDbHelper;
    private Gson gson;

    public MongoDBClient(MongoDbHelper mongoDbHelper) {
        this.gson = new GsonBuilder().create();
        this.mongoDbHelper = mongoDbHelper;
        this.mongoDbHelper.startConnection();
    }

    public Collection<EntityKeyword> findAllEntityKeyword() {
        Collection<EntityKeyword> entities = new HashSet<>();
        List<BsonDocument> docs = this.mongoDbHelper.findEntityDocuments();
        for (BsonDocument entity : docs) {
            String data = entity.toJson();
            entities.add(gson.fromJson(data, EntityKeyword.class));
        }
        return entities;
    }

    public Collection<JunctionKeyword> findAllJunctionKeyword() {
        Collection<JunctionKeyword> junctions = new HashSet<>();
        List<BsonDocument> docs = this.mongoDbHelper.findJunctionDocuments();
        if (!docs.isEmpty()) {
            for (BsonDocument entity : docs) {
                String data = entity.toJson();
                junctions.add(gson.fromJson(data, JunctionKeyword.class));
            }
        }

        return junctions;
    }

    public Collection<OperatorKeyword> findAllOperatorKeyword() {
        Collection<OperatorKeyword> operators = new HashSet<>();
        List<BsonDocument> docs = this.mongoDbHelper.findOperatorDocuments();
        if (!docs.isEmpty()) {
            for (BsonDocument entity : docs) {
                String data = entity.toJson();
                operators.add(gson.fromJson(data, OperatorKeyword.class));
            }
        }
        return operators;
    }
}
