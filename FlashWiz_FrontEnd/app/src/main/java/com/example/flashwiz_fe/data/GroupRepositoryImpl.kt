package com.example.flashwiz_fe.data

import com.example.flashwiz_fe.data.remote.GroupApiService
import com.example.flashwiz_fe.domain.model.GroupDTO
import com.example.flashwiz_fe.domain.repository.GroupRepository
import retrofit2.Response

class GroupRepositoryImpl(private val groupApiService: GroupApiService) : GroupRepository {
    override suspend fun createGroup(userId: Int, groupName: GroupDTO): Response<GroupDTO> {
        return groupApiService.createGroup(userId, groupName)
    }

    override suspend fun getAllGroups(): List<GroupDTO> {
        return groupApiService.getAllGroups()
    }

    override suspend fun getUserGroups(userId: Int): List<GroupDTO> {
        return groupApiService.getUserGroups(userId)
    }

    override suspend fun joinGroup(userId: Int, groupCode: String): Response<GroupDTO> {
        return groupApiService.joinGroup(userId, groupCode)
    }

    override suspend fun getGroup(groupId: Int): Map<String, Any> {
        return groupApiService.getGroup(groupId)
    }

    override suspend fun shareFolder(userId: Int, groupId: Int, folderId: Int): Response<Int> {
        return groupApiService.shareFolder(userId, groupId, folderId)
    }
}