import com.example.flashwiz_fe.domain.model.GroupDTO
import retrofit2.Response
import retrofit2.http.Body
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
}
