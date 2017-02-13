import domain.keyword.Keyword;
import domain.keyword.KeywordRepository;
import infrastructure.InMemoryKeywordRepository;
import infrastructure.KeywordDevDataFactory;
import persistence.SQLHelper;
import persistence.SQLiteHelper;

import java.sql.SQLException;
import java.util.List;

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
}
