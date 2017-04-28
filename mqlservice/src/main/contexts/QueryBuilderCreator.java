package contexts;

import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import configuration.keywords.EntityKeyword;
import configuration.keywords.ForeignKey;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SqlQueryBuilder;
import infrastructure.clients.KeywordClient;
import services.locator.ServiceLocator;

import java.util.Collection;

public class QueryBuilderCreator {

    private static Collection<EntityKeyword> entities;

    public static QueryBuilder create() {
        entities = ServiceLocator.getInstance().resolve(KeywordClient.class).fetchEntities();
        DbSchema dbSchema = new DbSpec().addDefaultSchema();
        for (EntityKeyword entityKeyword : entities) {
            DbTable table = dbSchema.addTable(entityKeyword.keyword);
            entityKeyword.attributes.forEach(c -> table.addColumn(c.keyword));
        }
        QueryBuilder queryBuilder = new SqlQueryBuilder(dbSchema).withAllTablesColumns();
        for (EntityKeyword entityKeyword : entities) {
            for (ForeignKey foreignKey : entityKeyword.foreignKeys) {
                queryBuilder.withJoin(entityKeyword.keyword, foreignKey.tableName,
                        foreignKey.fromColumn, foreignKey.toColumn);
            }
        }
        return queryBuilder;
    }
}
