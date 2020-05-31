package utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class Persistence<T extends S4Serializable> {
    private final MongoCollection<Document> collection;

    public Persistence(final MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void insert(T student) {
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

    public Boolean update(T data) {
        List<Bson> updates = new ArrayList<>();
        data.getFields().keySet().stream().forEach(
                item -> updates.add(Updates.set(item, data.getFields().get(item)))
        );

        UpdateResult updateResult = this.collection.updateMany(
                Filters.eq("_id", data.getId()),
                Updates.combine(updates)
        );

        return updateResult.getModifiedCount() > 0;
    }
}
