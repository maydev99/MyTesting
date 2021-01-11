package com.bombadu.mytesting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
class MyData(
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "time") var time: String,
    @ColumnInfo(name = "number") var number: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0


}