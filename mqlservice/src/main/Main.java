import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import configuration.keywords.EntityKeyword;
import configuration.keywords.EntityMap;
import configuration.keywords.ForeignKey;
import domain.keywords.KeywordRepository;
import domain.keywords.Keywords;
import domain.keywords.KeywordsSet;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SqlQueryBuilder;
import infrastructure.InMemoryKeywordRepository;
import infrastructure.InterpreterKeywordFactory;
import infrastructure.KeywordDevDataFactory;
import persistence.SQLHelper;

import java.sql.SQLException;

import static spark.Spark.before;
import static spark.Spark.options;

public class Main {
    public static void main(String[] args) {
        initKeywordRepositoryWithDevData(new KeywordDevDataFactory());
        //initDatabaseConnection(new SQLiteHelper());
        //initServer(new QueryController());
    }

    private static void initKeywordRepositoryWithDevData(KeywordDevDataFactory keywordDevDataFactory) {
        //TODO Build operators and junction keywords, add to resolver
        EntityMap entityMap = keywordDevDataFactory.readEntitiesFromJSON();
        InterpreterKeywordFactory keywordFactory = new InterpreterKeywordFactory();
        KeywordsSet keywordsSet = keywordFactory.createKeywordsFromEntityMap(entityMap);
        KeywordRepository keywordRepository = new InMemoryKeywordRepository(keywordsSet);
        Keywords keywords = keywordRepository.findAllKeywords();
    }

    private static void initDatabaseSchema() {
        EntityMap entityMap = new KeywordDevDataFactory().readEntitiesFromJSON();
        DbSchema dbSchema = new DbSpec().addDefaultSchema();
        for (EntityKeyword entityKeyword : entityMap.getEntityKeywords()) {
            DbTable table = dbSchema.addTable(entityKeyword.getKeyword());
            entityKeyword.getAttributes().forEach(c -> table.addColumn(c.getKeyword()));
        }
        QueryBuilder queryBuilder = new SqlQueryBuilder(dbSchema).withAllTablesColumns();
        for (EntityKeyword entityKeyword : entityMap.getEntityKeywords()) {
            for (ForeignKey foreignKey : entityKeyword.getForeignKeys()) {
                queryBuilder.withJoin(entityKeyword.getKeyword(), foreignKey.getTableName(),
                        foreignKey.getFromColumn(), foreignKey.getToColumn());
            }
        }
    }

    private static void initDatabaseConnection(SQLHelper sqlHelper) {
        try {
            sqlHelper.startConnection();
            sqlHelper.readDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void initServer(QueryController queryController) {
        enableCORS("*", "POST, GET, OPTIONS, DELETE, PUT", "x-requested-with, Content-Type");
        queryController.initializeEndPoints();
    }

    private static void enableCORS(final String origin, final String methods, final String headers) {
        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }
}
