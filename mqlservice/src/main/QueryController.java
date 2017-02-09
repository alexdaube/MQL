import com.google.gson.Gson;
import static spark.Spark.get;

public class QueryController {

    public void initializeEndPoints() {
        get("/query/:query", (request, response) ->
                "Query for now: " + request.params(":query"), new Gson()::toJson);
    }
}