package com.lucassimoesmartins.myroomexample.database.dao

import androidx.room.*
import com.lucassimoesmartins.myroomexample.model.Student

@Dao
interface StudentDao {

    @Insert
    fun save(student: Student)

    @Query("SELECT * FROM student")
    fun getAllStudents(): List<Student>

    @Delete
    fun delete(student: Student)

    @Update
    fun edit(student: Student)
}