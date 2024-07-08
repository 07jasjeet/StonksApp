package com.example.stonksapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.stonksapp.repository.preferences.AppPreferences
import com.example.stonksapp.ui.components.TextStonks
import com.example.stonksapp.ui.navigation.AppNavigation
import com.example.stonksapp.ui.navigation.AppNavigationItem
import com.example.stonksapp.ui.screens.explore.navigation.ExploreDestination.Companion.toExploreDestination
import com.example.stonksapp.ui.screens.search.SearchScreen
import com.example.stonksapp.ui.screens.search.rememberSearchBarState
import com.example.stonksapp.ui.theme.StonksAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appPreferences: AppPreferences

    @OptIn(ExperimentalMaterial3Api::class)
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
                val navController = rememberNavController()
                val searchBarState = rememberSearchBarState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = StonksAppTheme.colorScheme.background,
                    contentColor = StonksAppTheme.colorScheme.onBackground,
                    topBar = {
                        TopAppBar(
                            title = { TextStonks(text = "Stonks App") },
                            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                            navigationIcon = {
                                val currentDestination = navController
                                    .currentBackStackEntryAsState()
                                    .value
                                    ?.destination
                                    ?.route

                                if (currentDestination == AppNavigationItem.Explore.route) {
                                    IconButton(onClick = {
                                        // Do nothing
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Explore,
                                            tint = StonksAppTheme.colorScheme.text,
                                            contentDescription = null
                                        )
                                    }
                                } else {
                                    IconButton(onClick = {
                                        navController.popBackStack()
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBackIosNew,
                                            tint = StonksAppTheme.colorScheme.text,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                            actions = {
                                IconButton(
                                    onClick = { searchBarState.activate() }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        tint = StonksAppTheme.colorScheme.text,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    AppNavigation(
                        navController = navController,
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

                SearchScreen(
                    isActive = searchBarState.isActive,
                    navigateToDetails = {
                        navController.navigate(
                            AppNavigationItem.Details.createRoute(symbol = it)
                        )
                    },
                    deactivate = {
                        searchBarState.deactivate()
                    }
                )
            }
        }
    }
}