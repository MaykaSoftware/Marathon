package com.sportunity.marathon.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.sportunity.marathon.AppScreen
import com.sportunity.marathon.ConstantAppScreenName.ID_ARGUMENT
import com.sportunity.marathon.getTitleByRoute
import com.sportunity.marathon.presentation.event.MapScreen
import com.sportunity.marathon.presentation.event.MarathonRaceViewModel
import com.sportunity.marathon.presentation.events.MarathonEventsScreen
import com.sportunity.marathon.presentation.events.MarathonEventsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarathonApp() {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var actionBarTitle by rememberSaveable { mutableStateOf("Events") }
    val context = LocalContext.current

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            actionBarTitle = backStackEntry.destination.route?.let { getTitleByRoute(context, it) }!!
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MarathonAppBar(
                title = actionBarTitle,
                scrollBehavior,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        Navigation(navController = navController, innerPadding = innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarathonAppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun Navigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.MarathonEventsScreen.route,
        modifier = Modifier.padding(innerPadding)
    ) {

        composable(
            route = AppScreen.MarathonEventsScreen.route
        ) {
            val viewModel: MarathonEventsViewModel = hiltViewModel()
            val marathonEvents = viewModel.marathonEventsFlow.collectAsLazyPagingItems()
            MarathonEventsScreen(navController = navController, marathonEvents = marathonEvents)
        }

        composable(
            route = AppScreen.MarathonRaceScreen.route,
            arguments = listOf(navArgument(ID_ARGUMENT) {
                type = NavType.StringType
            })
        ) {
            val viewModel: MarathonRaceViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            MapScreen(state = state)
        }
    }
}