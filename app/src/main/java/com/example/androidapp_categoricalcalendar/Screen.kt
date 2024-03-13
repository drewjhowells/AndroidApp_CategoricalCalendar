package com.example.androidapp_categoricalcalendar

sealed class Screen(val route: String) {
	object AgendaScreen : Screen("AgendaScreen")
	object CategoryScreen : Screen("CategoryScreen")
}