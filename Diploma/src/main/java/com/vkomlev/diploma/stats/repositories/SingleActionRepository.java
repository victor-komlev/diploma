package com.vkomlev.diploma.stats.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.vkomlev.diploma.stats.entities.SingleAction;

@Transactional
public interface SingleActionRepository extends MongoRepository<SingleAction, String> {

    SingleAction getSingleActionById(String id);

    List<SingleAction> getSingleActionByParentTaskId(String id);
}
