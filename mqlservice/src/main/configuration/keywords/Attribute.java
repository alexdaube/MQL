package configuration.keywords;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class Attribute {
    @SerializedName("keyword")
    public String keyword;
    @SerializedName("synonyms")
    public Set<String> synonyms = new HashSet<>();
}
