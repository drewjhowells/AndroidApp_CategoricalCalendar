package com.example.androidapp_categoricalcalendar.data

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import java.sql.Time

//Database class using Task entities
@Database(entities = [Event::class], version = 1)
abstract class Database : RoomDatabase() {
	abstract fun eventDao(): EventDao
}

//Dao interface for interacting with database
@Dao
interface EventDao {
	//Define SQL functions to use by DAO object
	@Query("SELECT * FROM event ORDER BY startTime")
	suspend fun getAll(): MutableList<Event>
	@Query("SELECT * FROM event WHERE id = :id")
	suspend fun getEventByID(id : Int): Event?
	@Insert
	suspend fun insertAll(vararg events: Event)
	@Update(entity = Event::class)
	suspend fun updateEvent(event : Event)
	@Delete
	suspend fun delete(event: Event)

//	suspend fun updateTask(taskName: String) {
//		val task = getTaskByName(taskName)
//		task?.let {
//			val updatedTask = it.copy(taskCompleted = !it.taskCompleted)
//			updateTask(updatedTask)
//		}
//	}
}

//Table definition for Task Entities
@Entity
data class Event(
	@PrimaryKey(autoGenerate = true) val id: Int = 0,
	@ColumnInfo(name = "title") val title: String,
	@ColumnInfo(name = "category") val category: String,
	@ColumnInfo(name = "duration") val duration: Int,
	@ColumnInfo(name = "startTime") val startTime: Int
)