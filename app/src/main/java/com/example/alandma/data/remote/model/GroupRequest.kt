// app/src/main/java/com/example/alandma/data/remote/model/GroupRequest.kt
package com.example.alandma.data.remote.model

data class GroupRequest(
  val name: String,
  val members: List<String> // list of userIds
)
