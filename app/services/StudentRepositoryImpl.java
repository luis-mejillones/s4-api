package services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import models.Student;
import org.bson.Document;
import org.slf4j.Logger;

import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class StudentRepositoryImpl implements StudentRepository {
    private MongoCollection<Document> collection;
    private Logger logger;

    public StudentRepositoryImpl(MongoCollection<Document> collection, Logger logger) {
        this.collection = collection;
        this.logger = logger;
    }

    @Override
    public Student create(Student student) {
        Document doc = student.toDocument();
        this.collection.insertOne(doc);
        this.logger.info(">>> CREATE: Student with id: " + student.getId());

        return student;
    }

    @Override
    public Optional<Student> getById(String id) {
        Document document = this.collection.find(eq("_id", id)).first();

        if (document != null) {
            this.logger.info(">>> GET: Student retrieved id: " + id);
        } else {
            this.logger.info(">>> GET: Student with id: " + id + " not found");

            return Optional.empty();
        }
        Student student = new Student();
        student.fromDocument(document);

        return Optional.of(student);
    }

    @Override
    public Boolean delete(String id) {
        Long count = this.collection.deleteOne(eq("_id", id)).getDeletedCount();

        if (count > 0) {
            this.logger.info(">>> DELETE: Student id: " + id);
        } else {
            this.logger.info(">>> DELETE: Student with id: " + id + " not found");
        }

        return count > 0;
    }

    @Override
    public Student update(Student student) {
        UpdateResult updateResult = this.collection.updateMany(
                Filters.eq("_id", student.getId()),
                Updates.combine(
                        Updates.set("lastName", student.getLastName()),
                        Updates.set("firstName", student.getFirstName())
                ));

        this.logger.info(">>> UPDATE: Student updated with id: " + student.getId());

        return student;
    }
}
