package com.lucassimoesmartins.myroomexample.asynctask

import android.os.AsyncTask
import com.lucassimoesmartins.myroomexample.database.dao.StudentDao
import com.lucassimoesmartins.myroomexample.model.Student

class EditStudentTask(
    private val studentDao: StudentDao,
    private val student: Student,
    private val delegate: () -> Unit
) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void?): Void? {
        studentDao.edit(student)
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        delegate()
    }
}