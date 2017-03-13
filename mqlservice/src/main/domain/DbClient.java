package domain;

import java.util.List;
import java.util.Map;

public interface DbClient {
    List<Map<String, Object>> execute(String query);
}
