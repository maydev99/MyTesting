package com.bombadu.mytesting.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MyRepository
    private val allData: LiveData<List<MyData>>


    init {
        val myDao = MyDatabase.getDatabase(application, viewModelScope).myDao()
        repository = MyRepository(myDao)
        allData = repository.allData

    }

    fun insertData(myData: MyData) {
        repository.insertData(myData)
    }

    fun deleteData(myData: MyData) {
        repository.deleteData(myData)
    }

    fun deleteAllData() {
        repository.deleteAllData()
    }

    fun getAllData(): LiveData<List<MyData>> {
        return allData
    }
}
