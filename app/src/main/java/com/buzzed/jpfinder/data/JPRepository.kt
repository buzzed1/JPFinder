package com.buzzed.jpfinder.data

import kotlinx.coroutines.flow.Flow

interface JPRepository {
    /**
     * Retrieve all the JPs from the the given data source.
     */
    fun getAllJPStream(): Flow<List<JP>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    fun getJPStream(id: Int):JP

    fun getJpInCommunity(community: String): Flow<List<JP>>

    fun getFavoriteJPs(): Flow<List<JP>>

    /**
     * Insert item in the data source
     */
    suspend fun insertJP(jp: JP)

    /**
     * Delete item from the data source
     */
    suspend fun deleteJP(jp: JP)

    /**
     * Update item in the data source
     */
    suspend fun updateJP(jp: JP)
}


