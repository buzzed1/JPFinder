package com.buzzed.jpfinder

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.buzzed.jpfinder.data.JP
import com.buzzed.jpfinder.data.JPDao
import com.buzzed.jpfinder.data.JPFinderDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class JPDaoTest {
        private lateinit var jpDao: JPDao
        private lateinit var jpFinderDatabase: JPFinderDatabase

        @Before
        fun createDb() {
            val context: Context = ApplicationProvider.getApplicationContext()
            // Using an in-memory database because the information stored here disappears when the
            // process is killed.
            jpFinderDatabase = Room.inMemoryDatabaseBuilder(context, JPFinderDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
            jpDao = jpFinderDatabase.JPDao()
        }

        @After
        @Throws(IOException::class)
        fun closeDb() {
            jpFinderDatabase.close()
        }

    private var jp1 = JP(1, "Jones","George","","","","Westgreen","")
    private var jp2 = JP(2,"Smith", "Stephen", "George","Someplace","Sometown","Westgreen","smitstephen@someemail.com")


    private suspend fun addOneItemToDb() {
            jpDao.insert(jp1)
        }

        private suspend fun addTwoJpToDb() {
            jpDao.insert(jp1)
            jpDao.insert(jp2)
        }

        @Test
        @Throws(Exception::class)
        fun daoInsert_insertsJpIntoDB() = runBlocking {
            addTwoJpToDb()
            val allJPs = jpDao.getAllItems().first()
            assertEquals(allJPs[0], jp1)
        }

        @Test
        @Throws(Exception::class)
        fun dauUpdateItems_updatesJpInDB() = runBlocking {
            addTwoJpToDb()
            jpDao.update(JP(1, "James","George","","","","Westgreen",""))
           jpDao.update(JP(2, "Johnson", "Stephen", "George","Someplace","Sometown","Westgreen","smitstephen@someemail.com"))

            val allJPs = jpDao.getAllItems().first()
            assertEquals(allJPs[1], JP(1, "James","George","","","","Westgreen",""))
            assertEquals(allJPs[2], JP(2, "Johnson", "Stephen", "George","Someplace","Sometown","Westgreen","smitstephen@someemail.com"))
        }

        @Test
        @Throws(Exception::class)
        fun daoDeleteItems_deleteAllItemsFromDB() = runBlocking {
            addTwoJpToDb()
            jpDao.delete(jp1)
            jpDao.delete(jp2)

            val allItems = jpDao.getAllItems().first()
            assertTrue(allItems.isEmpty())
        }

        @Test
        @Throws(Exception::class)
        fun daoGetJp_returnsJPFromDB() = runBlocking {
            addOneItemToDb()
            val jp = jpDao.getJp(1)
            assertEquals(jp.first(), jp)
        }




    }

