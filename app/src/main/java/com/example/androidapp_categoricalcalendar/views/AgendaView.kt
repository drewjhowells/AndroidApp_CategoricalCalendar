package com.example.androidapp_categoricalcalendar.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.androidapp_categoricalcalendar.data.Event
import com.example.androidapp_categoricalcalendar.data.EventDao
import kotlinx.coroutines.runBlocking

@Composable
fun AgendaView(navController: NavHostController, eventDao: EventDao) {
    var events by remember {
        mutableStateOf(listOf<Event>())
    }
    runBlocking {
        events = eventDao.getAll()
    }

    LazyColumn {
        items(events) {event ->
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height((50*(event.duration/15)).dp)
            ){
                Text(text = event.title)
            }
        }
    }
}