package com.example.androidapp_categoricalcalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.androidapp_categoricalcalendar.ui.theme.AndroidApp_CategoricalCalendarTheme

class App : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			val navController = rememberNavController()
			AndroidApp_CategoricalCalendarTheme {
				// A surface container using the 'background' color from the theme
				Scaffold (
					floatingActionButton = {
						FloatingActionButton(onClick = {
							//Do Something
						}) {
							Icon(Icons.Default.Settings, contentDescription = "Settings")
						}
					},
					bottomBar = {
						BottomNavigationBar(
							items = listOf(
								BottomNavItem(
									name = "Agenda",
									route = "AgendaScreen",
									icon = Icons.Default.DateRange
								),
								BottomNavItem(
									name = "Categories",
									route = "CategoryScreen",
									icon = Icons.Default.List
								)
							),
							navController = navController,
							modifier = Modifier.background(Color.Blue),
							onItemClick = {
								navController.navigate(it.route)
							}
						)
					}
				){paddingValues ->
					Surface(
						modifier = Modifier
							.fillMaxSize()
							.padding(paddingValues),
						color = Color.DarkGray
					) {
						Navigation(navController = navController)
					}
				}

			}
		}
	}
}