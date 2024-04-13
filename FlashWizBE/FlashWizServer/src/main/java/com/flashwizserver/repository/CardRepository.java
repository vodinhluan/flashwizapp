package com.flashwizserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flashwizserver.model.Card;
import com.flashwizserver.model.Flashcard;
import com.flashwizserver.model.Folder;

public interface CardRepository extends JpaRepository<Card, Integer> {

}