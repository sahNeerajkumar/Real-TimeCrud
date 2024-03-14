package com.example.realtimedatabase

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val studentModeList: ArrayList<StudentModel>,
    val context: Context,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val neme_item = view.findViewById<TextView>(R.id.name_item_Data)
        val email_item = view.findViewById<TextView>(R.id.email_item_Data)
        val UpadateIcon_item = view.findViewById<ImageView>(R.id.editIcon_item_Data)
        val deleteIcon_item = view.findViewById<ImageView>(R.id.deleteIcon_item_Data)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = studentModeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val studentList = studentModeList[position]
        holder.neme_item.text = studentList.name
        holder.email_item.text = studentList.email

        holder.UpadateIcon_item.setOnClickListener {
            onClickListener.onUpdateStudentData(position)
        }
        holder.deleteIcon_item.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Delete")
            dialog.setMessage("Are you sure?")
            dialog.setPositiveButton("yes") { dialog, which ->
                onClickListener.onDeleteStudentData(position)
            }
            dialog.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            val alert = dialog.create()
            dialog.show()

        }
    }

    interface OnClickListener {
        fun onUpdateStudentData(position: Int)
        fun onDeleteStudentData(position: Int)
    }

}