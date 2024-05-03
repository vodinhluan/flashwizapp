package com.example.flashwiz_fe.domain.repository

import com.example.flashwiz_fe.domain.model.FolderDetail

interface FolderRepository {
    suspend fun getFoldersByUserId(userId: Int?) : List<FolderDetail>

    suspend fun getFolderById(folderId: Int) : FolderDetail
}