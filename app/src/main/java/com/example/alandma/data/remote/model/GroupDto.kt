// app/src/main/java/com/example/alandma/data/remote/model/GroupDto.kt
package com.example.alandma.data.remote.model

data class GroupDto(
  val id: String,
  val name: String,
  val members: List<String>
)
