package com.example.flashwiz_fe.data

import com.example.flashwiz_fe.data.remote.FolderApiService
import com.example.flashwiz_fe.domain.model.FolderDetail
import com.example.flashwiz_fe.domain.repository.FolderRepository

class FolderRepositoryImpl(private val folderApiService: FolderApiService) : FolderRepository {
    override suspend fun getFoldersByUserId(userId: Int?) : List<FolderDetail> {
        return folderApiService.getFoldersByUserId(userId)
    }

    override suspend fun getFolderById(folderId: Int): FolderDetail {
        return folderApiService.getFolderById(folderId)
    }
}