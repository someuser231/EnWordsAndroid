package com.example.enwordsandroid.ui

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
import com.example.enwordsandroid.view_models.MainVM
import kotlinx.coroutines.launch


const val screen_home = "home"
const val screen_dict = "dictionary"

@Composable
fun Drawer(mainVm: MainVM) {
    val drawerItems = listOf(
        DrawerItem(
            title = "Home",
            screen = screen_home
        ),
        DrawerItem(
            title = "Dictionary",
            screen = screen_dict
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
                            scope.launch {
                                selectedItem.value = item
                                drawerState.close()
                                navController.navigate(item.screen) {
                                    popUpTo(item.screen) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    )
                }
            }
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = screen_home
            ) {
                composable(screen_home) {
                    Home(mainVm)
                }
                composable(screen_dict) {
                    Dictionary()
                }
            }
        }
    )
}