package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import models.S4Class;
import models.Student;
import org.slf4j.LoggerFactory;
import services.GenericRepository;
import services.GenericService;
import services.S4ClassRepositoryImpl;
import services.S4ClassServiceImpl;
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
    public GenericService<Student> getStudentService(
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
        final Persistence<S4Serializable> persistence = new Persistence(conf.getStudentPersistenceModel());
        return new StudentRepositoryImpl(
                persistence,
                LoggerFactory.getLogger(StudentRepositoryImpl.class)
        );
    }

    @Provides
    @Singleton
    public GenericService<S4Class> getS4ClassService(
            GenericRepository<S4Class> repository
    ) {
        return new S4ClassServiceImpl(
                repository,
                LoggerFactory.getLogger(StudentServiceImpl.class)
        );
    }

    @Provides
    @Singleton
    public GenericRepository<S4Class> getS4ClassRepository() {
        final Config conf = new Config();
        final Persistence<S4Serializable> persistence = new Persistence(conf.getS4ClassPersistenceModel());
        return new S4ClassRepositoryImpl(
                persistence,
                LoggerFactory.getLogger(StudentRepositoryImpl.class)
        );
    }
}