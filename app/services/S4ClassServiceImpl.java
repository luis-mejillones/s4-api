package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.ResponseMessage;
import models.S4Class;
import org.slf4j.Logger;
import play.libs.Json;

import java.util.List;
import java.util.Optional;

public class S4ClassServiceImpl implements GenericService<S4Class> {
    private final GenericRepository<S4Class> repository;
    private final Logger logger;

    public S4ClassServiceImpl(
            final GenericRepository<S4Class> repository,
            final Logger logger
    ) {
        this.repository = repository;
        this.logger = logger;
    }

    @Override
    public S4Class create(S4Class s4Class) {
        return this.repository.create(s4Class);
    }

    @Override
    public List<S4Class> getAll() {
        return this.repository.getAll();
    }

    @Override
    public Optional<S4Class> getById(String id) {
        return this.repository.getById(id);
    }

    @Override
    public Boolean delete(String id) {
        return this.repository.delete(id);
    }

    @Override
    public void update(String id, S4Class newData) {
        Optional<S4Class> currentStudent = this.getById(id);
        if (currentStudent.isPresent()) {
            S4Class current = currentStudent.get();
            current.update(newData);

            this.repository.update(current);
        }
    }

    @Override
    public JsonNode getResponseMessage(Integer status, String body) {
        ResponseMessage response = new ResponseMessage.Builder()
                .withStatus(status)
                .withBody(body)
                .build();

        return Json.toJson(response);
    }
}
