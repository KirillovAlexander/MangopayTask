package com.verycoolapp.idea.repository;

import com.verycoolapp.idea.domain.Idea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends MongoRepository<Idea, String> {

}
