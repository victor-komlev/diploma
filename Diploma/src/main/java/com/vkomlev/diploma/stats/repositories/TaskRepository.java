package com.vkomlev.diploma.stats.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.vkomlev.diploma.stats.entities.Task;

@Transactional
public interface TaskRepository extends MongoRepository<Task, String> {

    Task getTaskById(String id);

    List<Task> findByCreatedAtLessThan(Long timestamp);
}
