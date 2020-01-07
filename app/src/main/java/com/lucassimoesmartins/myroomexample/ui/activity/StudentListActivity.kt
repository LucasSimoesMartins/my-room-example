package com.lucassimoesmartins.myroomexample.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucassimoesmartins.myroomexample.R
import com.lucassimoesmartins.myroomexample.asynctask.GetAllStudentsTask
import com.lucassimoesmartins.myroomexample.asynctask.RemoveStudentTask
import com.lucassimoesmartins.myroomexample.constants.Constants
import com.lucassimoesmartins.myroomexample.database.StudentDatabase
import com.lucassimoesmartins.myroomexample.database.dao.StudentDao
import com.lucassimoesmartins.myroomexample.model.Student
import com.lucassimoesmartins.myroomexample.ui.adapter.StudentListAdapter
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentListActivity : AppCompatActivity() {

    private var studentList: MutableList<Student> = mutableListOf()
    private lateinit var studentDao: StudentDao
    private lateinit var studentListAdapter: StudentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        title = getString(R.string.student_list)

        setUI()
    }

    private fun setUI() {

        studentDao = StudentDatabase.getInstance(this)!!.getStudentDao()

        studentListAdapter = StudentListAdapter(this, ArrayList(studentList), clickItemList())

        lvStudents.layoutManager = LinearLayoutManager(this)
        lvStudents.adapter = studentListAdapter

        fabNewStudent.setOnClickListener {
            startActivity(Intent(this, StudentFormActivity::class.java))
        }
    }

    private fun clickItemList(): (Student) -> Unit {
        return fun(student: Student) {

            val intent = Intent(this, StudentFormActivity::class.java)
            intent.putExtra(Constants.STUDENT_KEY, student)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadStudentList()
    }

    private fun loadStudentList() {

        GetAllStudentsTask(studentDao, fun(list: List<Student>) {
            studentList = list.toMutableList()
            studentListAdapter.updateList(studentList)
        }).execute()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        if (item.title == getString(R.string.remove)) {
            removeStudent(item)
        }

        return super.onContextItemSelected(item)
    }

    private fun removeStudent(item: MenuItem) {

        val student: Student = studentList[item.itemId]
        RemoveStudentTask(studentDao, student, fun() {
            studentList.remove(student)
            studentListAdapter.updateList(studentList)
        }).execute()
    }
}
