package com.example.androidapp_categoricalcalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.androidapp_categoricalcalendar.data.Database
import com.example.androidapp_categoricalcalendar.nav.BottomNavItem
import com.example.androidapp_categoricalcalendar.nav.BottomNavigationBar
import com.example.androidapp_categoricalcalendar.nav.Navigation
import com.example.androidapp_categoricalcalendar.ui.theme.AndroidApp_CategoricalCalendarTheme

class App : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		//Build db
		val db = Room.databaseBuilder(
			applicationContext,
			Database::class.java, "db"
		).build()
		val taskDao = db.eventDao()

		setContent {
			val navController = rememberNavController()
			AndroidApp_CategoricalCalendarTheme {
				// A surface container using the 'background' color from the theme
				Scaffold (
					floatingActionButton = {
						FloatingActionButton(onClick = {
							//Do Something
							navController.navigate("AddEventView")
						}) {
							Icon(Icons.Default.Add, contentDescription = "Add Event")
						}
					},
					bottomBar = {
						BottomNavigationBar(
							items = listOf(
								BottomNavItem(
									name = "Week",
									route = "WeekView",
									icon = Icons.Default.DateRange
								),
								BottomNavItem(
									name = "Agenda",
									route = "AgendaView",
									icon = Icons.Default.List
								),
								BottomNavItem(
									name = "Categories",
									route = "CategoryView",
									icon = Icons.Default.Settings
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
						color = Color.Black
					) {
						Navigation(navController = navController)
					}
				}

			}
		}
	}
}