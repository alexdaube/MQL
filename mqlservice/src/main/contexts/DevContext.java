package contexts;

import domain.DbClient;
import domain.keywords.KeywordRepository;
import domain.keywords.KeywordsResolver;
import domain.query.builder.QueryBuilder;
import domain.query.translators.InitialTranslatorState;
import domain.query.translators.MqlQueryTranslator;
import domain.query.translators.QueryTranslator;
import infrastructure.clients.KeywordClient;
import infrastructure.clients.SqlLiteClient;
import infrastructure.repositories.CmsKeywordRepository;
import persistence.SQLHelper;
import persistence.SQLiteHelper;
import services.locator.ServiceLocator;
import services.locator.ServiceRegistrar;

public class DevContext implements Context {

    @Override
    public void apply() {
        ServiceLocator.reset();
        ServiceRegistrar serviceRegistrar = ServiceLocator.getInstance();

        serviceRegistrar.register(KeywordClient::new).asSingleInstance().of(KeywordClient.class);
        serviceRegistrar.register(() -> new CmsKeywordRepository(ServiceLocator.getInstance().resolve(KeywordClient.class)))
                .asSingleInstance().of(KeywordRepository.class);

        serviceRegistrar.register(SQLiteHelper::new).asSingleInstance().of(SQLHelper.class);
        serviceRegistrar.register(() -> new SqlLiteClient(ServiceLocator.getInstance().resolve(SQLHelper.class)))
                .asSingleInstance().of(DbClient.class);

        serviceRegistrar.register(() -> {
            QueryBuilder queryBuilder = QueryBuilderCreator.create();
            KeywordsResolver keywordsResolver = KeywordsResolverCreator.create();
            return new MqlQueryTranslator(new InitialTranslatorState(queryBuilder, keywordsResolver), queryBuilder);
        }).asMultipleInstances().of(QueryTranslator.class);
    }
}
