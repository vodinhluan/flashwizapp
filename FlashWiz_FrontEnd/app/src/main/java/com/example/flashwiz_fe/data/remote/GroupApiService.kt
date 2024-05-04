
package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.domain.model.FlashcardDetail
import com.example.flashwiz_fe.domain.model.FolderDetail
import com.example.flashwiz_fe.domain.model.GroupDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GroupApiService {
    @POST("/{userId}/group/create")
    suspend fun createGroup(@Path("userId") userId: Int, @Body groupName: GroupDTO): Response<GroupDTO>

    @GET("/group/list")
    suspend fun getAllGroups(): List<GroupDTO>

    @GET("/group/user/{userId}")
    suspend fun getUserGroups(@Path("userId") userId: Int?): List<GroupDTO>

    @POST("/{userId}/group/join/{groupCode}")
    suspend fun joinGroup(@Path("userId") userId: Int, @Path("groupCode") groupCode: String): Response<GroupDTO>

    @GET("/group/{groupId}")
    suspend fun getGroup(@Path("groupId") groupId: Int): Map<String, Any>


    @POST("/{userId}/groups/{groupId}/folders/{folderId}/share")
    suspend fun shareFolder(@Path("userId") userId: Int, @Path("groupId") groupId: Int, @Path("folderId") folderId: Int): Response<Int>

    @GET("/group/{groupId}/folders")
    suspend fun getListFolderByGroupId(@Path("groupId") groupId: Int): Set<FolderDetail>
    @DELETE("/group/delete/{id}")
    suspend fun deleteGroup(@Path("id") id: Int): List<GroupDTO>
}
