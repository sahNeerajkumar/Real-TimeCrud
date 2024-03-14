package com.example.realtimedatabase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class InsertData : AppCompatActivity() {
    private lateinit var editTextName: AppCompatEditText
    private lateinit var editTextEmail: AppCompatEditText
    private lateinit var buttonInsert: Button
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        editTextName = findViewById(R.id.editText_name_insert)
        editTextEmail = findViewById(R.id.editText_email_insert)
        buttonInsert = findViewById(R.id.button_insert)
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        buttonInsert.setOnClickListener {
            insertData()
        }
    }

    private fun insertData() {
        val getName = editTextName.text.toString()
        val getEmail = editTextEmail.text.toString()
        val studentId = databaseReference.push().key!!
        val studentModel = StudentModel(studentId, getName, getEmail)

        databaseReference.child(studentId).setValue(studentModel)
            .addOnSuccessListener {
                Toast.makeText(this, "student data Add", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "student data add failed", Toast.LENGTH_SHORT).show()
            }
    }

}
