package com.example.androidapp_categoricalcalendar.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.androidapp_categoricalcalendar.data.Database
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
    Column {
        Text(
            text = "Agenda",
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp)
        )
        LazyColumn {
            items(events) {event ->
                Box (
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .height((30 * (event.duration / 15)).dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(color = Color.LightGray)
                        .clickable {
                            navController.navigate("EditEventView/" + event.id)
                        }
                ){
                    Row {
                        Text(text = event.title, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f).padding(5.dp))
                        Text(text = "${event.startTime}:00-${event.startTime}:${event.duration}", fontWeight = FontWeight.Light, modifier = Modifier.padding(5.dp))
                    }
                }
            }
        }
    }
}