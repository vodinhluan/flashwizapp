package com.flashwizserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashwizserver.model.Group;
import com.flashwizserver.model.GroupUser;
import com.flashwizserver.model.User;

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, Integer> {
	List<GroupUser> findByGroup(Group group);

	List<GroupUser> findByUser(User user);

	List<GroupUser> findByUserAndGroup(User user, Group group);

	Boolean existsByGroupAndUser(Group group, User user);
}
