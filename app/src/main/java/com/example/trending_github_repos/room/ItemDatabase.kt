package com.example.trending_github_repos.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.trending_github_repos.models.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun getDao():RoomDao

    companion object{
        private var INSTANCE : ItemDatabase?=null

        fun getDatabase(context: Context): ItemDatabase{
            if (INSTANCE==null){
                INSTANCE=Room.databaseBuilder(context,ItemDatabase::class.java,"itemsDB").build()
            }
            return INSTANCE!!
        }
    }
}