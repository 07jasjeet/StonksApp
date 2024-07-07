package com.example.stonksapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stonksapp.repository.preferences.AppPreferences
import com.example.stonksapp.ui.navigation.AppNavigation
import com.example.stonksapp.ui.theme.StonksAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StonksAppTheme(
                appPreferences = remember { appPreferences }
            ) {
                var scrollToTopState by remember { mutableStateOf(false) }
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = StonksAppTheme.colorScheme.background,
                    contentColor = StonksAppTheme.colorScheme.onBackground
                ) { innerPadding ->
                    AppNavigation(
                        scrollRequestState = scrollToTopState,
                        paddingValues = innerPadding,
                        onScrollToTop = { scrollToTop ->
                            scope.launch {
                                if (scrollToTopState) {
                                    scrollToTop()
                                    scrollToTopState = false
                                }
                            }
                        },
                        snackbarState = snackbarHostState
                    )
                }
            }
        }
    }
}