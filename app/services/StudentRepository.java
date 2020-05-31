package services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import models.Student;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class StudentRepository {
    private MongoCollection<Document> collection;
    private Logger logger;

    public StudentRepository(MongoCollection<Document> collection, Logger logger) {
        this.collection = collection;
        this.logger = logger;
    }

    public Student create(Student student) {
        Document doc = student.toDocument();
        this.collection.insertOne(doc);
        this.logger.info(">>> Student created with id: " + student.id);

        return student;
    }

    public Student getById(String id) {
        Document document = this.collection.find(eq("_id", id)).first();

        if (document != null) {
            this.logger.info(">>> Student retrieved id: " + id);
        } else {
            this.logger.info(">>> Student with id: " + id + " not found");
        }
        Student student = new Student();
        student.fromDocument(document);

        return student;
    }

    public Boolean delete(String id) {
        Long count = this.collection.deleteOne(eq("_id", id)).getDeletedCount();

        if (count > 0) {
            this.logger.info(">>> Student deleted id: " + id);
        } else {
            this.logger.info(">>> Student with id: " + id + " not found");
        }

        return count > 0;
    }

    public Student update(Student student) {
        UpdateResult updateResult = this.collection.updateMany(
                Filters.eq("_id", student.id),
                Updates.combine(
                        Updates.set("lastName", student.lastName),
                        Updates.set("firstName", student.firstName)
                ));

        this.logger.info(">>> Student updated with id: " + student.id);

        return student;
    }

    public List<Document> getAll() {
        MongoCursor<Document> cursor = this.collection.find().iterator();
        List<Document> list = this.retrieveDocuments(cursor);
        this.logger.info(">>> Merchants retrieved: " + list.size());

        return list;
    }

    private List<Document> retrieveDocuments(MongoCursor<Document> cursor) {
        List<Document> list = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                list.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return list;
    }
}
