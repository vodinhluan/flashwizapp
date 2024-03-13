package com.flashwizserver.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flashwizserver.model.Flashcard;
import com.flashwizserver.model.Folder;
import com.flashwizserver.model.User;
import com.flashwizserver.repository.FolderRepository;

@Service
public class FolderDAO {
	@Autowired
	private FolderRepository folderRepo;
	public List<Folder> listFolder() {
		return (List<Folder>) folderRepo.findAll();
	}
	
	public Folder saveFolder(Folder folder) {
		 if (folder.getDescriptions() == null) {
			    folder.setDescriptions(""); // Set an empty string if descriptions is null
			  }
		return folderRepo.save(folder);
	}
	
	public void deleteFolder(Folder folder) {
		folderRepo.delete(folder);
	}

	public Folder findById(Integer folderId) {
	    // Sử dụng phương thức findById của FolderRepository để truy vấn folder
	    return folderRepo.findById(folderId).orElse(null);
	  }

}

