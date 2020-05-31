package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.slf4j.LoggerFactory;
import services.StudentRepository;
import services.StudentRepositoryImpl;
import services.StudentService;
import services.StudentServiceImpl;
import utils.Config;
import utils.Persistence;

public class StudentModule extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    public StudentService getMerchantService(
            StudentRepository repository
    ) {
        return new StudentServiceImpl(
                repository,
                LoggerFactory.getLogger(StudentServiceImpl.class)
        );
    }

    @Provides
    @Singleton
    public StudentRepository getStudentRepository() {
        final Config conf = new Config();
        final Persistence persistence = new Persistence(conf.getPersistenceModel());
        return new StudentRepositoryImpl(
                persistence,
                LoggerFactory.getLogger(StudentRepositoryImpl.class)
        );
    }
}