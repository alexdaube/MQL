package configuration.keywords;

import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class EntityKeyword {
    @SerializedName("name")
    public String keyword;
    @SerializedName("keywords")
    public Set<String> synonyms = new HashSet<>();
    @SerializedName("foreignKeys")
    public List<ForeignKey> foreignKeys = new LinkedList<>();
    @SerializedName("columns")
    public Set<Attribute> attributes = new HashSet<>();
}
