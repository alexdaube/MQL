package configuration.keywords;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class JunctionKeyword {
    @SerializedName("type")
    public String type;
    @SerializedName("keywords")
    public List<String> keywords = new LinkedList<>();
}
