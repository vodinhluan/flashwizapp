package com.flashwizserver.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flashwizserver.model.Folder;

@Repository

public interface FolderRepository extends CrudRepository<Folder, Integer> {
	public Long countById(Integer id);
	  @Query("SELECT f FROM Folder f WHERE f.user.id = :userId")
	    List<Folder> findByUserId(@Param("userId") Integer userId);
}
