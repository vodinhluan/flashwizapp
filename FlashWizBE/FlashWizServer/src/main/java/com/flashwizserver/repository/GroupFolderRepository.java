package com.flashwizserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flashwizserver.model.Folder;
import com.flashwizserver.model.Group;
import com.flashwizserver.model.GroupFolder;

@Repository
public interface GroupFolderRepository extends JpaRepository<GroupFolder, Integer> {
    List<GroupFolder> findByGroup(Group group);

	Boolean existsByFolderAndGroup(Folder folder, Group group);

}