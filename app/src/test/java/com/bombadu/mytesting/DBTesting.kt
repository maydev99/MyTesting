package com.bombadu.mytesting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.bombadu.mytesting.database.MyDao
import com.bombadu.mytesting.database.MyDatabase
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

//Code Link: https://alediaferia.com/2018/12/17/testing-livedata-room-android/

@RunWith(AndroidJUnit4::class)
class DBTesting {

    private lateinit var myDao: MyDao
    private lateinit var myDatabase: MyDatabase

    //Extension Added
    fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
        val observer = OneTimeObserver(handler = onChangeHandler)
        observe(observer, observer)
    }


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        myDatabase = Room.inMemoryDatabaseBuilder(context, MyDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        myDao = myDatabase.myDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        myDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetSampleData() {
        val sampleData = SampleDataClass()
        myDao.insertData(sampleData.mySampleData()) //Inserts SampleDataClass into
        myDao.getAllData().observeOnce { //Extension on top of class uses custom OneTimeObserver
            assertEquals(1, it.size) //Test Size of List
            assertEquals(101, it[0].number) //Test a specific value in list
        }
    }

}


