package com.example.enwordsandroid.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.enwordsandroid.view_models.MainViewModel
import kotlinx.coroutines.launch


const val screen_home = "home"
const val screen_dict = "dictionary"
const val screen_learn = "learning"

@Composable
fun Drawer(mainViewModel: MainViewModel) {
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
                            navController.navigate(item.screen) {
                                popUpTo(item.screen) {
                                    inclusive = true

                                }
                            }
                            scope.launch {
                                selectedItem.value = item
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = screen_home,
                enterTransition = {
                    EnterTransition.None
                },
                exitTransition = {
                    ExitTransition.None
                }
            ) {
                composable(screen_home) {
                    Home(mainViewModel)
                }
                composable(screen_dict) {
                    Dictionary(mainViewModel)
                    mainViewModel.getWords()
                }
                composable(screen_learn) {
                    Learning(mainViewModel)
                    mainViewModel.getLearnWords()
                }
            }
        }
    )
}