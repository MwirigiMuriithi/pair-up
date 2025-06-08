// app/src/main/java/com/example/alandma/util/DataStoreManager.kt
package com.example.alandma.util

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("alandma_prefs")

class DataStoreManager(private val context: Context) {
  companion object {
    private val KEY_USER_ID    = stringPreferencesKey("user_id")
    private val KEY_TOKEN      = stringPreferencesKey("access_token")
    private val KEY_REFRESH    = stringPreferencesKey("refresh_token")
    private val KEY_GROUP_ID   = stringPreferencesKey("group_id")
  }

  val userId: Flow<String?> = context.dataStore.data.map { it[KEY_USER_ID] }
  val accessToken: Flow<String?> = context.dataStore.data.map { it[KEY_TOKEN] }
  val refreshToken: Flow<String?> = context.dataStore.data.map { it[KEY_REFRESH] }
  val groupId: Flow<String?> = context.dataStore.data.map { it[KEY_GROUP_ID] }

  suspend fun saveAuth(userId: String, accessToken: String, refreshToken: String) {
    context.dataStore.edit {
      it[KEY_USER_ID] = userId
      it[KEY_TOKEN]   = accessToken
      it[KEY_REFRESH] = refreshToken
    }
  }
  suspend fun saveGroupId(groupId: String) {
    context.dataStore.edit { it[KEY_GROUP_ID] = groupId }
  }
}
