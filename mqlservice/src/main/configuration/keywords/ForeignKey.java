package configuration.keywords;

import com.google.gson.annotations.SerializedName;

public class ForeignKey {
    @SerializedName("toTable")
    public String tableName;
    @SerializedName("fromColumn")
    public String fromColumn;
    @SerializedName("toColumn")
    public String toColumn;
}
