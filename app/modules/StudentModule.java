package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.slf4j.LoggerFactory;
import services.StudentRepository;
import services.StudentService;
import utils.Constants;

public class StudentModule extends AbstractModule {
    @Override
    protected void configure() {
//        bind(MessageReceiver.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    public StudentService getMerchantService(
            StudentRepository repository
    ) {
        return new StudentService(
                repository,
                LoggerFactory.getLogger(StudentService.class)
        );
    }

    @Provides
    @Singleton
    public StudentRepository getStudentRepository() {
        MongoClient mongoClient = new MongoClient(Constants.MONGODB_HOST, Constants.MONGODB_PORT);
        MongoDatabase database = mongoClient.getDatabase(Constants.MONGODB_DATABASE);
        return new StudentRepository(
                database.getCollection(Constants.MONGODB_COLLECTION_STUDENT),
                LoggerFactory.getLogger(StudentRepository.class)
        );
    }

//    @Provides
//    @Singleton
//    public MessageSender getMessageSender() {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(Constants.QUEUE_HOST);
//        factory.setPort(Constants.QUEUE_PORT);
//        factory.setUsername(Constants.QUEUE_USER_NAME);
//        factory.setPassword(Constants.QUEUE_PASSWORD);
//
//        return new MessageSender(factory, LoggerFactory.getLogger(MessageSender.class));
//    }
}