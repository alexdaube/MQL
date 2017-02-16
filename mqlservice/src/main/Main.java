import domain.keywords.DefaultKeywordResolver;
import domain.keywords.Keywords;
import domain.keywords.KeywordsResolver;
import infrastructure.KeywordDevDataFactory;
import persistence.SQLHelper;

import java.sql.SQLException;

import static spark.Spark.before;
import static spark.Spark.options;

public class Main {
    public static void main(String[] args) {
        KeywordsResolver keywordsResolver = initKeywordRepositoryWithDevData(new KeywordDevDataFactory());
        Keywords entities = keywordsResolver.resolveEntities();
        Keywords attributes = keywordsResolver.resolveAttributes();
        
        //initDatabaseConnection(new SQLiteHelper());
        //initServer(new QueryController());
    }

    private static KeywordsResolver initKeywordRepositoryWithDevData(KeywordDevDataFactory keywordDevDataFactory) {
        KeywordsResolver keywordsResolver = new DefaultKeywordResolver();
        keywordsResolver.initializeKeywords(keywordDevDataFactory.readEntitiesFromJSON());

        return keywordsResolver;
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

    ;
}
