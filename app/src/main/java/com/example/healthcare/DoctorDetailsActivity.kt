package com.example.healthcare


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DoctorDetailsActivity : AppCompatActivity() {

    private lateinit var titleDD: TextView
    private lateinit var backBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        titleDD=findViewById(R.id.textViewDDTitle)
        backBtn=findViewById(R.id.buttonDDBack)

        val intent= intent
        val title=intent.getStringExtra("title")
        titleDD.text=title

        backBtn.setOnClickListener {
            val intent= Intent(this,FindDoctorActivity::class.java)
            startActivity(intent)
        }


    }
}