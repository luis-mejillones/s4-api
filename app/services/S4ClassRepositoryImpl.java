package services;

import models.S4Class;
import models.Student;
import org.bson.Document;
import org.slf4j.Logger;
import utils.Persistence;
import utils.S4Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class S4ClassRepositoryImpl implements GenericRepository<S4Class> {
    private final Persistence<S4Serializable> persistence;
    private final Logger logger;

    public S4ClassRepositoryImpl(final Persistence<S4Serializable> persistence, final Logger logger) {
        this.persistence = persistence;
        this.logger = logger;
    }

    @Override
    public S4Class create(S4Class s4Class) {
        this.persistence.insert(s4Class);
        this.logger.info(">>> CREATE: Class with id: " + s4Class.getCode());

        return s4Class;
    }

    @Override
    public List<S4Class> getAll() {
        List<Document> list = this.persistence.getAll();
        List<S4Class> out = new ArrayList<>();
        list.forEach(item -> {
            S4Class s4Class = new S4Class();
            s4Class.fromDocument(item);
            out.add(s4Class);
        });

        this.logger.info(">>> GET: Class retrieved: " + out.size());

        return out;
    }

    @Override
    public Optional<S4Class> getById(String id) {
        Document document = this.persistence.find(id);

        if (document != null) {
            this.logger.info(">>> GET: Class retrieved id: " + id);
        } else {
            this.logger.info(">>> GET: Class with id: " + id + " not found");

            return Optional.empty();
        }
        S4Class s4Class = new S4Class();
        s4Class.fromDocument(document);

        return Optional.of(s4Class);
    }

    @Override
    public Boolean delete(String id) {
        Boolean result = this.persistence.delete(id);

        if (result) {
            this.logger.info(">>> DELETE: Class id: " + id);
        } else {
            this.logger.info(">>> DELETE: Class with id: " + id + " not found");
        }

        return result;
    }

    @Override
    public Boolean update(S4Class s4Class) {
        Boolean result = this.persistence.update(s4Class);

        if (result) {
            this.logger.info(">>> UPDATE: Class updated with id: " + s4Class.getCode());
        } else {
            this.logger.info(">>> UPDATE: Class with id: " + s4Class.getCode() + " not found");
        }

        return result;
    }
}
