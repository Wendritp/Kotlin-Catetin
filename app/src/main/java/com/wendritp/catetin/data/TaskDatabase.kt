package com.wendritp.catetin.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wendritp.catetin.db.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    //inject: same as @provides but used for our own class
    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback(){

        //Executed on the first time database is created on the phone. NOT everytime app is open.
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
               dao.insert(Task("Contoh Catatan Penting", isImportant = true))
               dao.insert(Task("Contoh Catatan 1"))
               dao.insert(Task("Contoh Catatan Selesai", isDone = true))
               dao.insert(Task("Contoh Catatan 2"))
            }

        }
    }
}