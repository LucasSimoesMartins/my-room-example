package com.lucassimoesmartins.myroomexample.ui.adapter

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.lucassimoesmartins.myroomexample.R
import com.lucassimoesmartins.myroomexample.model.Student
import kotlinx.android.synthetic.main.item_student_list.view.*


class StudentListAdapter(
    private val context: Context,
    private val list: ArrayList<Student>
) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_student_list, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnCreateContextMenuListener {

        val txtStudentName = itemView.txtStudentName
        val txtStudentPhone = itemView.txtStudentPhone
        val txtStudentEmail = itemView.txtStudentEmail

        fun bindView(student: Student) {
            txtStudentName.text = student.name
            txtStudentPhone.text = student.phone
            txtStudentEmail.text = student.email

            itemView.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(
            menu: ContextMenu,
            v: View,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            val popup = PopupMenu(v.context, v)
            popup.menuInflater.inflate(R.menu.student_list_menu, menu)
            popup.show()
        }
    }

    fun updateList(students: List<Student>) {
        list.clear()
        list.addAll(students)
        notifyDataSetChanged()
    }
}