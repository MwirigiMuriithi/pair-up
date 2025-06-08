// app/src/main/java/com/example/alandma/data/local/dao/GroupDao.kt
package com.example.alandma.data.local.dao

import androidx.room.*
import com.example.alandma.data.local.entity.GroupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun upsert(group: GroupEntity)

  @Query("SELECT * FROM groups WHERE id = :groupId")
  fun getGroup(groupId: String): Flow<GroupEntity>
}
