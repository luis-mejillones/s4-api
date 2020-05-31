package utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Config {
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    public Config() {
        this.mongoClient = new MongoClient(Constants.MONGODB_HOST, Constants.MONGODB_PORT);
        this.database = this.mongoClient.getDatabase(Constants.MONGODB_DATABASE);
    }

    public MongoCollection<Document> getStudentPersistenceModel() {
        return this.database.getCollection(Constants.MONGODB_COLLECTION_STUDENT);
    }

    public MongoCollection<Document> getS4ClassPersistenceModel() {
        return database.getCollection(Constants.MONGODB_COLLECTION_CLASS);
    }
}
