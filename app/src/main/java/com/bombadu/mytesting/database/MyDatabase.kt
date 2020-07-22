package com.bombadu.mytesting.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [MyData::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao(): MyDao

    private class MyDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { myDatabase ->
                scope.launch {  }
            }
        }
    }



    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MyDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "record_database"
                ).addCallback(MyDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}

