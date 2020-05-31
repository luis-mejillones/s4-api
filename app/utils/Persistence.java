package utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import models.Student;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Persistence {
    private final MongoCollection<Document> collection;

    public Persistence(final MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void insert(Student student) {
        Document doc = student.toDocument();
        this.collection.insertOne(doc);
    }

    public Document find(String id) {
        return this.collection.find(eq("_id", id)).first();
    }

    public Boolean delete(String id) {
        Long count = this.collection.deleteOne(eq("_id", id)).getDeletedCount();

        return  count > 0;
    }

    public Boolean update(Student student) {
        UpdateResult updateResult = this.collection.updateMany(
                Filters.eq("_id", student.getId()),
                Updates.combine(
                        Updates.set("lastName", student.getLastName()),
                        Updates.set("firstName", student.getFirstName())
                ));

        return updateResult.getModifiedCount() > 0;
    }
}
