// app/src/main/java/com/example/alandma/data/local/entity/GroupEntity.kt
package com.example.alandma.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class GroupEntity(
  @PrimaryKey val id: String,           // your shared groupId
  val name: String,                     // e.g. "Alice & Bob"
  val membersCsv: String,                // CSV of userIds: "alice123,bob456"
)
