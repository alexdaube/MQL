package infrastructure.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import configuration.keywords.EntityKeyword;
import configuration.keywords.JunctionKeyword;
import configuration.keywords.OperatorKeyword;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public class HTTPClient {
    private final String TABLES_URL = "http://localhost:3000/tables/";
    private final String JUNCTIONS_URL = "http://localhost:3000/junctions/";
    private final String OPERATORS_URL = "http://localhost:3000/operators/";
    private OkHttpClient client;
    private Gson gson;

    public HTTPClient() {
        this.gson = new GsonBuilder().create();
        this.client = new OkHttpClient();
    }

    public Collection<EntityKeyword> fetchEntities() throws IOException {
        Request request = new Request.Builder().url(TABLES_URL).build();
        Response response = this.client.newCall(request).execute();
        Type collectionType = new TypeToken<Collection<EntityKeyword>>() {
        }.getType();
        Collection<EntityKeyword> entities = this.gson.fromJson(response.body().string(), collectionType);

        return entities;
    }

    public Collection<JunctionKeyword> fetchJunctions() throws IOException {
        Request request = new Request.Builder().url(JUNCTIONS_URL).build();
        Response response = this.client.newCall(request).execute();
        Type collectionType = new TypeToken<Collection<JunctionKeyword>>() {
        }.getType();
        Collection<JunctionKeyword> junctions = this.gson.fromJson(response.body().string(), collectionType);

        return junctions;
    }

    public Collection<OperatorKeyword> fetchOperators() throws IOException {
        Request request = new Request.Builder().url(OPERATORS_URL).build();
        Response response = this.client.newCall(request).execute();
        Type collectionType = new TypeToken<Collection<OperatorKeyword>>() {
        }.getType();
        Collection<OperatorKeyword> operators = this.gson.fromJson(response.body().string(), collectionType);

        return operators;
    }
}
