package com.flashwizserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.flashwizserver.model.Flashcard;


@Repository
public interface FlashcardRepository  extends  CrudRepository<Flashcard, Integer>  {

}
