package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;
import utils.S4Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student implements S4Serializable {
    @BsonProperty(value = "_id")
    private String id;

    @BsonProperty
    private String lastName;

    @BsonProperty
    private String firstName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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
