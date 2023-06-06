package vassar.cmpu203.vassardiningapp.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import vassar.cmpu203.vassardiningapp.ui.screens.FavoritesScreen
import vassar.cmpu203.vassardiningapp.ui.screens.MenuScreen
import vassar.cmpu203.vassardiningapp.ui.screens.RestrictionsScreen
import java.time.LocalDate

sealed class Screen(val route: String) {
    object Menu : Screen("Menu")
    object Favorites : Screen("Favorites")
    object Restrictions : Screen("Restrictions")
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppBarPage(currentScreen: Screen) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(currentScreen.route) },
                navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch { scaffoldState.drawerState.open() } }) {
                        Icon(Icons.Default.Menu, contentDescription = "Open drawer")
                    }
                },
                actions = {
                    // Add other actions here
                }
            )
        },
        drawerContent = { DrawerMenu(navController, drawerState = scaffoldState.drawerState)},
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Menu.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Menu.route) { MenuScreen("gordon", LocalDate.now()) }
            composable(Screen.Favorites.route) { FavoritesScreen() }
            composable(Screen.Restrictions.route) { RestrictionsScreen() }
        }
    }
}

@Composable
private fun DrawerMenu(navController: NavController, drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate(Screen.Menu.route)
            coroutineScope.launch { drawerState.close() }
        }) {
            Text(Screen.Menu.route)
        }

        Button(onClick = {
            navController.navigate(Screen.Favorites.route)
            coroutineScope.launch { drawerState.close() }
        }) {
            Text(text = Screen.Favorites.route)
        }

        Button(onClick = {
            navController.navigate(Screen.Restrictions.route)
            coroutineScope.launch { drawerState.close() }
        }) {
            Text(Screen.Restrictions.route)
        }
    }
}

// TODO: use this to replace DrawerMenu composable
@Composable
private fun DrawerButton(route: String, navController: NavController, drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()

    Button(onClick = {
        navController.navigate(route)
        coroutineScope.launch { drawerState.close() }
    }) {
        Text(text = route)
    }
}
