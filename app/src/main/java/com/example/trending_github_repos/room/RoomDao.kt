package com.example.trending_github_repos.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trending_github_repos.models.Item

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepoItems(repoList:List<Item>)

    @Query("SELECT * FROM item WHERE full_name || language LIKE '%' || :searchQuery || '%'")
    suspend fun getRepoItems(searchQuery:String): List<Item>


}