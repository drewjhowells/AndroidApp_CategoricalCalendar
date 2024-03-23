package com.example.androidapp_categoricalcalendar.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androidapp_categoricalcalendar.data.Event
import com.example.androidapp_categoricalcalendar.data.EventDao
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEventView(navController: NavHostController, eventDao: EventDao, eventID: Int) {
	Text(text = "$eventID")
	val event = runBlocking {
		eventID.let { eventDao.getEventByID(eventID) }
	}
	var eventTitle by remember {
		mutableStateOf(event?.title)
	}
	var eventCategory by remember {
		mutableStateOf(event?.category)
	}
	Column (
		modifier = Modifier.fillMaxSize()
	){
		Text(
			text = "Edit Event Details",
			fontSize = 30.sp,
			color = Color.White,
			modifier = Modifier
				.align(Alignment.CenterHorizontally)
				.padding(10.dp)
		)
		eventTitle?.let {
			TextField(
				modifier = Modifier
					.fillMaxWidth()
					.padding(5.dp),
				placeholder = {
					Text(text = "Title")
				},
				value = it,
				onValueChange = {textEntered ->
					eventTitle = textEntered
				}
			)
		}
		eventCategory?.let {
			TextField(
				modifier = Modifier
					.fillMaxWidth()
					.padding(5.dp),
				placeholder = {
					Text(text = "Category")
				},
				value = it,
				onValueChange = {textEntered ->
					eventCategory = textEntered
				}
			)
		}
		var isDurationExpanded by remember {
			mutableStateOf(false)
		}
		var isTimeExpanded by remember {
			mutableStateOf(false)
		}
		var duration by remember {
			mutableStateOf(event?.duration.toString())
		}
		var startTime by remember {
			mutableStateOf(event?.startTime.toString())
		}
		Row {
			ExposedDropdownMenuBox(
				modifier = Modifier
					.padding(5.dp)
					.weight(1f),
				expanded = isDurationExpanded,
				onExpandedChange =  { isDurationExpanded = it}
			) {
				TextField(
					value = duration,
					onValueChange = {},
					readOnly = true,
					trailingIcon = {
						ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDurationExpanded)
					},
					colors = ExposedDropdownMenuDefaults.textFieldColors(),
					modifier = Modifier.menuAnchor()
				)

				ExposedDropdownMenu(
					expanded = isDurationExpanded,
					onDismissRequest = { isDurationExpanded = false}
				) {
					DropdownMenuItem(
						text = {
							Text(text = "15")
						},
						onClick = {
							duration = "15"
							isDurationExpanded = false
						}
					)
					DropdownMenuItem(
						text = {
							Text(text = "30")
						},
						onClick = {
							duration = "30"
							isDurationExpanded = false
						}
					)
					DropdownMenuItem(
						text = {
							Text(text = "45")
						},
						onClick = {
							duration = "45"
							isDurationExpanded = false
						}
					)
					DropdownMenuItem(
						text = {
							Text(text = "60")
						},
						onClick = {
							duration = "60"
							isDurationExpanded = false
						}
					)
				}
			}
			ExposedDropdownMenuBox(
				modifier = Modifier
					.padding(5.dp)
					.weight(1f),
				expanded = isTimeExpanded,
				onExpandedChange =  { isTimeExpanded = it}
			) {
				TextField(
					value = startTime,
					onValueChange = {},
					readOnly = true,
					trailingIcon = {
						ExposedDropdownMenuDefaults.TrailingIcon(expanded = isTimeExpanded)
					},
					colors = ExposedDropdownMenuDefaults.textFieldColors(),
					modifier = Modifier.menuAnchor()
				)

				ExposedDropdownMenu(
					expanded = isTimeExpanded,
					onDismissRequest = { isTimeExpanded = false}
				) {
					for (i in 1.. 24) {
						DropdownMenuItem(
							text = {
								Text(text = i.toString())
							},
							onClick = {
								startTime = i.toString()
								isTimeExpanded = false
							}
						)
					}
				}
			}
		}
		Row {
			Button(
				onClick = {
					event?.title = eventTitle.toString()
					event?.duration = duration.toInt()
					event?.category = eventCategory.toString()
					event?.startTime = startTime.toInt()
					runBlocking {
						if (event != null) {
							eventDao.updateEvent(event)
						}
					}
					navController.navigate("AgendaView")
				}) {
				Text(text = "Update Event")
			}
			Button(
				onClick = {
					runBlocking {
						event?.let {
							eventDao.delete(event)
						}
					}
					navController.navigate("AgendaView")
				}) {
				Icon(Icons.Default.Delete, contentDescription = "Delete")
			}
		}

	}
}