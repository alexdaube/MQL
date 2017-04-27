package configuration.keywords;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EntityKeyword {
    @SerializedName("keyword")
    public String keyword;
    @SerializedName("synonyms")
    public Set<String> synonyms = new HashSet<>();
    @SerializedName("foreign_keys")
    public List<ForeignKey> foreignKeys = new LinkedList<>();
    @SerializedName("attributes")
    public Set<Attribute> attributes = new HashSet<>();
}
