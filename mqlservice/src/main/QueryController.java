import com.google.gson.*;

import java.util.Map;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;
import spark.Request;

import static spark.Spark.post;

public class QueryController {
    private static final String EQUIPMENTS_DATA = "[{name: 'Router', barcode: \"#1234544343\", projectNumber: 24},{name: 'Wire', barcode: \"#864fq23435\", projectNumber: 28},{name: 'Server', barcode: \"#2d342rf7334\", projectNumber: 28}]";
    private static final String SITES_DATA = "[{site: 'Québec', zipCode: 12345, latitude: 24, longitude: 100},{site: 'Montréal', zipCode: 86435, latitude: 28, longitude: 10},{site: 'Trois-Rivière', zipCode: 27334, latitude: 34, longitude: 120},{site: 'Gatineau', zipCode: 32345, latitude: 35, longitude: 110},{site: 'Drumondville', zipCode: 12215, latitude: 64, longitude: 108}]";

    public void initializeEndPoints() {
        post("/query", "application/json", (request, response) -> {
            Map<String, String> body = parseBody(request);
            return getEquipmentOrSiteData(body.get("query"));
        }, new Gson()::toJson);
    }

    private Map<String, String> parseBody(Request request) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();

        return gson.fromJson(request.body(), type);
    }

    // For testing the demo ...
    private JsonArray getEquipmentOrSiteData(String query) {
        String data = (query.contains("equip")) ? EQUIPMENTS_DATA : SITES_DATA;
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(data).getAsJsonArray();
    }
}