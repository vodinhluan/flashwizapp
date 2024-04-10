package com.flashwizserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashwizserver.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
