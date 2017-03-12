package configuration.keywords;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class OperatorKeyword {
    @SerializedName("type")
    public String type;
    @SerializedName("keywords")
    public Set<String> keywords = new HashSet<>();
}
