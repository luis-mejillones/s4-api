package services;

import models.Student;
import org.bson.Document;
import org.slf4j.Logger;

import java.util.List;
import java.util.Random;

public class StudentService {
    private final Logger logger;
    private final StudentRepository repository;

    public StudentService(
            StudentRepository repository,
            Logger logger
    ) {
        this.repository = repository;
        this.logger = logger;
    }

    public Student create(Student student) {
        return this.repository.create(student);
    }

    public Student getById(String id) {
        return this.repository.getById(id);
    }

    public Boolean delete(String id) {
        return this.repository.delete(id);
    }

    public Student updatePatch(String id, Student newData) {
        Student currentStudent = this.getById(id);
        currentStudent.update(newData);

        return this.repository.update(currentStudent);
    }

    public List<Document> getAll() {
        return this.repository.getAll();
    }

//    public void saveProcessingTime(Long elapsedTime, String verb, String uri) {
//        ProcessingTime processingTime = new ProcessingTime();
//        processingTime.timestamp = ZonedDateTime.now();
//        processingTime.elapsedTime = elapsedTime;
//        processingTime.service = MERCHANT_SERVICE_NAME;
//        processingTime.verb = verb;
//        processingTime.uri = uri;
//        processingTime.save();
//    }

    private long generateRandom() {
        Random random = new Random();

        return random.nextInt(16);
    }
}
