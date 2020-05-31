package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import models.Student;
import org.slf4j.LoggerFactory;
import services.GenericRepository;
import services.GenericService;
import services.StudentRepositoryImpl;
import services.StudentServiceImpl;
import utils.Config;
import utils.Persistence;
import utils.S4Serializable;

public class Module extends AbstractModule {
    @Override
    protected void configure() {

    }

    @Provides
    @Singleton
    public GenericService<Student> getGenericService(
            GenericRepository<Student> repository
    ) {
        return new StudentServiceImpl(
                repository,
                LoggerFactory.getLogger(StudentServiceImpl.class)
        );
    }

    @Provides
    @Singleton
    public GenericRepository<Student> getStudentRepository() {
        final Config conf = new Config();
        final Persistence<S4Serializable> persistence = new Persistence(conf.getPersistenceModel());
        return new StudentRepositoryImpl(
                persistence,
                LoggerFactory.getLogger(StudentRepositoryImpl.class)
        );
    }

//    @Provides
//    @Singleton
//    public StudentRepository getStudentRepository() {
//        final Config conf = new Config();
//        final Persistence<Student> persistence = new Persistence(conf.getPersistenceModel());
//        return new StudentRepositoryImpl(
//                persistence,
//                LoggerFactory.getLogger(StudentRepositoryImpl.class)
//        );
//    }

}