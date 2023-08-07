package com.verycoolapp.idea.repository;

import com.verycoolapp.idea.domain.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends MongoRepository<Idea, String> {

    List<Idea> findAllByCreatedBy(String createdBy);
}
