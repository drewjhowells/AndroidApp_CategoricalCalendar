package com.example.androidapp_categoricalcalendar.nav

sealed class Screen(val route: String) {
	object WeekView : Screen("WeekView")
	object AgendaView : Screen("AgendaView")
	object CategoryView : Screen("CategoryView")
	object AddEventView : Screen("AddEventView")
	object EditEventView : Screen("EditEventView/{editId}")
}