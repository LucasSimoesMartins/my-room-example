package com.lucassimoesmartins.myroomexample.asynctask

import android.os.AsyncTask
import com.lucassimoesmartins.myroomexample.database.dao.StudentDao
import com.lucassimoesmartins.myroomexample.model.Student

class GetAllStudentsTask(
    private val studentDao: StudentDao,
    private val delegate : (List<Student>) -> Unit
) : AsyncTask<Void, Void, List<Student>>() {

    override fun doInBackground(vararg params: Void?): List<Student> {
        return studentDao.getAllStudents()
    }

    override fun onPostExecute(allStudents: List<Student>) {
        super.onPostExecute(allStudents)
        delegate(allStudents)
    }
}