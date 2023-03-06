package com.buzzed.jpfinder.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JPDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(jp: JP)

        @Update
        suspend fun update(jp: JP)

        @Delete
        suspend fun delete(jp: JP)

        @Query("SELECT * from jp_list WHERE id = :id")
        fun getJp(id: Int?): JP

        @Query("SELECT * from jp_list ORDER BY lastname ASC")
        fun getAllItems(): Flow<List<JP>>

        @Query("SELECT * from jp_list WHERE community = :community")
        fun getJpInCommunity(community: String): Flow<List<JP>>




}