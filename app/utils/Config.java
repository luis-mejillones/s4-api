package utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Config {
    public MongoCollection<Document> getPersistenceModel() {
        MongoClient mongoClient = new MongoClient(Constants.MONGODB_HOST, Constants.MONGODB_PORT);
        MongoDatabase database = mongoClient.getDatabase(Constants.MONGODB_DATABASE);

        return database.getCollection(Constants.MONGODB_COLLECTION_STUDENT);
    }
}
