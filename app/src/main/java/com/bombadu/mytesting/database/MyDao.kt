package com.bombadu.mytesting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyDao {

    @Insert
    fun insertData(myData: MyData)

    @Delete
    fun deleteData(myData: MyData)

    @Query("DELETE FROM data_table")
    fun deleteAllData()

    @Query("SELECT * FROM data_table")
    fun getAllData(): LiveData<List<MyData>>

}