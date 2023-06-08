package com.example.healthcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sharedpref = getSharedPreferences ("shared_prefs", Context.MODE_PRIVATE)
        val username =sharedpref.getString("username","").toString()
        Toast.makeText(this, "Welcome $username",Toast.LENGTH_SHORT).show()


        cardFindDoctor.setOnClickListener{
              val intent = Intent(this,FindDoctorActivity::class.java)
              startActivity(intent)
        }


        cardExit.setOnClickListener {
             val editor=sharedpref.edit()
             editor.clear()
             editor.apply()
             val intent = Intent(this, LoginActivity::class.java)
             startActivity(intent)
         }
    }
}