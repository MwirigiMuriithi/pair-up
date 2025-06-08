// app/src/main/java/com/example/alandma/data/remote/repository/GroupRepository.kt
package com.example.alandma.data.remote.repository

import com.example.alandma.data.local.dao.GroupDao
import com.example.alandma.data.local.entity.GroupEntity
import com.example.alandma.data.remote.api.GroupService
import com.example.alandma.data.remote.model.GroupDto
import com.example.alandma.data.remote.model.GroupRequest

class GroupRepository(
  private val service: GroupService,
  private val dao: GroupDao,
  private val dataStore: com.example.alandma.util.DataStoreManager
) {
  /** Create on server, then persist locally and in DataStore */
  suspend fun createGroup(name: String, members: List<String>): GroupDto {
    val resp = service.createGroup(GroupRequest(name, members))
    val dto = resp.body()!!
    // persist DataStore
    dataStore.saveGroupId(dto.id)
    // persist Room
    dao.upsert(GroupEntity(dto.id, dto.name, dto.members.joinToString(",")))
    return dto
  }

  /** Fetch from server and update local + DataStore */
  suspend fun fetchGroup(groupId: String): GroupDto {
    val dto = service.getGroup(groupId).body()!!
    dataStore.saveGroupId(dto.id)
    dao.upsert(GroupEntity(dto.id, dto.name, dto.members.joinToString(",")))
    return dto
  }
}
