package com.flashwizserver.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.flashwizserver.model.Flashcard;
import com.flashwizserver.model.User;


@Repository
public interface FlashcardRepository  extends  CrudRepository<Flashcard, Integer>  {

}
