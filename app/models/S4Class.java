package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import utils.S4Serializable;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class S4Class implements S4Serializable {
    @BsonProperty(value = "_id")
    private String code;

    @BsonProperty
    private String title;

    @BsonProperty
    private String description;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public String getId() {
        return this.code;
    }

    @JsonIgnore
    public Map<String, String> getFields() {
        Map<String, String> fields = new HashMap<>();
        fields.put("title", this.title);
        fields.put("description", this.description);

        return fields;
    }

    @JsonIgnore
    public Document toDocument() {
        return new Document("_id", this.code)
                .append("title", this.title)
                .append("description", this.description);
    }

    @JsonIgnore
    public void fromDocument(Document document) {
        S4Class student = new S4Class();
        this.code = document.getString("_id");
        this.title = document.getString("title");
        this.description = document.getString("description");
    }

    @JsonIgnore
    public void update(S4Class newData) {
        this.title = this.title.equals(newData.title) ? this.title : newData.title;
        this.description = this.description.equals(newData.description) ? this.description : newData.description;
    }
}
