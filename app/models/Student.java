package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {
    public Student() {
    }

    @BsonProperty(value = "_id")
    public String id;

    @BsonProperty
    public String lastName;

    @BsonProperty
    public String firstName;

    @JsonIgnore
    public Document toDocument() {
        return new Document("_id", this.id)
                .append("lastName", this.lastName)
                .append("firstName", this.firstName);
    }

    @JsonIgnore
    public void fromDocument(Document document) {
        Student student = new Student();
        this.id = document.getString("_id");
        this.lastName = document.getString("lastName");
        this.firstName = document.getString("firstName");
    }

    @JsonIgnore
    public void update(Student newData) {
        this.lastName = this.lastName.equals(newData.lastName) ? this.lastName : newData.lastName;
        this.firstName = this.firstName.equals(newData.firstName) ? this.firstName : newData.firstName;
    }
}
