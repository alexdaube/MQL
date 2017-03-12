package contexts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import configuration.keywords.*;
import domain.keywords.Keyword;
import domain.keywords.KeywordRepository;
import domain.keywords.KeywordsResolver;
import domain.keywords.KeywordsSet;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SqlQueryBuilder;
import infrastructure.InMemoryKeywordRepository;
import infrastructure.InterpreterKeywordFactory;
import infrastructure.KeywordDevDataFactory;
import infrastructure.KeywordResolver.KeywordsRegistrar;
import infrastructure.KeywordResolver.KeywordsResolverGenerator;
import services.locator.ServiceLocator;
import services.locator.ServiceRegistrar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class DevContext implements Context {

    @Override
    public void apply() {
        ServiceRegistrar serviceRegistrar = ServiceLocator.getInstance();
        serviceRegistrar.register(this::initKeywordRepository).asSingleInstance().of(KeywordRepository.class);
        serviceRegistrar.register(this::initBuilder).asMultipleInstances().of(QueryBuilder.class);
        serviceRegistrar.register(this::initResolver).asMultipleInstances().of(KeywordsResolver.class);
    }

    private QueryBuilder initBuilder() {
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
        return queryBuilder;
    }

    private KeywordsResolver initResolver() {
        KeywordsRegistrar registrar = KeywordsRegistrar.create();
        ServiceLocator.getInstance().resolve(KeywordRepository.class).findAllKeywords().forEach(registrar::register);

        return registrar.createKeywordsResolver();
    }

    private KeywordRepository initKeywordRepository() {
        EntityMap entityMap = new KeywordDevDataFactory().readEntitiesFromJSON();
        InterpreterKeywordFactory keywordFactory = new InterpreterKeywordFactory();
        KeywordsSet keywordsSet = keywordFactory.createKeywordsFromEntityMap(entityMap);

        try (final Reader data = new FileReader("./src/main/configuration/junction_config.json")) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Junctions junctions = gson.fromJson(data, Junctions.class);
            for (JunctionKeyword keyword : junctions.junctions) {
                keywordsSet.getKeywords().add(new Keyword(keyword.keywords.iterator().next(), Keyword.Type.valueOf(keyword.type), keyword.keywords));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (final Reader data = new FileReader("./src/main/configuration/operator_config.json")) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Operators operators = gson.fromJson(data, Operators.class);
            for (OperatorKeyword keyword : operators.operators) {
                keywordsSet.getKeywords().add(new Keyword(keyword.keywords.iterator().next(), Keyword.Type.valueOf(keyword.type), keyword.keywords));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new InMemoryKeywordRepository(keywordsSet);
    }
}
