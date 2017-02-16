import domain.keyword.Keyword;
import domain.keyword.KeywordRepository;
import infrastructure.InMemoryKeywordRepository;
import infrastructure.KeywordDevDataFactory;
import persistence.SQLHelper;
import persistence.SQLiteHelper;

import java.sql.SQLException;
import java.util.List;

import static spark.Spark.before;
import static spark.Spark.options;

public class Main {
    public static void main(String[] args) {
        initKeywordRepositoryWithDevData(new KeywordDevDataFactory());
        initDatabaseConnection(new SQLiteHelper());
        initServer(new QueryController());
    }

    private static void initKeywordRepositoryWithDevData(KeywordDevDataFactory keywordDevDataFactory) {
        KeywordRepository keywordRepository = new InMemoryKeywordRepository();
        List<Keyword> keywords = keywordDevDataFactory.createStubKeywords();

        for (Keyword keyword : keywords) {
            keywordRepository.create(keyword);
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
