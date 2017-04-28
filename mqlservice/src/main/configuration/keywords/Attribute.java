package configuration.keywords;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class Attribute {
    @SerializedName("name")
    public String keyword;
    @SerializedName("keywords")
    public Set<String> synonyms = new HashSet<>();
}
