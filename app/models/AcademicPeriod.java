package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import utils.S4Serializable;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AcademicPeriod implements S4Serializable {
    @BsonProperty(value = "_id")
    private String id;

    @BsonProperty
    private String classId;

    @BsonProperty
    private String studentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @JsonIgnore
    public Map<String, String> getFields() {
        Map<String, String> fields = new HashMap<>();
        fields.put("classId", this.classId);
        fields.put("studentId", this.studentId);

        return fields;
    }

    @JsonIgnore
    public Document toDocument() {
        return new Document("_id", this.id)
                .append("classId", this.classId)
                .append("studentId", this.studentId);
    }

    @JsonIgnore
    public void fromDocument(Document document) {
        AcademicPeriod student = new AcademicPeriod();
        this.id = document.getString("_id");
        this.classId = document.getString("classId");
        this.studentId = document.getString("studentId");
    }

    public static class Builder {
        private String id;
        private String classId;
        private String studentId;

        public AcademicPeriod.Builder withId(String id) {
            this.id = id;

            return this;
        }

        public AcademicPeriod.Builder withClassId(String classId) {
            this.classId = classId;

            return this;
        }

        public AcademicPeriod.Builder withStudentId(String studentId) {
            this.studentId = studentId;

            return this;
        }

        public AcademicPeriod build() {
            AcademicPeriod out = new AcademicPeriod();
            out.setId(this.id);
            out.setClassId(this.classId);
            out.setStudentId(this.studentId);

            return out;
        }
    }
}
