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

    override fun equals(other: Any?): Boolean {
        if(javaClass != other?.javaClass) {
            return false
        }

        other as MyData
        if (id != other.id){
            return false
        }

        if (date != other.date) {
            return false
        }

        if (time != other.time) {
            return false
        }

        if (number != other.number) {
            return false
        }
        return true
    }
}