package com.lucassimoesmartins.myroomexample.asynctask

import android.os.AsyncTask
import com.lucassimoesmartins.myroomexample.database.dao.StudentDao
import com.lucassimoesmartins.myroomexample.model.Student

class SaveStudentTask(
    val studentDao: StudentDao,
    val student: Student,
    val delegate : () -> Unit
) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void?): Void? {
        val id = studentDao.save(student)
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        delegate()
    }
}