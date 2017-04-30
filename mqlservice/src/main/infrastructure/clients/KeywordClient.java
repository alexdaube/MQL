package infrastructure.clients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import configuration.keywords.EntityKeyword;
import configuration.keywords.JunctionKeyword;
import configuration.keywords.OperatorKeyword;
import infrastructure.CommunicationException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public class KeywordClient {
    private final String TABLES_URL = "http://localhost:3000/tables";
    private final String JUNCTIONS_URL = "http://localhost:3000/junctions";
    private final String OPERATORS_URL = "http://localhost:3000/operators";
    private OkHttpClient client;
    private Gson gson;

    public KeywordClient() {
        this.gson = new GsonBuilder().create();
        this.client = new OkHttpClient();
    }

    public Collection<EntityKeyword> fetchEntities() {
        Request request = new Request.Builder().url(TABLES_URL).build();
        try {
            Response response = this.client.newCall(request).execute();
            Type collectionType = new TypeToken<Collection<EntityKeyword>>() {}.getType();
            return this.gson.fromJson(response.body().string(), collectionType);
        } catch (IOException e) {
            throw new CommunicationException("Fetch entities failed...");
        }
    }

    public Collection<JunctionKeyword> fetchJunctions() {
        Request request = new Request.Builder().url(JUNCTIONS_URL).build();
        try {
            Response response = this.client.newCall(request).execute();
            Type collectionType = new TypeToken<Collection<JunctionKeyword>>() {}.getType();
            return this.gson.fromJson(response.body().string(), collectionType);
        } catch (IOException e) {
            throw new CommunicationException("Fetch junctions failed...");
        }
    }

    public Collection<OperatorKeyword> fetchOperators() {
        Request request = new Request.Builder().url(OPERATORS_URL).build();
        try {
            Response response = this.client.newCall(request).execute();
            Type collectionType = new TypeToken<Collection<OperatorKeyword>>() {}.getType();
            return this.gson.fromJson(response.body().string(), collectionType);
        } catch (IOException e) {
            throw new CommunicationException("Fetch operators failed...");
        }
    }
}
