package com.flashwizserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flashwizserver.model.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {
}