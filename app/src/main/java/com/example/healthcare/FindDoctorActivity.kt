package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_find_doctor.*

class FindDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_doctor)


        cardFDBack.setOnClickListener{
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        cardFDFamilyPhy.setOnClickListener{
            val intent=Intent(this,DoctorDetailsActivity::class.java)
            intent.putExtra("title","Family Physicians")
            startActivity(intent)
        }

        cardFDietician.setOnClickListener{
            val intent=Intent(this,DoctorDetailsActivity::class.java)
            intent.putExtra("title","Dietician")
            startActivity(intent)
        }

        cardFDDentist.setOnClickListener{
            val intent=Intent(this,DoctorDetailsActivity::class.java)
            intent.putExtra("title","Dentist")
            startActivity(intent)
        }

        cardFDSurgeon.setOnClickListener {
            val intent=Intent(this,DoctorDetailsActivity::class.java)
            intent.putExtra("title","Surgeon")
            startActivity(intent)
        }

        cardFDCardiologist.setOnClickListener {
            val intent=Intent(this,DoctorDetailsActivity::class.java)
            intent.putExtra("title","Cardiologist")
            startActivity(intent)
        }
    }
}