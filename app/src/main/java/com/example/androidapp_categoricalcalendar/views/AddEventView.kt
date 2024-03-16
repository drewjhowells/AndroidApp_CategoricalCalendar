package com.example.androidapp_categoricalcalendar.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun Preview() {
	AddEventView(navController = rememberNavController())
}

@Composable
fun AddEventView(navController: NavHostController) {
	var eventDetail by remember {
		mutableStateOf("")
	}
	Column (
		modifier = Modifier.fillMaxSize()
	){
		Text(
			text = "New Event Details",
			fontSize = 30.sp,
			color = Color.White,
			modifier = Modifier.align(Alignment.CenterHorizontally)
		)
		TextField(
			modifier = Modifier.fillMaxWidth(),
			placeholder = {
				Text(text = "Name")
			},
			value = eventDetail,
			onValueChange = {textEntered ->
				eventDetail = textEntered
			}
		)
		TextField(
			modifier = Modifier.fillMaxWidth(),
			placeholder = {
				Text(text = "Category")
			},
			value = eventDetail,
			onValueChange = {textEntered ->
				eventDetail = textEntered
			}
		)
		Button(
			modifier = Modifier.align(Alignment.CenterHorizontally),
			onClick = {
			//Add Event to list & to db
			navController.navigate("WeekView")
		}) {
			Text(text = "Add Event")
		}
	}
}