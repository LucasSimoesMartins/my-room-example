package com.lucassimoesmartins.myroomexample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.lucassimoesmartins.myroomexample.R
import com.lucassimoesmartins.myroomexample.asynctask.SaveStudentTask
import com.lucassimoesmartins.myroomexample.constants.Constants
import com.lucassimoesmartins.myroomexample.database.StudentDatabase
import com.lucassimoesmartins.myroomexample.database.dao.StudentDao
import com.lucassimoesmartins.myroomexample.model.Student
import kotlinx.android.synthetic.main.activity_student_form.*

class StudentFormActivity : AppCompatActivity() {

    lateinit var studentDao: StudentDao
    lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_form)

        setUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.student_form_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.btnSaveMenuStudentForm) {
            editOrSaveStudent()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun editOrSaveStudent() {

        student.name = edtStudentName.text.toString()
        student.phone = edtStudentPhone.text.toString()
        student.email = edtStudentEmail.text.toString()

        if(student.id > 0) {
            editStudent()
        } else {
            saveStudent()
        }
    }

    private fun saveStudent() {

        SaveStudentTask(studentDao, student, fun(){
            finish()
        }).execute()
    }

    private fun editStudent() {

    }

    private fun setUI() {
        val database: StudentDatabase = StudentDatabase.getInstance(this)!!
        studentDao = database.getStudentDao()

        if (intent.hasExtra(Constants.STUDENT_KEY)) {
            student = intent.getSerializableExtra(Constants.STUDENT_KEY) as Student

            edtStudentName.setText(student.name)
            edtStudentPhone.setText(student.phone)
            edtStudentEmail.setText(student.email)
        } else {
            student = Student()
        }
    }
}
