package com.flashwizserver.service;

import java.util.List;
import java.util.Optional;

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

	public List<Folder> getAllFolder() {
		return (List<Folder>) folderRepo.findAll();
	}

	public Folder saveFolder(Folder folder) {
		if (folder.getDescriptions() == null) {
			folder.setDescriptions("");
		}
		return folderRepo.save(folder);
	}

	public void deleteFolder(Folder folder) {
		folderRepo.delete(folder);
	}

	public Folder findById(Integer folderId) {
		return folderRepo.findById(folderId).orElse(null);
	}


	public Folder getFolderById(Integer folderId) {
		Optional<Folder> optionalFolder = folderRepo.findById(folderId);
		return optionalFolder.orElse(null);
	}
	public void delete(Integer id){
		Long countById=folderRepo.countById(id);
	
		folderRepo.deleteById(id);
	
}

}

	

