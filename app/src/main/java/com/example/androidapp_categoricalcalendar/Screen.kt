package com.example.androidapp_categoricalcalendar

sealed class Screen(val route: String) {
	object WeekView : Screen("WeekView")
	object AgendaScreen : Screen("AgendaScreen")
	object CategoryScreen : Screen("CategoryScreen")
}