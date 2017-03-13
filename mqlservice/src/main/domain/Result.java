package domain;

import java.util.List;
import java.util.Map;

public class Result {
    private final List<Map<String, Object>> elements;

    public Result(List<Map<String, Object>> elements) {
        this.elements = elements;
    }

    public List<Map<String, Object>> getElements() {
        return elements;
    }
}
