package com.bombadu.mytesting.database

import android.os.AsyncTask
import androidx.lifecycle.LiveData

class MyRepository(private val myDao: MyDao) {

    val allData: LiveData<List<MyData>> = myDao.getAllData()

    fun insertData(myData: MyData) {
        InsertDataAsyncTask(
            myDao
        ).execute(myData)

    }

    fun deleteData(myData: MyData) {
        DeleteDataAsyncTask(
            myDao
        ).execute(myData)

    }

    fun deleteAllData() {
        DeleteAllDataAsyncTask(
            myDao
        ).execute()

    }

    private class InsertDataAsyncTask(val myDao: MyDao) : AsyncTask<MyData, Unit, Unit>(){
        override fun doInBackground(vararg myData: MyData?) {
            myDao.insertData(myData[0]!!)
        }

    }

    private class DeleteDataAsyncTask(val myDao: MyDao) : AsyncTask<MyData, Unit, Unit>(){
        override fun doInBackground(vararg myData: MyData?) {
            myDao.deleteData(myData[0]!!)
        }

    }

    private class DeleteAllDataAsyncTask(val myDao: MyDao) : AsyncTask<Unit, Unit, Unit>(){
        override fun doInBackground(vararg p0: Unit?) {
            myDao.deleteAllData()
        }

    }


}