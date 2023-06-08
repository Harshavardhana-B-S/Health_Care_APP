package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_find_doctor.cardFDBack

class FindDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_doctor)


        cardFDBack.setOnClickListener{

            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}