package com.example.flashwiz_fe.domain.repository

import com.example.flashwiz_fe.domain.model.GroupDTO
import retrofit2.Response

interface GroupRepository {
    suspend fun createGroup(userId: Int, groupName: String): Response<GroupDTO>

    suspend fun getAllGroups(): List<GroupDTO>

    suspend fun getUserGroups(userId: Int): List<GroupDTO>

    suspend fun joinGroup(userId: Int, groupCode: String): Response<GroupDTO>

    suspend fun getGroup(groupId: Int): Map<String, Any>

    suspend fun shareFolder(userId: Int, groupId: Int, folderId: Int): Response<Int>

}