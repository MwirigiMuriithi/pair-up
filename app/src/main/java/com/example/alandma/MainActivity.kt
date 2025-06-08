// app/src/main/java/com/example/alandma/MainActivity.kt
package com.example.alandma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.example.alandma.ui.navigation.AuthNavGraph
import com.example.alandma.ui.navigation.NavGraph
import com.example.alandma.ui.theme.AlAndMaTheme
import com.example.alandma.util.DataStoreManager
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val dataStore: DataStoreManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // observe saved userId
            val userId by dataStore.userId.collectAsState(initial = null)

            // create a coroutine scope for invoking suspend functions
            val scope = rememberCoroutineScope()

            AlAndMaTheme {
                if (userId.isNullOrBlank()) {
                    AuthNavGraph(
                        onAuthSuccess = { id, token, refresh ->
                            scope.launch {
                                dataStore.saveAuth(
                                    userId      = id,
                                    accessToken = token,
                                    refreshToken= refresh
                                )
                            }
                        }
                    )
                } else {
                    NavGraph()
                }
            }
        }
    }
}



// package com.example.alandma



// import android.os.Bundle
// import androidx.activity.ComponentActivity
// import androidx.activity.compose.setContent
// import com.example.alandma.ui.theme.AlAndMaTheme
// import com.example.alandma.ui.navigation.NavGraph



// class MainActivity : ComponentActivity() {
//     override fun onCreate(savedInstanceState: Bundle?) {
//         super.onCreate(savedInstanceState)
//         setContent {
//             AlAndMaTheme {
//                 NavGraph()
//             }
//         }
//     }
// }
