package com.example.enwordsandroid.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.enwordsandroid.ui.themes.clrSecondary
import com.example.enwordsandroid.view_models.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

const val screen_home = "home"
const val screen_dict = "dictionary"
const val screen_learn = "learning"
const val screen_addlInfo = "addl_word_info"

@Composable
fun Drawer(
    mainViewModel: MainViewModel = koinViewModel()
) {
    val drawerItems = listOf(
        DrawerItem(
            title = "Home",
            screen = screen_home
        ),
        DrawerItem(
            title = "Dictionary",
            screen = screen_dict
        ),
        DrawerItem(
            title = "Learning",
            screen = screen_learn
        )
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selectedItem = remember {
        mutableStateOf(drawerItems[0])
    }

    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(30.dp))
                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        label = {
                            Text(item.title)
                        },
                        selected = selectedItem.value == item,
                        onClick = {
//                            navController.navigate(item.screen) {
//                                popUpTo(item.screen) {
//                                    inclusive = true
//                                }
//                            }
                            navigateScreen(navController, item.screen)
                            scope.launch {
                                selectedItem.value = item
                                drawerState.close()
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = clrSecondary
                        ),
                        modifier = Modifier.padding(10.dp, 2.dp)
                    )
                }
            }
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = screen_home,
            ) {
                composable(screen_home) {
                    Home()
                }
                composable(screen_dict) {
                    Dictionary(toAddlInfo = { id ->
                        navigateScreen(navController, screen_addlInfo + "/$id")
                    })
                }
                composable(screen_learn) {
                    Learning()
                }
                composable(
                    screen_addlInfo + "/{wordId}",
                    arguments = listOf(
                        navArgument("wordId") {
                            type = NavType.IntType
                        }
                    )
                ) {
                    val wordId = it.arguments?.getInt("wordId")
                    AddlWordInfo(
                        navController = navController,
                        wordId = wordId ?: 0
                    )
                }
            }
        }
    )
}

fun navigateScreen(navController: NavHostController, screen: String) {
    navController.navigate(screen) {
        popUpTo(screen) {
            inclusive = true
        }
    }
}