import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import services.query.QueryDto;
import services.query.QueryService;

import java.util.List;
import java.util.Map;

import static spark.Spark.post;

public class QueryController {

    private final QueryService queryService;
    private final Gson gson;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
        this.gson = new GsonBuilder().create();
    }

    public void initializeEndPoints() {
        post("/query", "application/json", (request, response) ->
                queryService.executeQuery(gson.fromJson(request.body(), QueryDto.class)), gson::toJson);
    }

    private JsonArray toJsonArray(List<Map<String, Object>> result) {
        return gson.toJsonTree(result, new TypeToken<List<Map<String, Object>>>() {
        }.getType()).getAsJsonArray();
    }
}