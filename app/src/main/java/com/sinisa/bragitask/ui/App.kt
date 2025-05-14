package com.sinisa.bragitask.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.sinisa.bragitask.navigation.NavGraph
import com.sinisa.bragitask.navigation.mainRouter
import com.sinisa.bragitask.ui.theme.BragiTaskTheme

@Composable
fun App() {

    val navController = rememberNavController()

    BragiTaskTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FilterButton(modifier = Modifier.padding(16.dp)) {
                    navController.navigate(NavGraph.Filters) {
                        launchSingleTop = true
                    }
                }
            },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = NavGraph.Movies,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                mainRouter()
            }
        }
    }
}

@Composable
private fun FilterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Filters"
            )
        }
    )
}