package com.example.androidapp_categoricalcalendar.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.androidapp_categoricalcalendar.views.AddEventView
import com.example.androidapp_categoricalcalendar.views.AgendaView
import com.example.androidapp_categoricalcalendar.views.CategoryView
import com.example.androidapp_categoricalcalendar.views.WeekView

@Composable
fun Navigation(navController: NavHostController) {
	NavHost(navController = navController, startDestination = Screen.WeekView.route) {
		composable(Screen.WeekView.route) {
			WeekView(navController = navController)
		}
		composable(Screen.AgendaView.route) {
			AgendaView(navController = navController)
		}
		composable(Screen.CategoryView.route) {
			CategoryView(navController = navController)
		}
		composable(Screen.AddEventView.route) {
			AddEventView(navController = navController)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
	items: List<BottomNavItem>,
	navController: NavController,
	modifier: Modifier,
	onItemClick: (BottomNavItem) -> Unit
) {
	val backStackEntry = navController.currentBackStackEntryAsState()
	NavigationBar (
		modifier = Modifier,
		containerColor = Color.DarkGray,
		tonalElevation = 5.dp
	){
		items.forEach {item ->
			val selected = item.route == backStackEntry.value?.destination?.route
			NavigationBarItem(
				selected = selected,
				colors = NavigationBarItemDefaults.colors(
					selectedIconColor = Color.DarkGray,
					unselectedIconColor = Color.White,
					indicatorColor = Color.White,
				),
				onClick = { onItemClick(item) },
				icon = {
					Column(
						horizontalAlignment = CenterHorizontally
					) {
						if (item.badgeCount > 0) {
							BadgedBox(badge = {
								Badge {
									Text(text = item.badgeCount.toString())
								}
							}) {
								Icon(
									imageVector = item.icon,
									contentDescription = item.name)
							}
						} else {
							Icon(
								imageVector = item.icon,
								contentDescription = item.name)
						}
						if (selected) {
							Text(
								text = item.name,
								textAlign = TextAlign.Center,
								fontSize = 15.sp
							)
						}
					}

				}
			)
		}
	}
}