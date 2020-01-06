package com.lucassimoesmartins.myroomexample.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lucassimoesmartins.myroomexample.model.Student

@Dao
interface StudentDao {

    @Insert
    fun save(student: Student): Long

    @Query("SELECT * FROM student")
    fun getAllStudents(): List<Student>
}