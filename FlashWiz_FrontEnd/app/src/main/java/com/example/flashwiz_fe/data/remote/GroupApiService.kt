// GroupApiService.kt
package com.example.flashwiz_fe.data.remote

import com.example.flashwiz_fe.domain.model.Group
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GroupApiService {
    @POST("group/create")
    suspend fun createGroup(@Body group: Group): Response<Group>
}
