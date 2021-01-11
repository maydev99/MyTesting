package com.bombadu.mytesting.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyRepository(private val myDao: MyDao) : ViewModel() {

    val allData: LiveData<List<MyData>> = myDao.getAllData()

    fun insertData(myData: MyData) {
        viewModelScope.launch(Dispatchers.IO) {
            myDao.insertData(myData)
        }
    }

    fun deleteData(myData: MyData) {
        viewModelScope.launch(Dispatchers.IO) {
            myDao.deleteData(myData)
        }
    }

    fun deleteAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            myDao.deleteAllData()
        }
    }

}