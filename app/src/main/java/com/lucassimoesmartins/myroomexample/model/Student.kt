package com.lucassimoesmartins.myroomexample.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Student (

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String = "",
    var phone: String = "",
    var email: String = ""
) : Serializable