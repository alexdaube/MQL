package configuration.keywords;

import com.google.gson.annotations.SerializedName;

public class ForeignKey {
    @SerializedName("table")
    public String tableName;
    @SerializedName("from_column")
    public String fromColumn;
    @SerializedName("to_column")
    public String toColumn;
}
