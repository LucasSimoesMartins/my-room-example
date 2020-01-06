package com.lucassimoesmartins.myroomexample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lucassimoesmartins.myroomexample.constants.Constants
import com.lucassimoesmartins.myroomexample.database.dao.StudentDao
import com.lucassimoesmartins.myroomexample.model.Student

@Database(entities = arrayOf(Student::class), version = Constants.DATABASE_VERSION)

abstract class StudentDatabase : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao

    companion object {

        private var INSTANCE: StudentDatabase? = null

        fun getInstance(context: Context): StudentDatabase? {
            if (INSTANCE == null) {
                synchronized(StudentDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                        Constants.DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}