package com.buzzed.jpfinder.data

import kotlinx.coroutines.flow.Flow

class OfflineJPRepository(private val jpDao: JPDao) : JPRepository {

    override fun getAllJPStream(): Flow<List<JP>> = jpDao.getAllItems()

    override fun getJPStream(jp: JP): Flow<JP?> = jpDao.getJp(jp.lastName, jp.firstName)

    override suspend fun insertJP(jp: JP) = jpDao.insert(jp)

    override suspend fun deleteJP(jp: JP) = jpDao.delete(jp)

    override suspend fun updateJP(jp: JP) = jpDao.update(jp)

}