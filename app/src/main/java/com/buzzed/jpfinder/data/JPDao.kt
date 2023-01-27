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

        @Query("SELECT * from jp_list WHERE lastname = :lastName AND firstname = :firstName")
        fun getJp(lastName: String, firstName: String): Flow<JP>

        @Query("SELECT * from jp_list ORDER BY lastname ASC")
        fun getAllItems(): Flow<List<JP>>

}