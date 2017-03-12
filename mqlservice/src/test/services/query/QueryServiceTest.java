package services.query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSchema;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbSpec;
import com.healthmarketscience.sqlbuilder.dbspec.basic.DbTable;
import configuration.keywords.*;
import domain.DbClient;
import domain.keywords.Keyword;
import domain.keywords.KeywordRepository;
import domain.keywords.KeywordsResolver;
import domain.keywords.KeywordsSet;
import domain.query.builder.QueryBuilder;
import domain.query.builder.SqlQueryBuilder;
import domain.query.translators.MqlQueryTranslator;
import infrastructure.InMemoryKeywordRepository;
import infrastructure.InterpreterKeywordFactory;
import infrastructure.KeywordDevDataFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.keyword.KeywordsRegistrar;
import services.keyword.KeywordsValueRegistrar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@RunWith(MockitoJUnitRunner.class)
public class QueryServiceTest {

    @Mock
    private DbClient dbClient;
    private QueryService queryService;
    private QueryDto queryDto;

    @Before
    public void setUp() {
        queryService = new QueryService(dbClient, new MqlQueryTranslator(initBuilder(), initResolver()));
        queryDto = new QueryDto();
    }

    @Test
    public void given_whenExecuteQuery_then() {
        String query = "Site SiteId is equal to \"9999\"";
        System.err.println(query);
        queryDto.query = query;
        queryService.executeQuery(queryDto);
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

        KeywordRepository keywordRepository = new InMemoryKeywordRepository(keywordsSet);
        KeywordsRegistrar registrar = KeywordsRegistrar.create();
        keywordRepository.findAllKeywords().forEach(registrar::register);

        return registrar.createKeywordsResolver();
    }
}