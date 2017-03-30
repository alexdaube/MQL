import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import services.query.QueryDto;
import services.query.QueryService;

import static spark.Spark.exception;
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

        post("/suggestions","application/json", (request, response) ->
                queryService.getNextSuggestion(gson.fromJson(request.body(), QueryDto.class)), gson::toJson);

        exception(Exception.class, (exception, request, response) -> {
            response.status(404);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("errorMessage", exception.getMessage());
            response.body(jsonObject.toString());
        });
    }
}