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
<<<<<<< HEAD

	public Folder getFolderById(Integer folderId) {
		// Triển khai logic lấy folder từ cơ sở dữ liệu dựa trên folderId
		Optional<Folder> optionalFolder = folderRepo.findById(folderId);
		return optionalFolder.orElse(null);
	}

}
=======

	public Folder getFolderById(Integer folderId) {
		// Triển khai logic lấy folder từ cơ sở dữ liệu dựa trên folderId
		Optional<Folder> optionalFolder = folderRepo.findById(folderId);
		return optionalFolder.orElse(null);
	}

}
>>>>>>> b96a23905502acf46685e6f51ff9c5a0f1ee9888
