package com.lucassimoesmartins.myroomexample.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucassimoesmartins.myroomexample.R
import com.lucassimoesmartins.myroomexample.asynctask.GetAllStudentsTask
import com.lucassimoesmartins.myroomexample.database.StudentDatabase
import com.lucassimoesmartins.myroomexample.model.Student
import com.lucassimoesmartins.myroomexample.ui.adapter.StudentListAdapter
import kotlinx.android.synthetic.main.activity_student_list.*

class StudentListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        title = getString(R.string.student_list)

        setUI()
    }

    override fun onResume() {
        super.onResume()

        GetAllStudentsTask(StudentDatabase.getInstance(this)!!.getStudentDao(),
            fun(list: List<Student>) {
                lvStudents.adapter = StudentListAdapter(this, ArrayList(list))
            }).execute()
    }

//    override fun onCreateContextMenu(
//        menu: ContextMenu?,
//        v: View?,
//        menuInfo: ContextMenu.ContextMenuInfo?
//    ) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//        menuInflater.inflate(R.menu.student_list_menu, menu)
//    }
//
    override fun onContextItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.btnRemoveMenuStudentList) {
            removeStudent(item)
        }

        return super.onContextItemSelected(item)
    }

    private fun removeStudent(item : MenuItem) {
        val student = item.menuInfo
        student.toString()
    }

    private fun setUI() {

        fabNewStudent.setOnClickListener {
            startActivity(Intent(this, StudentFormActivity::class.java))
        }

        lvStudents.layoutManager = LinearLayoutManager(this)
    }
}
