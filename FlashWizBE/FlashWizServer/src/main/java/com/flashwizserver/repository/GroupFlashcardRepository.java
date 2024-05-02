package com.flashwizserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashwizserver.model.GroupFlashcard;


@Repository
public interface GroupFlashcardRepository extends JpaRepository<GroupFlashcard, Integer> {

}
