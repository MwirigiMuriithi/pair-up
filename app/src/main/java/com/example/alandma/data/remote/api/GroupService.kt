// app/src/main/java/com/example/alandma/data/remote/api/GroupService.kt
package com.example.alandma.data.remote.api

import com.example.alandma.data.remote.model.GroupDto
import com.example.alandma.data.remote.model.GroupRequest
import retrofit2.Response
import retrofit2.http.*

interface GroupService {
  @POST("groups")
  suspend fun createGroup(@Body request: GroupRequest): Response<GroupDto>

  @GET("groups/{id}")
  suspend fun getGroup(@Path("id") groupId: String): Response<GroupDto>
}
